package com.viktoriagavrosh.home.viewmodel

import com.viktoriagavrosh.home.HomeScreenState
import com.viktoriagavrosh.home.TalesListViewModel
import com.viktoriagavrosh.home.elements.Genre
import com.viktoriagavrosh.home.fake.FakeSource
import com.viktoriagavrosh.home.utils.toTaleUiHome
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class TalesListViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun talesListViewModel_init_verifyUiState_TaleType() {
        runTest {
            val viewModel = TalesListViewModel(taleRepository = FakeTaleRepository())

            val actualType = viewModel.uiState.value.genre
            assertEquals(Genre.Story, actualType)
        }
    }

    @Test
    fun homeViewModel_init_verifyUiState_IsFavoriteTaleList() {
        runTest {
            val viewModel = TalesListViewModel(taleRepository = FakeTaleRepository())

            val actualValue = viewModel.uiState.value.isFavoriteTalesShown
            assertEquals(false, actualValue)
        }
    }

    @Test
    fun homeViewModel_init_verifyScreenState() {
        runTest {
            val viewModel = TalesListViewModel(taleRepository = FakeTaleRepository())

            val actual = viewModel.screenState.first() is HomeScreenState.Success
            assert(actual)
        }
    }

    @Test
    fun homeViewModel_updateGenre_updateUiStateTaleType() {
        runTest {
            val expectedGenre = Genre.Puzzle
            val viewModel = TalesListViewModel(taleRepository = FakeTaleRepository())
            viewModel.updateGenre(expectedGenre)
            val actualGenre = viewModel.uiState.value.genre
            assertEquals(expectedGenre, actualGenre)
        }
    }

    @Test
    fun homeViewModel_updateGenre_updateScreenStateTales() {
        runTest {
            val newGenre = Genre.Puzzle
            val viewModel = TalesListViewModel(taleRepository = FakeTaleRepository())
            val expectedTales = FakeSource().fakeListTales
                .filter { it.genre == Genre.Puzzle.name.lowercase() }
                .map { it.toTaleUiHome() }
            viewModel.updateGenre(newGenre)
            val actualTales = viewModel.screenState.first().tales ?: emptyList()
            assertEquals(expectedTales, actualTales)
        }
    }

    @Test
    fun homeViewModel_updateFavoriteTalesList_updateUiStateIsFavoriteTalesList() {
        runTest {
            val viewModel = TalesListViewModel(taleRepository = FakeTaleRepository())

            viewModel.updateFavoriteTalesList()
            val actualValue = viewModel.uiState.value.isFavoriteTalesShown
            assertEquals(true, actualValue)
        }
    }

    /*   TODO 111
    @Test
    fun homeViewModel_updateTaleFavorite_updateUiStateListFavorite() {
        runTest {
            val viewModel = TalesListViewModel(taleRepository = FakeTaleRepository())
            val expectedFavoriteList = listOf(
                FakeSource().fakeListTales[1].copy(isFavorite = true).toTaleUiHome()
            )
            viewModel.updateGenre(Genre.Puzzle)
            viewModel.updateFavoriteTalesList()
            viewModel.updateTaleFavorite(FakeSource().fakeListTales[1].toTaleUiHome())
            val actualFavoriteTales = viewModel.screenState.first().tales ?: emptyList()
            assertEquals(expectedFavoriteList, actualFavoriteTales)
        }
    }

     */
}
