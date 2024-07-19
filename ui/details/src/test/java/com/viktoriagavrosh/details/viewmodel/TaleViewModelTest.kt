package com.viktoriagavrosh.details.viewmodel

import com.viktoriagavrosh.details.fake.FakeData
import com.viktoriagavrosh.details.uiscreens.TaleViewModel
import com.viktoriagavrosh.details.uiscreens.toFolkWorkUiDetails
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class TaleViewModelTest {
    @get:Rule
    val testDispatcher = TestDetailScreenDispatcherRule()

    @Test
    fun taleViewModel_init_initFolkWorkUiDetailsSuccess() {
        runTest {
            val viewModel = TaleViewModel(
                taleId = 1,
                folkWorkRepository = FakeFolkWorkUiDetailsRepository()
            )

            val expectedFolkWork = FakeData.fakeTale.toFolkWorkUiDetails()
            val actualFolkWork = viewModel.folkWorkUiDetails.first()
            assertEquals(
                expectedFolkWork,
                actualFolkWork
            )
        }
    }
}
