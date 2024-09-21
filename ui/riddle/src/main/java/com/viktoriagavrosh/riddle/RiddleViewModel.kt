package com.viktoriagavrosh.riddle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viktoriagavrosh.repositories.ReadRepository
import com.viktoriagavrosh.repositories.utils.RequestResult
import com.viktoriagavrosh.repositories.utils.ShelfGenre
import com.viktoriagavrosh.riddle.model.ReadRiddle
import com.viktoriagavrosh.riddle.utils.toReadRiddle
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
@HiltViewModel(assistedFactory = RiddleViewModel.RiddleViewModelFactory::class)
class RiddleViewModel @AssistedInject constructor(
    @Assisted private val riddleId: Int,
    private val repository: ReadRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(RiddleUiState())
    internal val uiState: StateFlow<RiddleUiState>
        get() = _uiState

    init {
        initUiState()
    }

    internal fun initUiState() {
        viewModelScope.launch {
            val requestResultRiddle = repository.getBookById(riddleId, ShelfGenre.Riddles).first()
            val requestResultTextSize = repository.getTextSize().first()

            if (requestResultRiddle is RequestResult.Error || requestResultTextSize is RequestResult.Error) {
                _uiState.update {
                    it.copy(
                        isError = true
                    )
                }
            } else {
                val riddle = requestResultRiddle.data?.toReadRiddle() ?: ReadRiddle()
                val textSize = requestResultTextSize.data ?: 8.0F

                _uiState.update {
                    it.copy(
                        riddle = riddle,
                        textSize = textSize
                    )
                }
            }
        }
    }

    @AssistedFactory
    interface RiddleViewModelFactory {
        fun create(bookId: Int): RiddleViewModel
    }
}

internal data class RiddleUiState(
    val riddle: ReadRiddle = ReadRiddle(),
    val isError: Boolean = false,
    val textSize: Float = 0.0F,
)
