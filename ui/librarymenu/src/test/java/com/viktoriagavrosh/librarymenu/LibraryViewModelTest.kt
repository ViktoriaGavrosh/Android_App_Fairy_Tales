package com.viktoriagavrosh.librarymenu

import com.viktoriagavrosh.librarymenu.fake.FakeMenuRepository
import com.viktoriagavrosh.librarymenu.fake.FakeSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNotEquals
import org.junit.Rule
import org.junit.Test

class LibraryViewModelTest {
    @get:Rule
    internal val testDispatcher = TestDispatcherRule()

    @Test
    fun libraryViewModel_initLibraryUiState_verifyRandomTaleId() {
        runTest {
            val viewModel = LibraryViewModel(FakeMenuRepository())
            val randomTaleId = viewModel.uiState.first().randomTaleId
            assert(randomTaleId in FakeSource.fakeTales.map { it.id })
        }
    }

    @Test
    fun libraryViewModel_updateRandomTale_uiStateUpdated() {
        runTest {
            val viewModel = LibraryViewModel(FakeMenuRepository())
            val oldId = viewModel.uiState.first().randomTaleId
            viewModel.updateRandomTale()
            val newId = viewModel.uiState.first().randomTaleId
            assertNotEquals(oldId, newId)
        }
    }


    @Test
    fun startViewModel_updateLastTaleId_uiStateUpdated() {
        runTest {
            val repository = FakeMenuRepository()
            val viewModel = LibraryViewModel(repository)
            val oldId = repository.getLastTaleId().first().data ?: 0
            viewModel.updateLastTale()
            val newId = repository.getLastTaleId().first().data ?: 0
            assertNotEquals(oldId, newId)
        }
    }
}
