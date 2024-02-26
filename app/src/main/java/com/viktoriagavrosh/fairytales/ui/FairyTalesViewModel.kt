package com.viktoriagavrosh.fairytales.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.viktoriagavrosh.fairytales.FairyTalesApplication
import com.viktoriagavrosh.fairytales.data.CatalogFairyTales
import com.viktoriagavrosh.fairytales.data.CompositionType
import com.viktoriagavrosh.fairytales.data.FolkWorkRepository
import com.viktoriagavrosh.fairytales.model.Composition
import com.viktoriagavrosh.fairytales.model.FolkWork
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class FairyTalesViewModel(
    private val folkWorkRepository: FolkWorkRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(FairyTalesUiState())
    val uiState: StateFlow<FairyTalesUiState> = _uiState

   val allStoriesState: StateFlow<List<FolkWork>> = folkWorkRepository.getAllStories()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = listOf()
        )

    fun getAllGamesStream() = folkWorkRepository.getAllGames()

    fun navigateToDetailScreen(composition: Composition) {
        _uiState.update {
            it.copy(
                selectedComposition = composition,
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
                selectedComposition = compositions[0],
                isShowHomeScreen = true
            )
        }
    }

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FairyTalesApplication)
                FairyTalesViewModel(application.container.folkWorkRepository)
            }
        }
    }
}

data class FairyTalesUiState(
    val compositionType: CompositionType = CompositionType.FairyTales,
    val compositions: List<Composition> = CatalogFairyTales.fairyTales,
    val selectedComposition: Composition = compositions[0],
    val isShowHomeScreen: Boolean = true
)

