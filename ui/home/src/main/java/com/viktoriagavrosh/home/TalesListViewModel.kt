package com.viktoriagavrosh.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viktoriagavrosh.home.elements.TaleType
import com.viktoriagavrosh.home.model.TaleUiHome
import com.viktoriagavrosh.repositories.RequestResult
import com.viktoriagavrosh.repositories.TaleRepository
import com.viktoriagavrosh.repositories.model.Tale
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel to retrieve and update item from the [TaleRepository]'s data source
 */
@HiltViewModel
class TalesListViewModel @Inject constructor(
    private val taleRepository: TaleRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(TalesListUiState())
    val uiState: StateFlow<TalesListUiState> = _uiState

    private var _screenState = taleRepository
        .getTales(getGenre(TaleType.Story), _uiState.value.isFavoriteTalesList)
        .map { it.toHomeScreenState() }

    internal val screenState: Flow<HomeScreenState>
        get() = _screenState
    /*.stateIn(     TODO проверить где меньше ресурсов кушает
    viewModelScope,
    SharingStarted.Lazily,
    HomeScreenState.None()
)

     */

    init {
        updateTaleType(TaleType.Story)
    }

    /**
     * Update [TalesListUiState] for navigation between tabs
     */
    fun updateTaleType(taleType: TaleType) {
        val genre = getGenre(taleType)
        _screenState = taleRepository.getTales(genre, _uiState.value.isFavoriteTalesList)
            .map { it.toHomeScreenState() }

        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    taleType = taleType,
                )
            }
        }
    }

    /**
     * Update the value of an item field is_favorite in the data source
     */
    fun updateTaleFavorite(tale: TaleUiHome) {
        val changedFavoriteState = !tale.isFavorite
        val taleType = _uiState.value.taleType
        viewModelScope.launch {
            taleRepository.updateFavoriteTale(tale.id, changedFavoriteState)
        }
        updateTaleType(taleType)
    }

    /**
     * Update [TalesListUiState] for navigation to Favorite List
     */
    fun updateFavoriteTalesList() {
        val newState = !_uiState.value.isFavoriteTalesList
        changeIsFavoriteTalesList(newState)
        val taleType = _uiState.value.taleType
        updateTaleType(taleType)
    }

    internal fun changeIsFavoriteTalesList(isFavorite: Boolean) {
        _uiState.update {
            it.copy(
                isFavoriteTalesList = isFavorite
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
}

/**
 * Holds the UI state.
 */
data class TalesListUiState(
    val taleType: TaleType = TaleType.Story,
    val isFavoriteTalesList: Boolean = false
)

internal sealed class HomeScreenState(val tales: List<TaleUiHome>? = null) {
    class None : HomeScreenState()
    class Success(tales: List<TaleUiHome>) : HomeScreenState(tales)
    class Loading : HomeScreenState()
    class Error : HomeScreenState()
}

internal fun RequestResult<List<Tale>>.toHomeScreenState(): HomeScreenState {
    return when (this) {
        is RequestResult.Success -> HomeScreenState.Success(
            tales = data.map { it.toTaleUiHome() }
        )

        is RequestResult.Loading -> HomeScreenState.Loading()
        is RequestResult.Error -> HomeScreenState.Error()
    }
}

fun Tale.toTaleUiHome(): TaleUiHome {
    return TaleUiHome(
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
