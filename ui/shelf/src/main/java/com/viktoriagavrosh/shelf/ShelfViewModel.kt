package com.viktoriagavrosh.shelf

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viktoriagavrosh.repositories.ShelfRepository
import com.viktoriagavrosh.repositories.utils.RequestResult
import com.viktoriagavrosh.repositories.utils.ShelfGenre
import com.viktoriagavrosh.shelf.model.Book
import com.viktoriagavrosh.shelf.utils.Tabs
import com.viktoriagavrosh.shelf.utils.toBook
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel to update data to repository data source
 *
 * @param genre selected genre of books
 * @param repository instance of [ShelfRepository]
 */
@HiltViewModel(assistedFactory = ShelfViewModel.ShelfViewModelFactory::class)
class ShelfViewModel @AssistedInject constructor(
    @Assisted private val genre: ShelfGenre,
    private val repository: ShelfRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow(ShelfUiState())

    init {
        initScreenState(genre)
    }

    internal val uiState: StateFlow<ShelfUiState>
        get() = _uiState

    /**
     * Update [ShelfUiState]
     *
     * @param tab selected one of [Tabs]
     */
    internal fun updateScreenState(tab: Tabs) {
        val genre = tab.genre
        viewModelScope.launch {
            val requestResult = repository.getItems(genre).first()
            if (requestResult is RequestResult.Error) {
                _uiState.update {
                    it.copy(
                        isError = true,
                        selectedTab = tab,
                    )
                }
            } else {
                val books = requestResult.data?.map { it.toBook() } ?: emptyList()
                _uiState.update {
                    it.copy(
                        books = books,
                        selectedTab = tab,
                    )
                }
            }
        }
    }

    /**
     * Update isFavorite value of book in repository
     *
     * @param book item for updating
     */
    fun updateBookFavorite(book: Book) {
        viewModelScope.launch {
            repository.updateFavoriteTale(book.id, !book.isFavorite)
            val selectedTab = uiState.first().selectedTab
            updateScreenState(selectedTab)
        }
    }

    /**
     * init instance of [ShelfUiState]
     */
    internal fun initScreenState(genre: ShelfGenre) {
        viewModelScope.launch {
            val requestResult = repository.getItems(genre).first()
            if (requestResult is RequestResult.Error) {
                _uiState.update {
                    it.copy(
                        isError = true
                    )
                }
            } else {
                val books = requestResult.data?.map { it.toBook() } ?: emptyList()
                val selectedTab = genre.toTab()
                _uiState.update {
                    it.copy(
                        books = books,
                        selectedTab = selectedTab,
                    )
                }
            }
        }
    }

    @AssistedFactory
    interface ShelfViewModelFactory {
        fun create(genre: ShelfGenre): ShelfViewModel
    }
}

/**
 * holds [ShelfScreen] state
 *
 * @param books list of [Book]
 * @param selectedTab one of [Tabs], that is shown
 * @param isError boolean parameter describes screen state. If true ErrorScreen will be shown.
 */
internal data class ShelfUiState(
    val books: List<Book> = emptyList(),
    val selectedTab: Tabs = Tabs.TaleTab.Animal,
    val isError: Boolean = false,
)

private fun ShelfGenre.toTab(): Tabs {
    return when (this) {
        ShelfGenre.Tales.Animal -> Tabs.TaleTab.Animal
        ShelfGenre.Tales.Fairy -> Tabs.TaleTab.Fairy
        ShelfGenre.Tales.People -> Tabs.TaleTab.People
        ShelfGenre.Folks.Poem -> Tabs.FolkTab.Poem
        ShelfGenre.Folks.Counting -> Tabs.FolkTab.Counting
        ShelfGenre.Folks.Lullaby -> Tabs.FolkTab.Lullaby
        ShelfGenre.Favorites -> Tabs.Favorite
        ShelfGenre.Nights -> Tabs.Night
        ShelfGenre.Riddles -> Tabs.Riddle
    }

}
