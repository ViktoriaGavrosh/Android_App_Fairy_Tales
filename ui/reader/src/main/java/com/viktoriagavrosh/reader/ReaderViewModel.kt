package com.viktoriagavrosh.reader

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viktoriagavrosh.reader.model.ReadBook
import com.viktoriagavrosh.reader.utils.toReadBook
import com.viktoriagavrosh.repositories.ReadRepository
import com.viktoriagavrosh.repositories.utils.RequestResult
import com.viktoriagavrosh.repositories.utils.ShelfGenre
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel to retrieve and update item from the [ReadRepository]'s data source
 */
@HiltViewModel(assistedFactory = ReaderViewModel.ReaderViewModelFactory::class)
class ReaderViewModel @AssistedInject constructor(
    @Assisted private val bookId: Int,
    @Assisted private val genre: ShelfGenre,
    private val repository: ReadRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ReaderUiState())
    internal val uiState: StateFlow<ReaderUiState>
        get() = _uiState

    private var _textSize: Flow<Float> = repository.getTextSize()
        .map { requestResult ->
            if (requestResult is RequestResult.Error) {
                _uiState.update {
                    it.copy(isError = true)
                }
                0.0F
            } else {
                requestResult.data ?: 0.0F
            }
        }

    init {
        initUiState()
    }

    internal val textSize: StateFlow<Float>
        get() = _textSize.stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            0.0F
        )


    internal fun initUiState() {
        viewModelScope.launch {
            val requestResultBook = repository.getBookById(bookId, genre).first()

            if (requestResultBook is RequestResult.Error) {
                _uiState.update {
                    it.copy(
                        isError = true
                    )
                }
            } else {
                val book = requestResultBook.data?.toReadBook() ?: ReadBook()

                _uiState.update {
                    it.copy(
                        book = book,
                    )
                }
            }
        }
    }

    @AssistedFactory
    interface ReaderViewModelFactory {
        fun create(bookId: Int, genre: ShelfGenre): ReaderViewModel
    }
}

internal data class ReaderUiState(
    val book: ReadBook = ReadBook(),
    val isError: Boolean = false,
)
