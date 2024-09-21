package com.viktoriagavrosh.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viktoriagavrosh.details.model.ReadBook
import com.viktoriagavrosh.details.utils.toReadBook
import com.viktoriagavrosh.repositories.ReadRepository
import com.viktoriagavrosh.repositories.utils.RequestResult
import com.viktoriagavrosh.repositories.utils.ShelfGenre
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel to retrieve and update item from the [ReadRepository]'s data source
 */
@HiltViewModel(assistedFactory = ReadViewModel.ReadViewModelFactory::class)
class ReadViewModel @AssistedInject constructor(
    @Assisted private val bookId: Int,
    @Assisted private val genre: ShelfGenre,
    private val repository: ReadRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ReadUiState())
    internal val uiState: StateFlow<ReadUiState>
        get() = _uiState

    init {
        initUiState()
    }

    internal fun initUiState() {
        viewModelScope.launch {
            val requestResultBook = repository.getBookById(bookId, genre).first()
            val requestResultTextSize = repository.getTextSize().first()

            if (requestResultBook is RequestResult.Error || requestResultTextSize is RequestResult.Error) {
                _uiState.update {
                    it.copy(
                        isError = true
                    )
                }
            } else {
                val book = requestResultBook.data?.toReadBook() ?: ReadBook()
                val textSize = requestResultTextSize.data ?: 8.0F

                _uiState.update {
                    it.copy(
                        book = book,
                        textSize = textSize
                    )
                }
            }
        }
    }

    @AssistedFactory
    interface ReadViewModelFactory {
        fun create(bookId: Int, genre: ShelfGenre): ReadViewModel
    }
}

internal data class ReadUiState(
    val book: ReadBook = ReadBook(),
    val isError: Boolean = false,
    val textSize: Float = 0.0F,
)
