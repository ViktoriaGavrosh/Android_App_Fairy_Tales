package com.viktoriagavrosh.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viktoriagavrosh.home.elements.Genre
import com.viktoriagavrosh.home.model.TaleUiHome
import com.viktoriagavrosh.home.utils.toHomeScreenState
import com.viktoriagavrosh.repositories.tale.TaleRepository
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
        .getTales(Genre.Story.name.lowercase(), _uiState.value.isFavoriteTalesShown)  //TODO 111
        .map { it.toHomeScreenState() }

    internal val screenState: Flow<HomeScreenState>
        get() = _screenState
    /* TODO check where resource consumption is less
    .stateIn(
    viewModelScope,
    SharingStarted.Lazily,
    HomeScreenState.None()
)
     */

    init {
        updateGenre(Genre.Story)
    }

    /**
     * Update [TalesListUiState] for navigation between tabs
     */
    fun updateGenre(genre: Genre) {
        _screenState = taleRepository
            .getTales(genre.name.lowercase(), _uiState.value.isFavoriteTalesShown)   // TODO 111
            .map { it.toHomeScreenState() }

        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    genre = genre,
                )
            }
        }
    }

    /**
     * Update the value of an item field is_favorite in the data source
     */
    fun updateTaleFavorite(tale: TaleUiHome) {
        val changedFavoriteState = !tale.isFavorite
        val genre = _uiState.value.genre
        viewModelScope.launch {
            taleRepository.updateFavoriteTale(tale.id, changedFavoriteState)
        }
        updateGenre(genre)
    }

    /**
     * Update [TalesListUiState] for navigation to Favorite List
     */
    fun updateFavoriteTalesList() {
        val newState = !_uiState.value.isFavoriteTalesShown
        changeIsFavoriteTalesShown(newState)
        val genre = _uiState.value.genre
        updateGenre(genre)
    }

    private fun changeIsFavoriteTalesShown(isFavorite: Boolean) {
        _uiState.update {
            it.copy(
                isFavoriteTalesShown = isFavorite
            )
        }
    }
}

/**
 * Holds the UI state.
 */
data class TalesListUiState(
    val genre: Genre = Genre.Story,
    val isFavoriteTalesShown: Boolean = false
)

internal sealed class HomeScreenState(val tales: List<TaleUiHome>? = null) {
    class None : HomeScreenState()
    class Success(tales: List<TaleUiHome>) : HomeScreenState(tales)
    class Error : HomeScreenState()
}
