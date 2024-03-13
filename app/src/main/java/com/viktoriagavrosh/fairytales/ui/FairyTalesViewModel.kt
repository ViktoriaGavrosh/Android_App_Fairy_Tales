package com.viktoriagavrosh.fairytales.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.viktoriagavrosh.fairytales.FairyTalesApplication
import com.viktoriagavrosh.fairytales.data.FolkWorkRepository
import com.viktoriagavrosh.fairytales.data.FolkWorkType
import com.viktoriagavrosh.fairytales.model.FolkWork
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel to retrieve and update item from the [FolkWorkRepository]'s data source
 */
class FairyTalesViewModel(
    private val folkWorkRepository: FolkWorkRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(FairyTalesUiState())
    val uiState: StateFlow<FairyTalesUiState> = _uiState

    init {
        updateCompositionType(FolkWorkType.Story)
    }

    /**
     * Update [FairyTalesUiState] for navigation to DetailScreen
     */
    fun navigateToDetailScreen(folkWork: FolkWork) {
        _uiState.update {
            it.copy(
                selectedWork = folkWork,
                isShowHomeScreen = false
            )
        }
    }

    /**
     * Update [FairyTalesUiState] for navigation to HomeScreen
     */
    fun navigateToHomeScreen() {
        _uiState.update {
            it.copy(
                isShowHomeScreen = true
            )
        }
    }

    /**
     * Update [FairyTalesUiState] for navigation between tabs
     */
    fun updateCompositionType(folkWorkType: FolkWorkType) {
        val genre = getGenre(folkWorkType)
        val folkWorks = if (_uiState.value.isFavoriteWorks) {
            folkWorkRepository.getAllFavoriteWorks(genre)
        } else {
            folkWorkRepository.getAllWorks(genre)
        }

        var selectedWork = _uiState.value.selectedWork
        viewModelScope.launch {
            if (folkWorks.first().isNotEmpty()) {
                selectedWork = folkWorks.first().first()
            }
            _uiState.update {
                it.copy(
                    folkWorkType = folkWorkType,
                    folkWorks = folkWorks.first(),
                    selectedWork = selectedWork,
                    isShowHomeScreen = true
                )
            }
        }
    }

    /**
     * Update the value of an item field is_favorite in the data source
     */
    fun updateWorkFavorite(folkWork: FolkWork) {
        val changedFavoriteState = !folkWork.isFavorite
        val folkWorkType = _uiState.value.folkWorkType
        viewModelScope.launch {
            folkWorkRepository.updateFavoriteWork(folkWork.id, changedFavoriteState)
        }
        updateCompositionType(folkWorkType)
    }

    /**
     * Update [FairyTalesUiState] for navigation to Favorite List
     */
    fun updateListFavoriteWorks() {
        val newState = !_uiState.value.isFavoriteWorks
        changeIsFavoriteWorks(newState)
        val folkWorkType = _uiState.value.folkWorkType
        updateCompositionType(folkWorkType)
    }

    internal fun changeIsFavoriteWorks(isFavoriteWorks: Boolean) {
        _uiState.update {
            it.copy(
                isFavoriteWorks = isFavoriteWorks
            )
        }
    }

    private fun getGenre(folkWorkType: FolkWorkType): String = when (folkWorkType) {
        FolkWorkType.Story -> "story"
        FolkWorkType.Poem -> "poem"
        FolkWorkType.Puzzle -> "puzzle"
        FolkWorkType.Game -> "game"
        FolkWorkType.Lullaby -> "lullaby"

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

/**
 * Holds the UI state.
 */
data class FairyTalesUiState(
    val folkWorkType: FolkWorkType = FolkWorkType.Story,
    val folkWorks: List<FolkWork> = listOf(
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
    val selectedWork: FolkWork = folkWorks[0],
    val isShowHomeScreen: Boolean = true,
    val isFavoriteWorks: Boolean = false
)

