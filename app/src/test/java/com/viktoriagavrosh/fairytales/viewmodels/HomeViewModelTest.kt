package com.viktoriagavrosh.fairytales.viewmodels

import com.viktoriagavrosh.fairytales.TestDispatcherRule
import com.viktoriagavrosh.fairytales.data.repositories.utils.toTaleUi
import com.viktoriagavrosh.fairytales.fake.FakeData
import com.viktoriagavrosh.fairytales.fake.FakeTaleRepository
import com.viktoriagavrosh.fairytales.ui.ScreenState
import com.viktoriagavrosh.fairytales.ui.elements.Genre
import com.viktoriagavrosh.fairytales.ui.homescreen.HomeViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun homeViewModel_init_verifyUiState_TaleType() {
        runTest {
            val viewModel = HomeViewModel(taleRepository = FakeTaleRepository())

            val actualType = viewModel.uiState.value.genre
            assertEquals(Genre.Story, actualType)
        }
    }

    @Test
    fun homeViewModel_init_verifyUiState_IsFavoriteTaleList() {
        runTest {
            val viewModel = HomeViewModel(taleRepository = FakeTaleRepository())

            val actualValue = viewModel.uiState.value.isFavoriteTalesShown
            assertEquals(false, actualValue)
        }
    }

    @Test
    fun homeViewModel_init_verifyScreenState() {
        runTest {
            val viewModel = HomeViewModel(taleRepository = FakeTaleRepository())

            val actual = viewModel.screenState.first() is ScreenState.Success
            assert(actual)
        }
    }

    @Test
    fun homeViewModel_updateGenre_updateUiStateTaleType() {
        runTest {
            val expectedGenre = Genre.Puzzle
            val viewModel = HomeViewModel(taleRepository = FakeTaleRepository())
            viewModel.updateGenre(expectedGenre)
            val actualGenre = viewModel.uiState.value.genre
            assertEquals(expectedGenre, actualGenre)
        }
    }

    @Test
    fun homeViewModel_updateGenre_updateScreenStateTales() {
        runTest {
            val newGenre = Genre.Puzzle
            val viewModel = HomeViewModel(taleRepository = FakeTaleRepository())
            val expectedTales = FakeData.fakeListTales
                .filter { it.genre == Genre.Puzzle }
                .map { it.toTaleUi() }
            viewModel.updateGenre(newGenre)
            val actualTales = viewModel.screenState.first().data ?: emptyList()
            assertEquals(expectedTales, actualTales)
        }
    }

    @Test
    fun homeViewModel_updateFavoriteTalesList_updateUiStateIsFavoriteTalesList() {
        runTest {
            val viewModel = HomeViewModel(taleRepository = FakeTaleRepository())

            viewModel.updateFavoriteTalesList()
            val actualValue = viewModel.uiState.value.isFavoriteTalesShown
            assertEquals(true, actualValue)
        }
    }

    @Test
    fun homeViewModel_updateTaleFavorite_updateUiStateListFavorite() {
        runTest {
            val viewModel = HomeViewModel(taleRepository = FakeTaleRepository())
            val expectedFavoriteList = listOf(
                FakeData.fakeListTales[1].copy(isFavorite = true).toTaleUi()
            )
            viewModel.updateGenre(Genre.Puzzle)
            viewModel.updateFavoriteTalesList()
            viewModel.updateTaleFavorite(FakeData.fakeListTales[1].toTaleUi())
            val actualFavoriteTales = viewModel.screenState.first().data ?: emptyList()
            assertEquals(expectedFavoriteList, actualFavoriteTales)
        }
    }
}
