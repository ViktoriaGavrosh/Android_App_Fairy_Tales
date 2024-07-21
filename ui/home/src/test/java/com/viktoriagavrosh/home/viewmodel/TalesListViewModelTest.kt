package com.viktoriagavrosh.home.viewmodel

import com.viktoriagavrosh.home.TalesListViewModel
import com.viktoriagavrosh.home.elements.TaleType
import com.viktoriagavrosh.home.fake.FakeSource
import com.viktoriagavrosh.home.model.TaleUiHome
import com.viktoriagavrosh.home.toTaleUiHome
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class TalesListViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()


    @Test
    fun fairyTalesViewModel_init_verifyFairyTalesUiState() {
        runTest {
            val viewModel = TalesListViewModel(
                taleRepository = FakeTaleRepository()
            )
            val expectedList = FakeSource().fakeListTales
                .map { it.toTaleUiHome() }
                .filter { it.genre == "story" }
            val actualList = viewModel.uiState.value.tales
            assertEquals(
                expectedList,
                actualList
            )
            val expectedTypeName = "Story"
            val actualTypeName = viewModel.uiState.value.taleType.name
            assertEquals(
                expectedTypeName,
                actualTypeName
            )
            assertEquals(
                false,
                viewModel.uiState.value.isFavoriteTalesList
            )
        }
    }

    @Test
    fun fairyTalesViewModel_updateCompositionType_successUpdateUiState() {
        val expectedList = FakeSource().fakeListTales
            .map { it.toTaleUiHome() }
            .filter { it.genre == "game" }
        val expectedTaleType = TaleType.Game
        runTest {
            val viewModel = TalesListViewModel(
                taleRepository = FakeTaleRepository()
            )
            viewModel.updateTaleType(expectedTaleType)
            assertEquals(
                expectedTaleType,
                viewModel.uiState.value.taleType
            )
            assertEquals(
                expectedList,
                viewModel.uiState.value.tales
            )
        }
    }

    @Test
    fun fairyTalesViewModel_updateCompositionType_updateUiStateWithEmptyList() {
        val expectedList = emptyList<TaleUiHome>()
        val expectedTaleType = TaleType.Puzzle
        runTest {
            val viewModel = TalesListViewModel(
                taleRepository = FakeTaleRepository()
            )
            viewModel.updateTaleType(expectedTaleType)
            assertEquals(
                expectedTaleType,
                viewModel.uiState.value.taleType
            )
            assertEquals(
                expectedList,
                viewModel.uiState.value.tales
            )
        }
    }

    @Test
    fun fairyTalesViewModel_updateCompositionType_successUpdateUiStateWithFavoriteList() {
        val newTale = FakeSource().fakeListTales[3].toTaleUiHome()
        val expectedTaleType = TaleType.Game
        runTest {
            val viewModel = TalesListViewModel(
                taleRepository = FakeTaleRepository()
            )
            viewModel.updateTaleFavorite(newTale)
            viewModel.changeIsFavoriteTalesList(true)
            viewModel.updateTaleType(expectedTaleType)
            val expectedList = listOf(newTale.copy(isFavorite = true))
            assertEquals(
                expectedTaleType,
                viewModel.uiState.value.taleType
            )
            assertEquals(
                expectedList,
                viewModel.uiState.value.tales
            )
        }
    }

    @Test
    fun fairyTalesViewModel_updateListFavoriteWorks_UpdateUiStateIsFavoriteWorks() {
        runTest {
            val viewModel = TalesListViewModel(
                taleRepository = FakeTaleRepository()
            )
            viewModel.updateFavoriteTalesList()
            assertEquals(
                true,
                viewModel.uiState.value.isFavoriteTalesList
            )
            viewModel.updateFavoriteTalesList()
            assertEquals(
                false,
                viewModel.uiState.value.isFavoriteTalesList
            )
        }
    }
}
