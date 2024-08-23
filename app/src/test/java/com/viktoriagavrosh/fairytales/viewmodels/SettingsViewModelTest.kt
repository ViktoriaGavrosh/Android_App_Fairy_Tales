package com.viktoriagavrosh.fairytales.viewmodels

import com.viktoriagavrosh.fairytales.TestDispatcherRule
import com.viktoriagavrosh.fairytales.fake.FakeData
import com.viktoriagavrosh.fairytales.fake.FakeSettingsRepository
import com.viktoriagavrosh.fairytales.ui.settingsscreen.SettingsViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class SettingsViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun settingsViewModel_validTestSize_initSettingsStateSuccess() {
        runTest {
            val viewModel = SettingsViewModel(
                settingsRepository = FakeSettingsRepository()
            )
            val expectedTextSize = FakeData.fakeSettingsState.textSize
            val actualTextSize = viewModel.uiState.value.textSize
            assertEquals(expectedTextSize, actualTextSize)
        }
    }

    @Test
    fun settingsViewModel_notValidMinTestSize_initSettingsStateSuccess() {
        runTest {
            val fakeSettingsState = FakeData.fakeSettingsState.copy(textSize = -25.0F)
            val viewModel = SettingsViewModel(
                settingsRepository = FakeSettingsRepository(fakeSettingsState = fakeSettingsState)
            )
            val expectedTextSize = 8.0F
            val actualTextSize = viewModel.uiState.value.textSize
            assertEquals(expectedTextSize, actualTextSize)
        }
    }

    @Test
    fun settingsViewModel_notValidMaxTestSize_initSettingsStateSuccess() {
        runTest {
            val fakeSettingsState = FakeData.fakeSettingsState.copy(textSize = 150.0F)
            val viewModel = SettingsViewModel(
                settingsRepository = FakeSettingsRepository(fakeSettingsState = fakeSettingsState)
            )
            val expectedTextSize = 100.0F
            val actualTextSize = viewModel.uiState.value.textSize
            assertEquals(expectedTextSize, actualTextSize)
        }
    }

    @Test
    fun settingsViewModel_updateTestSize_textSizeUpdated() {
        runTest {
            val viewModel = SettingsViewModel(
                settingsRepository = FakeSettingsRepository()
            )
            val expectedTextSize = 100.0F
            viewModel.updateTextSize(expectedTextSize)
            val actualTextSize = viewModel.uiState.value.textSize
            assertEquals(expectedTextSize, actualTextSize)
        }
    }
}
