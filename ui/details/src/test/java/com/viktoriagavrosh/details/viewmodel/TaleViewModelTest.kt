package com.viktoriagavrosh.details.viewmodel

import com.viktoriagavrosh.details.TaleViewModel
import com.viktoriagavrosh.details.fake.FakeData
import com.viktoriagavrosh.details.model.TaleUiDetail
import com.viktoriagavrosh.details.utils.toTaleUiDetail
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

internal class TaleViewModelTest {
    @get:Rule
    val testDispatcher = TestDetailScreenDispatcherRule()

    @Test
    internal fun taleViewModel_init_initTaleSuccess() {
        runTest {
            val viewModel = TaleViewModel(
                taleId = 1,
                taleRepository = FakeTaleRepository(),
                settingsRepository = FakeSettingsRepository()
            )

            val expectedTale = FakeData.fakeTale.toTaleUiDetail()
            val actualTale = viewModel.tales.first().tale ?: TaleUiDetail()
            assertEquals(
                expectedTale,
                actualTale
            )
        }
    }
}
