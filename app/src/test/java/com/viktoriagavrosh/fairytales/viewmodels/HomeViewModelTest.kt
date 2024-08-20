package com.viktoriagavrosh.fairytales.viewmodels

import com.viktoriagavrosh.fairytales.TestDispatcherRule
import com.viktoriagavrosh.fairytales.data.repositories.utils.toTaleUi
import com.viktoriagavrosh.fairytales.fake.FakeData
import com.viktoriagavrosh.fairytales.fake.FakeTaleRepository
import com.viktoriagavrosh.fairytales.ui.elements.Genre
import com.viktoriagavrosh.fairytales.ui.homescreen.HomeScreenState
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

            val actual = viewModel.screenState.first() is HomeScreenState.Success
            assert(actual)
        }
    }

    @Test
    fun homeViewModel_updateTaleType_updateUiStateTaleType() {
        runTest {
            val expectedType = Genre.Puzzle
            val viewModel = HomeViewModel(taleRepository = FakeTaleRepository())
            viewModel.updateGenre(expectedType)
            val actualType = viewModel.uiState.value.genre
            assertEquals(expectedType, actualType)
        }
    }

    @Test
    fun homeViewModel_updateTaleType_updateScreenStateTales() {
        runTest {
            val newType = Genre.Puzzle
            val viewModel = HomeViewModel(taleRepository = FakeTaleRepository())
            val expectedTales = FakeData.fakeListTales
                .filter { it.genre == Genre.Puzzle }
                .map { it.toTaleUi() }
            viewModel.updateGenre(newType)
            val actualTales = viewModel.screenState.first().tales ?: emptyList()
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
}
