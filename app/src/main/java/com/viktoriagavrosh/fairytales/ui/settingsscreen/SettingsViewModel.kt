package com.viktoriagavrosh.fairytales.ui.settingsscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viktoriagavrosh.fairytales.data.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel to retrieve and update item from [SettingsRepository]`s
 */
@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(SettingsState())
    val uiState: StateFlow<SettingsState>
        get() = _uiState

    init {
        getSettingsState()
    }

    private fun getSettingsState() {
        viewModelScope.launch {
            val actualTextSize = settingsRepository.getTextSize()
                .map { convertToValidTextSize(it) }
                .first()
            _uiState.update {
                it.copy(textSize = actualTextSize)
            }
        }
    }

    /**
     * Update value of textSize in data source
     */
    fun updateTextSize(textSize: Float) {
        viewModelScope.launch {
            settingsRepository.updateTextSize(textSize)
        }
    }

    private fun convertToValidTextSize(textSize: Float) : Float {
        return when {
            textSize > 100 -> 100.0F
            textSize < 8 -> 8.0F
            else -> textSize
        }
    }

}

data class SettingsState(
    val textSize: Float = 0.0F
)