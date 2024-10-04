package com.viktoriagavrosh.settings

import com.viktoriagavrosh.settings.fake.FakeSettingsRepository
import com.viktoriagavrosh.settings.fake.FakeSource
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

internal class SettingsViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    internal fun settingsViewModel_initSettingsState_initTextSize() {
        runTest {
            val viewModel = SettingsViewModel(
                settingsRepository = FakeSettingsRepository(),
            )
            val expectedTextSize = FakeSource.fakeSettings.textSize
            val actualTextSize = viewModel.uiState.value.textSize
            assertEquals(expectedTextSize, actualTextSize)
        }
    }

    @Test
    internal fun settingsViewModel_updateTextSize_textSizeUpdated() {
        runTest {
            val viewModel = SettingsViewModel(FakeSettingsRepository())
            val expectedTextSize = 50.0F
            viewModel.updateTextSize(expectedTextSize)
            val actualTextSize = viewModel.uiState.value.textSize
            assertEquals(expectedTextSize, actualTextSize)
        }
    }

    @Test
    internal fun settingsViewModel_updateTextSizeWithSmallValue_textSizeUpdated() {
        runTest {
            val viewModel = SettingsViewModel(FakeSettingsRepository())
            val newTextSize = 1.0F
            viewModel.updateTextSize(newTextSize)
            val expectedTextSize = 8.0F
            val actualTextSize = viewModel.uiState.value.textSize
            assertEquals(expectedTextSize, actualTextSize)
        }
    }

    @Test
    internal fun settingsViewModel_updateTextSizeWithLargeValue_textSizeUpdated() {
        runTest {
            val viewModel = SettingsViewModel(FakeSettingsRepository())
            val newTextSize = 100.0F
            viewModel.updateTextSize(newTextSize)
            val expectedTextSize = 60.0F
            val actualTextSize = viewModel.uiState.value.textSize
            assertEquals(expectedTextSize, actualTextSize)
        }
    }
}
