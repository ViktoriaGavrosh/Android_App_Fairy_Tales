package com.viktoriagavrosh.librarymenu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viktoriagavrosh.repositories.MenuRepository
import com.viktoriagavrosh.repositories.utils.RequestResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel to update data to repository data source
 *
 * @param repository instance of [MenuRepository]
 */
@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val repository: MenuRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow(LibraryUiState())

    init {
        initLibraryUiState()
    }

    internal val uiState: StateFlow<LibraryUiState>
        get() = _uiState

    /**
     * init instance of [LibraryUiState]
     */
    internal fun initLibraryUiState() {
        updateRandomTale()
    }

    /**
     * Update last tale value of repository
     */
    internal fun updateLastTale() {
        viewModelScope.launch {
            val newLastTaleId = uiState.first().randomTaleId
            repository.updateLastTaleId(newLastTaleId)
        }
    }

    /**
     * Update random tale value of [LibraryUiState]
     */
    internal fun updateRandomTale() {
        viewModelScope.launch {
            val requestResultRandomTale = repository.getRandomTaleId().first()
            if (requestResultRandomTale is RequestResult.Error) {
                _uiState.update {
                    it.copy(
                        isError = true,
                    )
                }
            } else {
                val randomTaleId = requestResultRandomTale.data ?: 1
                _uiState.update {
                    it.copy(
                        randomTaleId = randomTaleId,
                    )
                }
            }
        }
    }
}

/**
 * holds [LibraryScreen] state
 *
 * @param randomTaleId random tale id for ui
 * @param isError boolean parameter describes screen state. If true ErrorScreen will be shown.
 */
internal data class LibraryUiState(
    val randomTaleId: Int = 0,
    val isError: Boolean = false,
)
