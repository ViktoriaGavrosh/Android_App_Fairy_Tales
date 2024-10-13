package com.viktoriagavrosh.addtale

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viktoriagavrosh.addtale.model.NewTale
import com.viktoriagavrosh.addtale.utils.TaleGenre
import com.viktoriagavrosh.addtale.utils.TaleGenre.Animal
import com.viktoriagavrosh.addtale.utils.TaleGenre.Fairy
import com.viktoriagavrosh.addtale.utils.TaleGenre.People
import com.viktoriagavrosh.addtale.utils.toTale
import com.viktoriagavrosh.repositories.AddRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel to retrieve, update and insert item to repository data source
 *
 * @param repository instance of [AddRepository]
 */
@HiltViewModel
class AddTaleViewModel @Inject constructor(
    private val repository: AddRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow(AddTaleUiState())
    internal val uiState: StateFlow<AddTaleUiState>
        get() = _uiState

    /**
     * Add new tale to data source
     */
    internal fun addTale() {
        viewModelScope.launch {
            val newTale = uiState.first().newTale
            repository.addTale(newTale.toTale())
        }
    }

    /**
     * Update title value of [AddTaleUiState]
     *
     * @param title new value
     */
    internal fun updateTitle(title: String) {
        viewModelScope.launch {
            val newTale = uiState.first().newTale.copy(
                title = title,
            )
            val isTaleValid = checkTaleValid(newTale)
            updateUiState(newTale, isTaleValid)
        }
    }

    /**
     * Update text value of [AddTaleUiState]
     *
     * @param text new value
     */
    internal fun updateText(text: String) {
        viewModelScope.launch {
            val newTale = uiState.first().newTale.copy(
                text = text,
            )
            val isTaleValid = checkTaleValid(newTale)
            updateUiState(newTale, isTaleValid)
        }
    }

    /**
     * Update genre value of [AddTaleUiState]
     *
     * @param genreTitle name of new genre
     */
    internal fun updateGenre(genreTitle: String) {
        val genre = getGenreByTitle(genreTitle)
        viewModelScope.launch {
            val newTale = uiState.first().newTale.copy(
                taleGenre = genre,
            )
            updateUiState(newTale)
        }
    }

    /**
     * Update isNight value of [AddTaleUiState]
     *
     * @param isNight new value
     */
    internal fun updateIsNight(isNight: Boolean) {
        viewModelScope.launch {
            val newTale = uiState.first().newTale.copy(
                isNight = isNight,
            )
            updateUiState(newTale)
        }
    }

    private fun updateUiState(newTale: NewTale, isTaleValid: Boolean? = null) {
        _uiState.update {
            if (isTaleValid == null) {
                it.copy(
                    newTale = newTale,
                )
            } else {
                it.copy(
                    newTale = newTale,
                    isTaleValid = isTaleValid,
                )
            }
        }
    }

    private fun checkTaleValid(newTale: NewTale): Boolean {
        return newTale.title.isNotEmpty() && newTale.text.isNotEmpty()
    }

    private fun getGenreByTitle(title: String): TaleGenre {
        return when (title) {
            Animal.title -> Animal
            Fairy.title -> Fairy
            People.title -> People
            else -> People
        }
    }
}

/**
 * holds [AddTaleScreen] state
 *
 * @param newTale instance [NewTale]
 * @param isError boolean parameter describes screen state. If true ErrorScreen will be shown.
 * @param isTaleValid if true, new tale can be saved.
 */
internal data class AddTaleUiState(
    val newTale: NewTale = NewTale(),
    val isError: Boolean = false,
    val isTaleValid: Boolean = false,
)
