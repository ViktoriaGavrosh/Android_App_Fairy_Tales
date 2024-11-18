package com.viktoriagavrosh.startmenu

import com.viktoriagavrosh.startmenu.fake.FakeMenuRepository
import com.viktoriagavrosh.startmenu.fake.FakeSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Rule
import org.junit.Test

class StartViewModelTest {
    @get:Rule
    internal val testDispatcher = TestDispatcherRule()

    @Test
    fun startViewModel_initStartUiState_verifyLastTaleId() {
        runTest {
            val viewModel = StartViewModel(FakeMenuRepository())
            val expectedId = FakeSource.fakeLastTaleId
            val actualId = viewModel.uiState.first().lastTaleId
            assertEquals(expectedId, actualId)
        }
    }

    @Test
    fun startViewModel_initStartUiState_verifyRandomTaleId() {
        runTest {
            val viewModel = StartViewModel(FakeMenuRepository())
            val randomId = viewModel.uiState.first().randomTaleId
            assert(randomId in FakeSource.fakeTales.map { it.id })
        }
    }

    @Test
    fun startViewModel_updateLastTaleId_uiStateUpdated() {
        runTest {
            val viewModel = StartViewModel(FakeMenuRepository())
            val oldId = viewModel.uiState.first().lastTaleId
            viewModel.updateLastTale()
            val newId = viewModel.uiState.first().lastTaleId
            assertNotEquals(oldId, newId)
        }
    }

    // test sometimes crashes because of random
    @Test
    fun startViewModel_updateRandomTaleId_uiStateUpdated() {
        runTest {
            val viewModel = StartViewModel(FakeMenuRepository())
            val oldId = viewModel.uiState.first().randomTaleId
            viewModel.updateRandomTale()
            val newId = viewModel.uiState.first().randomTaleId
            assertNotEquals(oldId, newId)
        }
    }
}
