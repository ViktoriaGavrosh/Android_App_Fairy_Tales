package com.viktoriagavrosh.fairytales.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.viktoriagavrosh.fairytales.FairyTalesApplication
import com.viktoriagavrosh.fairytales.data.TaleRepository
import com.viktoriagavrosh.fairytales.data.TaleType
import com.viktoriagavrosh.fairytales.model.FolkWork
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel to retrieve and update item from the [TaleRepository]'s data source
 */
class FairyTalesViewModel(
    private val taleRepository: TaleRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(TalesUiState())
    val uiState: StateFlow<TalesUiState> = _uiState

    init {
        updateCompositionType(TaleType.Story)
    }

    /**
     * Update [TalesUiState] for navigation to DetailScreen
     */
    fun navigateToDetailScreen(tale: FolkWork) {
        _uiState.update {
            it.copy(
                selectedTale = tale,
                isShowHomeScreen = false
            )
        }
    }

    /**
     * Update [TalesUiState] for navigation to HomeScreen
     */
    fun navigateToHomeScreen() {
        _uiState.update {
            it.copy(
                isShowHomeScreen = true
            )
        }
    }

    /**
     * Update [TalesUiState] for navigation between tabs
     */
    fun updateCompositionType(taleType: TaleType) {
        val genre = getGenre(taleType)
        val tale = if (_uiState.value.isFavoriteTales) {
            taleRepository.getAllFavoriteTales(genre)
        } else {
            taleRepository.getAllTales(genre)
        }

        var selectedTale = _uiState.value.selectedTale
        viewModelScope.launch {
            if (tale.first().isNotEmpty()) {
                selectedTale = tale.first().first()
            }
            _uiState.update {
                it.copy(
                    taleType = taleType,
                    tale = tale.first(),
                    selectedTale = selectedTale,
                    isShowHomeScreen = true
                )
            }
        }
    }

    /**
     * Update the value of an item field is_favorite in the data source
     */
    fun updateTaleFavorite(tale: FolkWork) {
        val changedFavoriteState = !tale.isFavorite
        val taleType = _uiState.value.taleType
        viewModelScope.launch {
            taleRepository.updateFavoriteTale(tale.id, changedFavoriteState)
        }
        updateCompositionType(taleType)
    }

    /**
     * Update [TalesUiState] for navigation to Favorite List
     */
    fun updateListFavoriteTales() {
        val newState = !_uiState.value.isFavoriteTales
        changeIsFavoriteTales(newState)
        val taleType = _uiState.value.taleType
        updateCompositionType(taleType)
    }

    internal fun changeIsFavoriteTales(isFavoriteTales: Boolean) {
        _uiState.update {
            it.copy(
                isFavoriteTales = isFavoriteTales
            )
        }
    }

    private fun getGenre(taleType: TaleType): String = when (taleType) {
        TaleType.Story -> "story"
        TaleType.Poem -> "poem"
        TaleType.Puzzle -> "puzzle"
        TaleType.Game -> "game"
        TaleType.Lullaby -> "lullaby"

    }

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FairyTalesApplication)
                FairyTalesViewModel(application.container.taleRepository)
            }
        }
    }
}

/**
 * Holds the UI state.
 */
data class TalesUiState(
    val taleType: TaleType = TaleType.Story,
    val tale: List<FolkWork> = listOf(
        FolkWork(
            id = 0,
            genre = "story",
            title = "Story",
            text = "Text",
            answer = "Answer",
            imageUri = null,
            audioUri = null,
            isFavorite = false
        )
    ),
    val selectedTale: FolkWork = tale[0],
    val isShowHomeScreen: Boolean = true,
    val isFavoriteTales: Boolean = false
)

