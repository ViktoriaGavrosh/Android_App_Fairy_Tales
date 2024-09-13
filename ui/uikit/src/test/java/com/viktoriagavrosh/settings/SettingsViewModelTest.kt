package com.viktoriagavrosh.settings

import com.viktoriagavrosh.settings.fake.FakeSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description

internal class SettingsViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    internal fun settingsViewModel_validTextSize_initSettingsStateSuccess() {
        runTest {
            val fakeSettingsState = FakeSource.fakeSettingsState
            val viewModel = SettingsViewModel(
                settingsRepository = FakeSettingsRepository(fakeSettingsState),
            )

            val expectedTextSize = FakeSource.fakeSettingsState.textSize
            val actualTextSize = viewModel.uiState.value.textSize
            assertEquals(
                expectedTextSize,
                actualTextSize
            )
        }
    }

    @Test
    internal fun settingsViewModel_notValidMinTextSize_initSettingsStateSuccess() {
        runTest {
            val fakeSettingsState = FakeSource.fakeSettingsState.copy(textSize = -25.0F)
            val viewModel = SettingsViewModel(
                settingsRepository = FakeSettingsRepository(fakeSettingsState),
            )

            val expectedTextSize = 8.0F
            val actualTextSize = viewModel.uiState.value.textSize
            assertEquals(
                expectedTextSize,
                actualTextSize
            )
        }
    }

    @Test
    internal fun settingsViewModel_notValidMaxTextSize_initSettingsStateSuccess() {
        runTest {
            val fakeSettingsState = FakeSource.fakeSettingsState.copy(textSize = 150.0F)
            val viewModel = SettingsViewModel(
                settingsRepository = FakeSettingsRepository(fakeSettingsState),
            )

            val expectedTextSize = 100.0F
            val actualTextSize = viewModel.uiState.value.textSize
            assertEquals(
                expectedTextSize,
                actualTextSize
            )
        }
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
internal class TestDispatcherRule(
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
) : TestWatcher() {
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}
