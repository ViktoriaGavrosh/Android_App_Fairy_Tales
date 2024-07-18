package com.viktoriagavrosh.details.uiscreens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viktoriagavrosh.details.model.FolkWorkUiDetails
import com.viktoriagavrosh.fairytales.model.Tale
import com.viktoriagavrosh.home.uiscreens.FolkWorkType
import com.viktoriagavrosh.repositories.FolkWorkRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

/**
 * ViewModel to retrieve and update item from the [FolkWorkRepository]'s data source
 */
@HiltViewModel(assistedFactory = TaleViewModel.TaleViewModelFactory::class)
class TaleViewModel @AssistedInject constructor(   // TODO было Inject
    @Assisted private val taleId: Int,    // TODO не было
    private val folkWorkRepository: FolkWorkRepository,
) : ViewModel() {
    /*
    private val _uiState = MutableStateFlow(FairyTalesUiState())
    val uiState: StateFlow<FairyTalesUiState> = _uiState
     */

    val folkWorkUiDetails: StateFlow<FolkWorkUiDetails> = folkWorkRepository.getWorkById(taleId)  // TODO изменила taleId
        .map { tale ->
        tale.toFolkWorkUiDetails()
    }
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            FolkWorkUiDetails()
        )

    // TODO добавлено для передачи значения во ViewModel
    @AssistedFactory
    interface TaleViewModelFactory {
        fun create(taleId: Int): TaleViewModel
    }


    /*
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
    fun updateWorkFavorite(folkWork: FolkWorkUiDetails) {
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

     */
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

/*  TODO спрятала временно пока подключон модуль  home
/**
 * Enum class to describe the destination of tab navigation
 */
enum class FolkWorkType(
    @DrawableRes val iconId: Int,
    val textId: Int
) {
    Story(
        iconId = R.drawable.ic_text,
        textId = R.string.title_fairy_tales
    ),
    Puzzle(
        iconId = R.drawable.ic_puzzle,
        textId = R.string.title_puzzle
    ),
    Poem(
        iconId = R.drawable.ic_poem,
        textId = R.string.title_poem
    ),
    Game(
        iconId = R.drawable.ic_game,
        textId = R.string.title_game
    ),
    Lullaby(
        iconId = R.drawable.ic_lullaby,
        textId = R.string.lullaby
    );
}


 */

/*   TODO delete
/**
* Holds the UI state.
*/
data class FairyTalesUiState(
   val folkWorkType: FolkWorkType = FolkWorkType.Story,
    val folkWorks: List<FolkWorkUiDetails> = listOf(
       FolkWorkUiDetails(
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
   val selectedWork: FolkWorkUiDetails = folkWorks[0],
   //val isFavoriteWorks: Boolean = false
)

 */

fun Tale.toFolkWorkUiDetails(): FolkWorkUiDetails {
   return FolkWorkUiDetails(
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
/*  TODO delete
private fun Flow<List<Tale>>.toFlowListFolkWork(): Flow<List<FolkWorkUiDetails>> {
   return this.map { tales ->
       tales.map { tale ->
           tale.toFolkWorkUiDetails()
       }
   }
}


 */
