package com.viktoriagavrosh.infomenu

import com.viktoriagavrosh.infomenu.fake.FakeMenuRepository
import com.viktoriagavrosh.infomenu.fake.FakeSource
import com.viktoriagavrosh.infomenu.utils.toTaleInfo
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class InfoViewModelTest {
    @get:Rule
    internal val testDispatcher = TestDispatcherRule()

    @Test
    fun infoViewModel_updateUiState_taleUpdated() {
        runTest {
            val viewModel = InfoViewModel(taleId = 1, FakeMenuRepository())
            val expectedTale = FakeSource.fakeTales[0].toTaleInfo()
            val actualTale = viewModel.uiState.first().tale
            assertEquals(expectedTale, actualTale)
        }
    }

    @Test
    fun infoViewModel_updateUiState_isErrorUpdated() {
        runTest {
            val viewModel = InfoViewModel(taleId = 10, FakeMenuRepository())
            val isErrorValue = viewModel.uiState.first().isError
            assertEquals(true, isErrorValue)
        }
    }
}
