package com.viktoriagavrosh.ui.viewmodel

import com.viktoriagavrosh.fairytales.fake.FakeSource
import com.viktoriagavrosh.ui.uiscreens.FairyTalesViewModel
import com.viktoriagavrosh.ui.uiscreens.FolkWorkType
import com.viktoriagavrosh.ui.uiscreens.toFolkWork
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
                folkWorkRepository = FakeFolkWorkRepository()
            )
            assertEquals(
                FakeSource().fakeListFolkWork[0].toFolkWork(),
                viewModel.uiState.value.selectedWork
            )
        }
    }

    @Test
    fun fairyTalesViewModel_updateCompositionType_successUpdateUiState() {
        val expectedFolkWork = FakeSource().fakeListFolkWork[1].toFolkWork()
        val expectedFolkWorkType = FolkWorkType.Game
        runTest {
            val viewModel = FairyTalesViewModel(
                folkWorkRepository = FakeFolkWorkRepository()
            )
            viewModel.updateCompositionType(expectedFolkWorkType)
            assertEquals(
                expectedFolkWorkType,
                viewModel.uiState.value.folkWorkType
            )
            assertEquals(
                expectedFolkWork,
                viewModel.uiState.value.selectedWork
            )
        }
    }

    @Test
    fun fairyTalesViewModel_updateCompositionType_updateUiStateWithEmptyList() {
        val expectedFolkWork = FakeSource().fakeListFolkWork[0].toFolkWork()
        val expectedFolkWorkType = FolkWorkType.Puzzle
        runTest {
            val viewModel = FairyTalesViewModel(
                folkWorkRepository = FakeFolkWorkRepository()
            )
            viewModel.updateCompositionType(expectedFolkWorkType)
            assertEquals(
                expectedFolkWorkType,
                viewModel.uiState.value.folkWorkType
            )
            assertEquals(
                expectedFolkWork,
                viewModel.uiState.value.selectedWork
            )
        }
    }

    @Test
    fun fairyTalesViewModel_updateCompositionType_successUpdateUiStateWithFavoriteList() {
        val newFolkWork = FakeSource().fakeListFolkWork[3].toFolkWork()
        val expectedFolkWorkType = FolkWorkType.Game
        runTest {
            val viewModel = FairyTalesViewModel(
                folkWorkRepository = FakeFolkWorkRepository()
            )
            viewModel.updateWorkFavorite(newFolkWork)
            viewModel.changeIsFavoriteWorks(true)
            viewModel.updateCompositionType(expectedFolkWorkType)
            val expectedFolkWork = newFolkWork.copy(isFavorite = true)
            assertEquals(
                expectedFolkWorkType,
                viewModel.uiState.value.folkWorkType
            )
            assertEquals(
                expectedFolkWork,
                viewModel.uiState.value.selectedWork
            )
        }
    }

    @Test
    fun fairyTalesViewModel_updateWorkFavorite_updateSelectedWork() {
        val folkWork = FakeSource().fakeListFolkWork[0].toFolkWork()
        runTest {
            val viewModel = FairyTalesViewModel(
                folkWorkRepository = FakeFolkWorkRepository()
            )
            viewModel.updateWorkFavorite(folkWork)
            val expectedFolkWork = folkWork.copy(isFavorite = true)
            assertEquals(
                expectedFolkWork,
                viewModel.uiState.value.selectedWork
            )
        }
    }

    @Test
    fun fairyTalesViewModel_updateWorkFavorite_doubleUpdateSelectedWork() {
        val folkWork = FakeSource().fakeListFolkWork[0].toFolkWork()
        runTest {
            val viewModel = FairyTalesViewModel(
                folkWorkRepository = FakeFolkWorkRepository()
            )
            viewModel.updateWorkFavorite(folkWork)
            viewModel.updateWorkFavorite(viewModel.uiState.value.selectedWork)
            assertEquals(
                folkWork,
                viewModel.uiState.value.selectedWork
            )
        }
    }

    @Test
    fun fairyTalesViewModel_updateListFavoriteWorks_UpdateUiStateIsFavoriteWorks() {
        runTest {
            val viewModel = FairyTalesViewModel(
                folkWorkRepository = FakeFolkWorkRepository()
            )
            viewModel.updateListFavoriteWorks()
            assertEquals(
                true,
                viewModel.uiState.value.isFavoriteWorks
            )
            viewModel.updateListFavoriteWorks()
            assertEquals(
                false,
                viewModel.uiState.value.isFavoriteWorks
            )
        }
    }
}
