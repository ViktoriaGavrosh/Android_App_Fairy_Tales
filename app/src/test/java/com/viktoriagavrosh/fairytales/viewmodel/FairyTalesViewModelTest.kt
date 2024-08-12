package com.viktoriagavrosh.fairytales.viewmodel

import com.viktoriagavrosh.fairytales.data.TaleType
import com.viktoriagavrosh.fairytales.fake.FakeSource
import com.viktoriagavrosh.fairytales.ui.FairyTalesViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class FairyTalesViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun fairyTalesViewModel_init_verifyFairyTalesUiState() {
        runTest {
            val viewModel = FairyTalesViewModel(
                taleRepository = FakeFolkWorkRepository()
            )
            assertEquals(
                FakeSource().fakeListFolkWork[0],
                viewModel.uiState.value.selectedTale
            )
        }
    }

    @Test
    fun fairyTalesViewModel_navigateToDetailScreen_updateUiState() {
        val expectedFolkWork = FakeSource().fakeListFolkWork[1]
        runTest {
            val viewModel = FairyTalesViewModel(
                taleRepository = FakeFolkWorkRepository()
            )
            viewModel.navigateToDetailScreen(expectedFolkWork)
            assertEquals(
                expectedFolkWork,
                viewModel.uiState.value.selectedTale
            )
            assertEquals(
                false,
                viewModel.uiState.value.isShowHomeScreen
            )
        }
    }

    @Test
    fun fairyTalesViewModel_navigateToHomeScreen_updateUiState() {
        runTest {
            val viewModel = FairyTalesViewModel(
                taleRepository = FakeFolkWorkRepository()
            )
            viewModel.navigateToHomeScreen()
            assertEquals(
                true,
                viewModel.uiState.value.isShowHomeScreen
            )
        }
    }

    @Test
    fun fairyTalesViewModel_updateCompositionType_successUpdateUiState() {
        val expectedFolkWork = FakeSource().fakeListFolkWork[1]
        val expectedFolkWorkType = TaleType.Game
        runTest {
            val viewModel = FairyTalesViewModel(
                taleRepository = FakeFolkWorkRepository()
            )
            viewModel.updateCompositionType(expectedFolkWorkType)
            assertEquals(
                expectedFolkWorkType,
                viewModel.uiState.value.taleType
            )
            assertEquals(
                expectedFolkWork,
                viewModel.uiState.value.selectedTale
            )
            assertEquals(
                true,
                viewModel.uiState.value.isShowHomeScreen
            )
        }
    }

    @Test
    fun fairyTalesViewModel_updateCompositionType_updateUiStateWithEmptyList() {
        val expectedFolkWork = FakeSource().fakeListFolkWork[0]
        val expectedFolkWorkType = TaleType.Puzzle
        runTest {
            val viewModel = FairyTalesViewModel(
                taleRepository = FakeFolkWorkRepository()
            )
            viewModel.updateCompositionType(expectedFolkWorkType)
            assertEquals(
                expectedFolkWorkType,
                viewModel.uiState.value.taleType
            )
            assertEquals(
                expectedFolkWork,
                viewModel.uiState.value.selectedTale
            )
            assertEquals(
                true,
                viewModel.uiState.value.isShowHomeScreen
            )
        }
    }

    @Test
    fun fairyTalesViewModel_updateCompositionType_successUpdateUiStateWithFavoriteList() {
        val newFolkWork = FakeSource().fakeListFolkWork[3]
        val expectedFolkWorkType = TaleType.Game
        runTest {
            val viewModel = FairyTalesViewModel(
                taleRepository = FakeFolkWorkRepository()
            )
            viewModel.updateTaleFavorite(newFolkWork)
            viewModel.changeIsFavoriteTales(true)
            viewModel.updateCompositionType(expectedFolkWorkType)
            val expectedFolkWork = newFolkWork.copy(isFavorite = true)
            assertEquals(
                expectedFolkWorkType,
                viewModel.uiState.value.taleType
            )
            assertEquals(
                expectedFolkWork,
                viewModel.uiState.value.selectedTale
            )
            assertEquals(
                true,
                viewModel.uiState.value.isShowHomeScreen
            )
        }
    }

    @Test
    fun fairyTalesViewModel_updateWorkFavorite_updateSelectedWork() {
        val folkWork = FakeSource().fakeListFolkWork[0]
        runTest {
            val viewModel = FairyTalesViewModel(
                taleRepository = FakeFolkWorkRepository()
            )
            viewModel.updateTaleFavorite(folkWork)
            val expectedFolkWork = folkWork.copy(isFavorite = true)
            assertEquals(
                expectedFolkWork,
                viewModel.uiState.value.selectedTale
            )
        }
    }

    @Test
    fun fairyTalesViewModel_updateWorkFavorite_doubleUpdateSelectedWork() {
        val folkWork = FakeSource().fakeListFolkWork[0]
        runTest {
            val viewModel = FairyTalesViewModel(
                taleRepository = FakeFolkWorkRepository()
            )
            viewModel.updateTaleFavorite(folkWork)
            viewModel.updateTaleFavorite(viewModel.uiState.value.selectedTale)
            assertEquals(
                folkWork,
                viewModel.uiState.value.selectedTale
            )
        }
    }

    @Test
    fun fairyTalesViewModel_updateListFavoriteWorks_UpdateUiStateIsFavoriteWorks() {
        runTest {
            val viewModel = FairyTalesViewModel(
                taleRepository = FakeFolkWorkRepository()
            )
            viewModel.updateListFavoriteTales()
            assertEquals(
                true,
                viewModel.uiState.value.isFavoriteTales
            )
            viewModel.updateListFavoriteTales()
            assertEquals(
                false,
                viewModel.uiState.value.isFavoriteTales
            )
        }
    }
}
