package com.viktoriagavrosh.shelf

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viktoriagavrosh.repositories.ShelfRepository
import com.viktoriagavrosh.repositories.utils.ShelfGenre
import com.viktoriagavrosh.shelf.model.Book
import com.viktoriagavrosh.shelf.utils.Tabs
import com.viktoriagavrosh.shelf.utils.toBook
import com.viktoriagavrosh.uikit.utils.ScreenState
import com.viktoriagavrosh.uikit.utils.toScreenState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * ViewModel to retrieve and update item from the [ShelfRepository]'s data source
 */
@HiltViewModel(assistedFactory = ShelfViewModel.ShelfViewModelFactory::class)
class ShelfViewModel @AssistedInject constructor(
    @Assisted private val genre: ShelfGenre,
    private val shelfRepository: ShelfRepository
) : ViewModel() {

    private lateinit var _screenState: Flow<ScreenState<List<Book>>>

    internal lateinit var tabs: List<Tabs>

    init {
        val genre = genre
        updateScreenState(genre)
        initTabs(genre)
    }

    internal val screenState: StateFlow<ScreenState<List<Book>>> = _screenState
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            ScreenState.None()
        )

    /**
     * Update [ScreenState] for navigation between tabs
     */
    internal fun updateScreenState(genre: ShelfGenre) {
        _screenState = shelfRepository
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
            shelfRepository.updateFavoriteTale(book.id, !book.isFavorite)
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

    @AssistedFactory
    interface ShelfViewModelFactory {
        fun create(genre: ShelfGenre): ShelfViewModel
    }
}
