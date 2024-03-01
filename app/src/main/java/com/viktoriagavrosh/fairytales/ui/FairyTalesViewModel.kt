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

class FairyTalesViewModel(
    private val folkWorkRepository: FolkWorkRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(FairyTalesUiState())
    val uiState: StateFlow<FairyTalesUiState> = _uiState
/*
   val allStoriesState: StateFlow<List<FolkWork>> = folkWorkRepository.getAllStories()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = listOf()
        )

    fun getAllGamesStream() = folkWorkRepository.getAllGames()


 */
    init {
        updateCompositionType(FolkWorkType.Story)
    }

    fun navigateToDetailScreen(folkWork: FolkWork) {
        _uiState.update {
            it.copy(
                selectedWork = folkWork,
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

    fun updateCompositionType(folkWorkType: FolkWorkType) {

         val genre = when(folkWorkType) {
            FolkWorkType.Story -> "story"
            FolkWorkType.Poem -> "poem"
            FolkWorkType.Puzzle -> "puzzle"
            FolkWorkType.Game -> "game"
            FolkWorkType.Lullaby -> "lullaby"
        }
        val folkWorks = folkWorkRepository.getAllWorks(genre)

        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    folkWorkType = folkWorkType,
                    folkWorks = folkWorks.first().shuffled(),
                    selectedWork = folkWorks.first()[0],
                    isShowHomeScreen = true
                )
            }
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
    val folkWorkType: FolkWorkType = FolkWorkType.Story,
    val folkWorks: List<FolkWork> = listOf(                 // TODO my поменять потом !!!
        FolkWork(
            id = 0,
            genre = "story",
            title = "Story",
            text = "Story",
            answer = null,
            imageUri = null,
            audioUri = null,
            isFavorite = false)
    ),
    val selectedWork: FolkWork = folkWorks[0],
    val isShowHomeScreen: Boolean = true
)

