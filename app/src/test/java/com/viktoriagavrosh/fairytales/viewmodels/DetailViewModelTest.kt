package com.viktoriagavrosh.fairytales.viewmodels

import com.viktoriagavrosh.fairytales.TestDispatcherRule
import com.viktoriagavrosh.fairytales.data.repositories.utils.toTaleUi
import com.viktoriagavrosh.fairytales.fake.FakeData
import com.viktoriagavrosh.fairytales.fake.FakeSettingsRepository
import com.viktoriagavrosh.fairytales.fake.FakeTaleRepository
import com.viktoriagavrosh.fairytales.model.TaleUi
import com.viktoriagavrosh.fairytales.ui.detailscreen.DetailViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class DetailViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun detailViewModel_init_initTaleSuccess() {
        runTest {
            val viewModel = DetailViewModel(
                taleId = 0,
                taleRepository = FakeTaleRepository(),
                settingsRepository = FakeSettingsRepository()
            )

            val expectedTale = FakeData.fakeListTales.first().toTaleUi()
            val actualTale = viewModel.tales.first().data ?: TaleUi()
            assertEquals(expectedTale, actualTale)
        }
    }

    @Test
    fun detailViewModel_init_initTextSizeSuccess() {
        runTest {
            val viewModel = DetailViewModel(
                taleId = 0,
                taleRepository = FakeTaleRepository(),
                settingsRepository = FakeSettingsRepository()
            )

            val expectedTextSize = FakeData.fakeSettingsState.textSize
            val actualTextSize = viewModel.textSize.first()
            assertEquals(expectedTextSize, actualTextSize)
        }
    }

    @Test
    fun detailViewModel_updateTestSize_textSizeUpdated() {
        runTest {
            val viewModel = DetailViewModel(
                taleId = 0,
                taleRepository = FakeTaleRepository(),
                settingsRepository = FakeSettingsRepository()
            )
            val expectedTextSize = 70.0F
            viewModel.updateTextSize(expectedTextSize)
            val actualTextSize = viewModel.textSize.first()
            assertEquals(expectedTextSize, actualTextSize)
        }
    }
}
