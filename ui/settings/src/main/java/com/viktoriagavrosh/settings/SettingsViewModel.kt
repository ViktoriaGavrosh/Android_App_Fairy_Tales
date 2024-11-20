package com.viktoriagavrosh.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viktoriagavrosh.repositories.SettingsRepository
import com.viktoriagavrosh.repositories.utils.RequestResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel to update data to repository DataStore
 *
 * @param repository instance of [SettingsRepository]
 */
@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: SettingsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState>
        get() = _uiState

    init {
        initSettingsState()
    }

    /**
     * init instance of [SettingsUiState]
     */
    internal fun initSettingsState() {
        viewModelScope.launch {
            val requestResult = repository.getSettings().first()
            if (requestResult is RequestResult.Error) {
                _uiState.update {
                    it.copy(
                        isError = true
                    )
                }
            } else {
                val textSize = requestResult.data?.textSize ?: 24.0F
                _uiState.update {
                    it.copy(
                        textSize = convertToValidTextSize(textSize)
                    )
                }
            }
        }
    }

    /**
     * Update text size value of repository
     */
    internal fun updateTextSize(textSize: Float) {
        viewModelScope.launch {
            repository.updateTextSize(textSize)
            initSettingsState()
        }
    }

    private fun convertToValidTextSize(textSize: Float): Float {
        return when {
            textSize > 60 -> 60.0F
            textSize < 8 -> 8.0F
            else -> textSize
        }
    }
}

/**
 * holds [SettingsScreen] state
 *
 * @param textSize book's text size
 * @param isError boolean parameter describes screen state. If true ErrorScreen will be shown.
 */
data class SettingsUiState(
    val textSize: Float = 0.0F,
    val isError: Boolean = false,
)
