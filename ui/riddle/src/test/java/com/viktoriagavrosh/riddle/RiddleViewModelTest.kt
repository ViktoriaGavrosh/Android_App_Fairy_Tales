package com.viktoriagavrosh.riddle

import com.viktoriagavrosh.riddle.fake.FakeReadRepository
import com.viktoriagavrosh.riddle.fake.FakeSource
import com.viktoriagavrosh.riddle.utils.toReadRiddle
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class RiddleViewModelTest {
    @get:Rule
    internal val testDispatcher = TestDispatcherRule()

    @Test
    fun riddleViewModel_initUiState_verifyRiddle() {
        runTest {
            val expectedRiddle = FakeSource.fakeRiddles[0].toReadRiddle()
            val viewModel = RiddleViewModel(
                riddleId = expectedRiddle.id,
                repository = FakeReadRepository()
            )
            val actualRiddle = viewModel.uiState.first().riddle
            assertEquals(expectedRiddle, actualRiddle)
        }
    }

    @Test
    fun readerViewModel_initUiState_verifyIsError() {
        runTest {
            val viewModel = RiddleViewModel(
                riddleId = 10,
                repository = FakeReadRepository()
            )
            val isErrorValue = viewModel.uiState.first().isError
            assert(isErrorValue)
        }
    }

    @Test
    fun readerViewModel_initTextSize_verifyTextSize() {
        runTest {
            val expectedTextSize = FakeSource.fakeTextSize
            val viewModel = RiddleViewModel(
                riddleId = 0,
                repository = FakeReadRepository()
            )
            val actualTextSize = viewModel.textSize.first()
            assertEquals(expectedTextSize, actualTextSize)
        }
    }
}
