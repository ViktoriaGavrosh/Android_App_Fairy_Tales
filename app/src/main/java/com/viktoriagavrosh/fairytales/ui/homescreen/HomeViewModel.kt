package com.viktoriagavrosh.fairytales.ui.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viktoriagavrosh.fairytales.data.repositories.TaleRepository
import com.viktoriagavrosh.fairytales.data.repositories.utils.toFlowRequestResultTalesUi
import com.viktoriagavrosh.fairytales.data.repositories.utils.toScreenState
import com.viktoriagavrosh.fairytales.model.TaleUi
import com.viktoriagavrosh.fairytales.ui.ScreenState
import com.viktoriagavrosh.fairytales.ui.elements.Genre
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
        .getTales(Genre.Story, _uiState.value.isFavoriteTalesShown)
        .toFlowRequestResultTalesUi()
        .map { it.toScreenState() }

    val screenState: Flow<ScreenState<List<TaleUi>>>
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
        updateGenre(Genre.Story)
    }


    /**
     * Update [TalesUiState] for navigation between tabs
     */
    fun updateGenre(genre: Genre) {
        _screenState = taleRepository.getTales(genre, _uiState.value.isFavoriteTalesShown)
            .toFlowRequestResultTalesUi()
            .map { it.toScreenState() }

        viewModelScope.launch {
            _uiState.update {
                it.copy(genre = genre)
            }
        }
    }

    /**
     * Update value of item field is_favorite in data source
     */
    fun updateTaleFavorite(tale: TaleUi) {
        val changedFavoriteState = !tale.isFavorite
        val taleType = _uiState.value.genre

        viewModelScope.launch {
            taleRepository.updateFavoriteTale(tale.id, changedFavoriteState)
        }
        updateGenre(taleType)
    }

    /**
     * Update [TalesUiState] for navigation ti Favorite list
     */
    fun updateFavoriteTalesList() {
        val newState = !_uiState.value.isFavoriteTalesShown
        changeIsFavoriteTalesShown(newState)
        updateGenre(_uiState.value.genre)
    }

    private fun changeIsFavoriteTalesShown(isFavorite: Boolean) {
        _uiState.update {
            it.copy(isFavoriteTalesShown = isFavorite)
        }
    }
}

/**
 * Holds the Ui state
 */
data class TalesUiState(
    val genre: Genre = Genre.Story,
    val isFavoriteTalesShown: Boolean = false,
)

