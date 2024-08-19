package com.viktoriagavrosh.fairytales.ui.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viktoriagavrosh.fairytales.data.repositories.TaleRepository
import com.viktoriagavrosh.fairytales.data.repositories.utils.toHomeScreenState
import com.viktoriagavrosh.fairytales.model.TaleUi
import com.viktoriagavrosh.fairytales.ui.elements.TaleType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel to retrieve and update items from [TaleRepository]`s for [HomeScreen]
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val taleRepository: TaleRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(TalesUiState())
    val uiState: StateFlow<TalesUiState> = _uiState

    private var _screenState = taleRepository
        .getTales(getGenre(TaleType.Story), _uiState.value.isFavoriteTalesList)
        .map { it.toHomeScreenState() }

    val screenState: Flow<HomeScreenState>
        get() = _screenState
    /*
    TODO  check where resource consumption is less
    .stateIn(
    viewModelScope,
    SharingStarted.Lazily,
    HomeScreenState.None()
    )
     */

    init {
        updateTaleType(TaleType.Story)
    }

    /**
     * Update [TalesUiState] for navigation between tabs
     */
    fun updateTaleType(taleType: TaleType) {
        val genre = getGenre(taleType)
        _screenState = taleRepository.getTales(genre, _uiState.value.isFavoriteTalesList)
            .map { it.toHomeScreenState() }

        viewModelScope.launch {
            _uiState.update {
                it.copy(taleType = taleType)
            }
        }
    }

    /**
     * Update value of item field is_favorite in data source
     */
    fun updateTaleFavorite(tale: TaleUi) {
        val changedFavoriteState = !tale.isFavorite
        val taleType = _uiState.value.taleType

        viewModelScope.launch {
            taleRepository.updateFavoriteTale(tale.id, changedFavoriteState)
        }
        updateTaleType(taleType)
    }

    /**
     * Update [TalesUiState] for navigation ti Favorite list
     */
    fun updateFavoriteTalesList() {
        val newState = !_uiState.value.isFavoriteTalesList
        changeIsFavoriteTalesList(newState)
        val taleType = _uiState.value.taleType
        updateTaleType(taleType)
    }

    private fun changeIsFavoriteTalesList(isFavorite: Boolean) {
        _uiState.update {
            it.copy(isFavoriteTalesList = isFavorite)
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
 * Holds the Ui state
 */
data class TalesUiState(
    val taleType: TaleType = TaleType.Story,
    val isFavoriteTalesList: Boolean = false,
)

sealed class HomeScreenState(val tales: List<TaleUi>? = null) {
    class None : HomeScreenState()
    class Success(tales: List<TaleUi>) : HomeScreenState(tales)
    class Error : HomeScreenState()
}
