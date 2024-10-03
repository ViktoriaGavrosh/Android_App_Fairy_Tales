package com.viktoriagavrosh.reader

import com.viktoriagavrosh.reader.fake.FakeReadRepository
import com.viktoriagavrosh.reader.fake.FakeSource
import com.viktoriagavrosh.reader.utils.toReadBook
import com.viktoriagavrosh.repositories.utils.ShelfGenre
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class ReaderViewModelTest {
    @get:Rule
    internal val testDispatcher = TestDispatcherRule()

    @Test
    fun readerViewModel_initUiState_verifyTaleBook() {
        runTest {
            val expectedBook = FakeSource.fakeTales[0].toReadBook()
            val viewModel = ReaderViewModel(
                bookId = expectedBook.id,
                genre = expectedBook.genre,
                repository = FakeReadRepository()
            )
            val actualBook = viewModel.uiState.first().book
            assertEquals(expectedBook, actualBook)
        }
    }

    @Test
    fun readerViewModel_initUiState_verifyFolkBook() {
        runTest {
            val expectedBook = FakeSource.fakeFolks[0].toReadBook()
            val viewModel = ReaderViewModel(
                bookId = expectedBook.id,
                genre = expectedBook.genre,
                repository = FakeReadRepository()
            )
            val actualBook = viewModel.uiState.first().book
            assertEquals(expectedBook, actualBook)
        }
    }

    @Test
    fun readerViewModel_initUiState_verifyIsError() {
        runTest {
            val viewModel = ReaderViewModel(
                bookId = 10,
                genre = ShelfGenre.Folks.Poem,
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
            val viewModel = ReaderViewModel(
                bookId = 0,
                genre = ShelfGenre.Folks.Poem,
                repository = FakeReadRepository()
            )
            val actualTextSize = viewModel.textSize.first()
            assertEquals(expectedTextSize, actualTextSize)
        }
    }
}
