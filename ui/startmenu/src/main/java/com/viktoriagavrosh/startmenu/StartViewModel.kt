package com.viktoriagavrosh.startmenu

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
 * ViewModel to update and retrieve data from repository data source
 *
 * @param repository instance of [MenuRepository]
 */
@HiltViewModel
class StartViewModel @Inject constructor(
    private val repository: MenuRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow(StartUiState())

    init {
        initStartUiState()
    }

    internal val uiState: StateFlow<StartUiState>
        get() = _uiState

    /**
     * init instance of [StartUiState]
     */
    internal fun initStartUiState() {
        viewModelScope.launch {
            getLastTaleId()
            updateRandomTale()
        }
    }

    /**
     * Update last tale value of [StartUiState]
     */
    internal fun updateLastTale() {
        viewModelScope.launch {
            val newLastTaleId = uiState.first().randomTaleId
            repository.updateLastTaleId(newLastTaleId)
            _uiState.update {
                it.copy(
                    lastTaleId = newLastTaleId,
                )
            }
        }
    }

    /**
     * Update random tale value of [StartUiState]
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

    private fun getLastTaleId() {
        viewModelScope.launch {
            val requestResultLastTale = repository.getLastTaleId().first()

            if (requestResultLastTale is RequestResult.Error) {
                _uiState.update {
                    it.copy(
                        isError = true,
                    )
                }
            } else {
                val lastTaleId = requestResultLastTale.data ?: 1
                _uiState.update {
                    it.copy(
                        lastTaleId = lastTaleId,
                    )
                }
            }
        }
    }
}

/**
 * holds [StartScreen] state
 *
 * @param randomTaleId random tale id for ui
 * @param lastTaleId last tale id for ui
 * @param isError boolean parameter describes screen state. If true ErrorScreen will be shown.
 */
internal data class StartUiState(
    val randomTaleId: Int = 0,
    val lastTaleId: Int = 0,
    val isError: Boolean = false,
)
