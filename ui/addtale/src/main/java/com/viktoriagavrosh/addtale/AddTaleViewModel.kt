package com.viktoriagavrosh.addtale

import androidx.lifecycle.ViewModel
import com.viktoriagavrosh.addtale.model.NewTale
import com.viktoriagavrosh.repositories.AddRepository
import com.viktoriagavrosh.repositories.ShelfRepository
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * ViewModel to retrieve and update item from the [ShelfRepository]'s data source
 */
@HiltViewModel
class AddTaleViewModel @Inject constructor(
    private val repository: AddRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow(AddTaleUiState())

    init {

    }

    internal val uiState: StateFlow<AddTaleUiState>
        get() = _uiState
/*
    /**
     * Update [ScreenState] for navigation between tabs
     */
    internal fun updateScreenState(genre: ShelfGenre) {
        _uiState = repository
            .getItems(genre)
            .map { requestResult ->
                requestResult.toScreenState(
                    convertData = { list ->
                        list.map { it.toBook() }
                    }
                )
            }
    }

    /**
     * Update the value of an item field is_favorite in the data source
     */
    fun updateBookFavorite(book: Book) {
        viewModelScope.launch {
            repository.updateFavoriteTale(book.id, !book.isFavorite)
        }
        updateScreenState(book.genre)   // TODO check : uiState might update without it ???
    }

    private fun initTabs(genre: ShelfGenre) {
        tabs = when (genre) {
            in ShelfGenre.Folks.entries -> Tabs.FolkTab.entries
            in ShelfGenre.Tales.entries -> Tabs.TaleTab.entries
            else -> emptyList()
        }
    }

 */
}

internal data class AddTaleUiState(
    val newTale: NewTale = NewTale(),
    val isError: Boolean = false,
)
