package com.viktoriagavrosh.home.viewmodel

import com.viktoriagavrosh.home.fake.FakeShelfRepository
import com.viktoriagavrosh.home.fake.FakeSource
import com.viktoriagavrosh.repositories.utils.ShelfGenre
import com.viktoriagavrosh.shelf.ShelfUiState
import com.viktoriagavrosh.shelf.ShelfViewModel
import com.viktoriagavrosh.shelf.utils.Tabs
import com.viktoriagavrosh.shelf.utils.toBook
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class ShelfViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun shelfViewModel_init_verifyUiStateTalesAnimal() {
        runTest {
            val viewModel = ShelfViewModel(ShelfGenre.Tales.Animal, FakeShelfRepository())
            val expectedState = ShelfUiState(
                books = FakeSource.fakeTales
                    .filter { it.genre == ShelfGenre.Tales.Animal }
                    .map { it.toBook() },
                selectedTab = Tabs.TaleTab.Animal,
            )
            val actualState = viewModel.uiState.first()
            assertEquals(expectedState, actualState)
        }
    }

    @Test
    fun shelfViewModel_init_verifyUiStateTalesFairy() {
        runTest {
            val viewModel = ShelfViewModel(ShelfGenre.Tales.Fairy, FakeShelfRepository())
            val expectedState = ShelfUiState(
                books = FakeSource.fakeTales
                    .filter { it.genre == ShelfGenre.Tales.Fairy }
                    .map { it.toBook() },
                selectedTab = Tabs.TaleTab.Fairy,
            )
            val actualState = viewModel.uiState.first()
            assertEquals(expectedState, actualState)
        }
    }

    @Test
    fun shelfViewModel_init_verifyUiStateTalesPeople() {
        runTest {
            val viewModel = ShelfViewModel(ShelfGenre.Tales.People, FakeShelfRepository())
            val expectedState = ShelfUiState(
                books = FakeSource.fakeTales
                    .filter { it.genre == ShelfGenre.Tales.People }
                    .map { it.toBook() },
                selectedTab = Tabs.TaleTab.People,
            )
            val actualState = viewModel.uiState.first()
            assertEquals(expectedState, actualState)
        }
    }

    @Test
    fun shelfViewModel_init_verifyUiStateFolksPoem() {
        runTest {
            val viewModel = ShelfViewModel(ShelfGenre.Folks.Poem, FakeShelfRepository())
            val expectedState = ShelfUiState(
                books = FakeSource.fakeFolks
                    .filter { it.genre == ShelfGenre.Folks.Poem }
                    .map { it.toBook() },
                selectedTab = Tabs.FolkTab.Poem,
            )
            val actualState = viewModel.uiState.first()
            assertEquals(expectedState, actualState)
        }
    }

    @Test
    fun shelfViewModel_init_verifyUiStateFolksLullaby() {
        runTest {
            val viewModel = ShelfViewModel(ShelfGenre.Folks.Lullaby, FakeShelfRepository())
            val expectedState = ShelfUiState(
                books = FakeSource.fakeFolks
                    .filter { it.genre == ShelfGenre.Folks.Lullaby }
                    .map { it.toBook() },
                selectedTab = Tabs.FolkTab.Lullaby,
            )
            val actualState = viewModel.uiState.first()
            assertEquals(expectedState, actualState)
        }
    }

    @Test
    fun shelfViewModel_init_verifyUiStateFolksCounting() {
        runTest {
            val viewModel = ShelfViewModel(ShelfGenre.Folks.Counting, FakeShelfRepository())
            val expectedState = ShelfUiState(
                books = FakeSource.fakeFolks
                    .filter { it.genre == ShelfGenre.Folks.Counting }
                    .map { it.toBook() },
                selectedTab = Tabs.FolkTab.Counting,
            )
            val actualState = viewModel.uiState.first()
            assertEquals(expectedState, actualState)
        }
    }

    @Test
    fun shelfViewModel_init_verifyUiStateNightTale() {
        runTest {
            val viewModel = ShelfViewModel(ShelfGenre.Nights, FakeShelfRepository())
            val expectedState = ShelfUiState(
                books = FakeSource.fakeTales
                    .filter { it.isNight }
                    .map { it.toBook() },
                selectedTab = Tabs.Night,
            )
            val actualState = viewModel.uiState.first()
            assertEquals(expectedState, actualState)
        }
    }

    @Test
    fun shelfViewModel_init_verifyUiStateFavoriteTale() {
        runTest {
            val viewModel = ShelfViewModel(ShelfGenre.Favorites, FakeShelfRepository())
            val expectedState = ShelfUiState(
                books = FakeSource.fakeTales
                    .filter { it.isFavorite }
                    .map { it.toBook() },
                selectedTab = Tabs.Favorite,
            )
            val actualState = viewModel.uiState.first()
            assertEquals(expectedState, actualState)
        }
    }

    @Test
    fun shelfViewModel_init_verifyUiStateRiddles() {
        runTest {
            val viewModel = ShelfViewModel(ShelfGenre.Riddles, FakeShelfRepository())
            val expectedState = ShelfUiState(
                books = FakeSource.fakeRiddles
                    .map { it.toBook() },
                selectedTab = Tabs.Riddle,
            )
            val actualState = viewModel.uiState.first()
            assertEquals(expectedState, actualState)
        }
    }

    @Test
    fun shelfViewModel_updateScreenState_taleAnimalBooksUpdated() {
        runTest {
            val viewModel = ShelfViewModel(ShelfGenre.Tales.Fairy, FakeShelfRepository())
            val expectedBooks = FakeSource.fakeTales.filter { it.genre == ShelfGenre.Tales.Animal }
                .map { it.toBook() }
            viewModel.updateScreenState(Tabs.TaleTab.Animal)
            val actualBooks = viewModel.uiState.first().books
            assertEquals(expectedBooks, actualBooks)
        }
    }

    @Test
    fun shelfViewModel_updateScreenState_taleFairyBooksUpdated() {
        runTest {
            val viewModel = ShelfViewModel(ShelfGenre.Tales.Animal, FakeShelfRepository())
            val expectedBooks = FakeSource.fakeTales.filter { it.genre == ShelfGenre.Tales.Fairy }
                .map { it.toBook() }
            viewModel.updateScreenState(Tabs.TaleTab.Fairy)
            val actualBooks = viewModel.uiState.first().books
            assertEquals(expectedBooks, actualBooks)
        }
    }

    @Test
    fun shelfViewModel_updateScreenState_talePeopleBooksUpdated() {
        runTest {
            val viewModel = ShelfViewModel(ShelfGenre.Tales.Fairy, FakeShelfRepository())
            val expectedBooks = FakeSource.fakeTales.filter { it.genre == ShelfGenre.Tales.People }
                .map { it.toBook() }
            viewModel.updateScreenState(Tabs.TaleTab.People)
            val actualBooks = viewModel.uiState.first().books
            assertEquals(expectedBooks, actualBooks)
        }
    }

    @Test
    fun shelfViewModel_updateScreenState_favoriteTaleBooksUpdated() {
        runTest {
            val viewModel = ShelfViewModel(ShelfGenre.Tales.Fairy, FakeShelfRepository())
            val expectedBooks = FakeSource.fakeTales.filter { it.isFavorite }
                .map { it.toBook() }
            viewModel.updateScreenState(Tabs.Favorite)
            val actualBooks = viewModel.uiState.first().books
            assertEquals(expectedBooks, actualBooks)
        }
    }

    @Test
    fun shelfViewModel_updateScreenState_nightTaleBooksUpdated() {
        runTest {
            val viewModel = ShelfViewModel(ShelfGenre.Tales.Fairy, FakeShelfRepository())
            val expectedBooks = FakeSource.fakeTales.filter { it.isNight }
                .map { it.toBook() }
            viewModel.updateScreenState(Tabs.Night)
            val actualBooks = viewModel.uiState.first().books
            assertEquals(expectedBooks, actualBooks)
        }
    }

    @Test
    fun shelfViewModel_updateScreenState_poemFolkBooksUpdated() {
        runTest {
            val viewModel = ShelfViewModel(ShelfGenre.Tales.Fairy, FakeShelfRepository())
            val expectedBooks = FakeSource.fakeFolks.filter { it.genre == ShelfGenre.Folks.Poem }
                .map { it.toBook() }
            viewModel.updateScreenState(Tabs.FolkTab.Poem)
            val actualBooks = viewModel.uiState.first().books
            assertEquals(expectedBooks, actualBooks)
        }
    }

    @Test
    fun shelfViewModel_updateScreenState_countingFolkBooksUpdated() {
        runTest {
            val viewModel = ShelfViewModel(ShelfGenre.Tales.Fairy, FakeShelfRepository())
            val expectedBooks =
                FakeSource.fakeFolks.filter { it.genre == ShelfGenre.Folks.Counting }
                    .map { it.toBook() }
            viewModel.updateScreenState(Tabs.FolkTab.Counting)
            val actualBooks = viewModel.uiState.first().books
            assertEquals(expectedBooks, actualBooks)
        }
    }

    @Test
    fun shelfViewModel_updateScreenState_lullabyFolkBooksUpdated() {
        runTest {
            val viewModel = ShelfViewModel(ShelfGenre.Tales.Fairy, FakeShelfRepository())
            val expectedBooks = FakeSource.fakeFolks.filter { it.genre == ShelfGenre.Folks.Lullaby }
                .map { it.toBook() }
            viewModel.updateScreenState(Tabs.FolkTab.Lullaby)
            val actualBooks = viewModel.uiState.first().books
            assertEquals(expectedBooks, actualBooks)
        }
    }

    @Test
    fun shelfViewModel_updateScreenState_riddlesBooksUpdated() {
        runTest {
            val viewModel = ShelfViewModel(ShelfGenre.Tales.Fairy, FakeShelfRepository())
            val expectedBooks = FakeSource.fakeRiddles
                .map { it.toBook() }
            viewModel.updateScreenState(Tabs.Riddle)
            val actualBooks = viewModel.uiState.first().books
            assertEquals(expectedBooks, actualBooks)
        }
    }

    @Test
    fun shelfViewModel_updateBookFavorite_isFavoriteValueUpdated() {
        runTest {
            val viewModel = ShelfViewModel(ShelfGenre.Tales.Fairy, FakeShelfRepository())
            val book = FakeSource.fakeTales[1].toBook()
            viewModel.updateBookFavorite(book)
            viewModel.updateScreenState(Tabs.Favorite)
            val expectedBook = book.copy(isFavorite = true)
            val actualBooks = viewModel.uiState.first().books
            assert(expectedBook in actualBooks)
        }
    }
}
