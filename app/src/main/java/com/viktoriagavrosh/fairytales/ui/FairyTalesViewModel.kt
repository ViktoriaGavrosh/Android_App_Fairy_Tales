package com.viktoriagavrosh.fairytales.ui

import androidx.lifecycle.ViewModel
import com.viktoriagavrosh.fairytales.data.CatalogFairyTales
import com.viktoriagavrosh.fairytales.data.CompositionType
import com.viktoriagavrosh.fairytales.model.Composition
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class FairyTalesViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(FairyTalesUiState())
    val uiState: StateFlow<FairyTalesUiState> = _uiState

    fun navigateToDetailScreen() {
        _uiState.update {
            it.copy(
                isShowHomeScreen = false
            )
        }
    }

    fun navigateToHomeScreen() {
        _uiState.update {
            it.copy(
                isShowHomeScreen = true
            )
        }
    }

    fun updateCompositionType(compositionType: CompositionType) {
        val compositions = when(compositionType) {
            CompositionType.FairyTales -> CatalogFairyTales.fairyTales
            CompositionType.Poems -> CatalogFairyTales.poems
            CompositionType.Puzzles -> CatalogFairyTales.puzzles
            CompositionType.Games -> CatalogFairyTales.games
        }
        _uiState.update {
            it.copy(
                compositionType = compositionType,
                compositions = compositions,
                isShowHomeScreen = true
            )
        }
    }
}

data class FairyTalesUiState(
    val compositionType: CompositionType = CompositionType.FairyTales,
    val compositions: List<Composition> = CatalogFairyTales.fairyTales,
    val currentComposition: Composition = compositions[0],
    val isShowHomeScreen: Boolean = true
)

