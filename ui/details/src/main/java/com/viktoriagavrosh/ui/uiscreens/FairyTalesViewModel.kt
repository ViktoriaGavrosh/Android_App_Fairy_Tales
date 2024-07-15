package com.viktoriagavrosh.ui.uiscreens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viktoriagavrosh.ui.model.FolkWork
import com.viktoriagavrosh.fairytales.model.Tale
import com.viktoriagavrosh.repositories.FolkWorkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel to retrieve and update item from the [FolkWorkRepository]'s data source
 */
@HiltViewModel
class FairyTalesViewModel @Inject constructor(
    private val folkWorkRepository: FolkWorkRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(FairyTalesUiState())
    val uiState: StateFlow<FairyTalesUiState> = _uiState

    init {
        updateCompositionType(FolkWorkType.Story)
    }

    /**
     * Update the value of an item field selectedWork in the data source
     */
    fun updateSelectedWork(id: Int) {
        val folkWork = folkWorkRepository.getWorkById(id)
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    selectedWork = folkWork.first().toFolkWork(),
                )
            }
        }
    }

    /**
     * Update [FairyTalesUiState] for navigation between tabs
     */
    fun updateCompositionType(folkWorkType: FolkWorkType) {
        val genre = getGenre(folkWorkType)
        val folkWorks = if (_uiState.value.isFavoriteWorks) {
            folkWorkRepository.getAllFavoriteWorks(genre).toFlowListFolkWork()
        } else {
            folkWorkRepository.getAllWorks(genre).toFlowListFolkWork()
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
                    //isShowHomeScreen = true
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
    /*
        companion object {
            val factory: ViewModelProvider.Factory = viewModelFactory {
                initializer {
                    val application = (this[APPLICATION_KEY] as FairyTalesApplication)
                    FairyTalesViewModel(application.container.folkWorkRepository)
                }
            }
        }


     */
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
    val isFavoriteWorks: Boolean = false
)

fun Tale.toFolkWork(): FolkWork {
    return FolkWork(
        id = id,
        genre = genre,
        title = title,
        text = text,
        answer = answer,
        imageUri = imageUri,
        audioUri = audioUri,
        isFavorite = isFavorite
    )
}

private fun Flow<List<Tale>>.toFlowListFolkWork(): Flow<List<FolkWork>> {
    return this.map { tales ->
        tales.map { tale ->
            tale.toFolkWork()
        }
    }
}
