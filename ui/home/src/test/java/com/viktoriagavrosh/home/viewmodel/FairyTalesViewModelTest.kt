package com.viktoriagavrosh.home.viewmodel

import com.viktoriagavrosh.fairytales.model.FolkWork
import com.viktoriagavrosh.home.fake.FakeSource
import com.viktoriagavrosh.home.uiscreens.FairyTalesViewModel
import com.viktoriagavrosh.home.uiscreens.FolkWorkType
import com.viktoriagavrosh.home.uiscreens.toFolkWork
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
            val expectedList = FakeSource().fakeListFolkWork
                .map { it.toFolkWork() }
                .filter { it.genre == "story" }
            val actualList = viewModel.uiState.value.folkWorks
            assertEquals(
                expectedList,
                actualList
            )
            val expectedTypeName = "Story"
            val actualTypeName = viewModel.uiState.value.folkWorkType.name
            assertEquals(
                expectedTypeName,
                actualTypeName
            )
            assertEquals(
                false,
                viewModel.uiState.value.isFavoriteWorks
            )
        }
    }

    @Test
    fun fairyTalesViewModel_updateCompositionType_successUpdateUiState() {
        val expectedList = FakeSource().fakeListFolkWork
            .map { it.toFolkWork() }
            .filter { it.genre == "game" }
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
                expectedList,
                viewModel.uiState.value.folkWorks
            )
        }
    }

    @Test
    fun fairyTalesViewModel_updateCompositionType_updateUiStateWithEmptyList() {
        val expectedList = emptyList<FolkWork>()
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
                expectedList,
                viewModel.uiState.value.folkWorks
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
            val expectedList = listOf(newFolkWork.copy(isFavorite = true))
            assertEquals(
                expectedFolkWorkType,
                viewModel.uiState.value.folkWorkType
            )
            assertEquals(
                expectedList,
                viewModel.uiState.value.folkWorks
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

