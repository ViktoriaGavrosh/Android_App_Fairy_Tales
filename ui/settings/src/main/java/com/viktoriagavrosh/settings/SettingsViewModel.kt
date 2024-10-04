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
 * ViewModel to retrieve and update item from the [SettingsRepository]'s data source
 */
@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsState())
    val uiState: StateFlow<SettingsState>
        get() = _uiState

    init {
        initSettingsState()
    }

    internal fun initSettingsState() {
        viewModelScope.launch {
            val requestResult = settingsRepository.getSettings().first()
            if (requestResult is RequestResult.Error) {
                _uiState.update {
                    it.copy(
                        isError = true
                    )
                }
            } else {
                val textSize = requestResult.data?.textSize ?: 8.0F
                _uiState.update {
                    it.copy(
                        textSize = convertToValidTextSize(textSize)
                    )
                }
            }
        }
    }

    /**
     * Update the value of textSize in the data source
     */
    internal fun updateTextSize(textSize: Float) {
        viewModelScope.launch {
            settingsRepository.updateTextSize(textSize)
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

data class SettingsState(
    val textSize: Float = 0.0F,
    val isError: Boolean = false,
)
