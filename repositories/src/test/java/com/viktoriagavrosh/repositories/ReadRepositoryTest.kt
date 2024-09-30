package com.viktoriagavrosh.repositories

import com.viktoriagavrosh.repositories.fake.FakeDb
import com.viktoriagavrosh.repositories.fake.FakePreferencesManager
import com.viktoriagavrosh.repositories.fake.FakeSource
import com.viktoriagavrosh.repositories.model.Folk
import com.viktoriagavrosh.repositories.model.Riddle
import com.viktoriagavrosh.repositories.model.Tale
import com.viktoriagavrosh.repositories.utils.RequestResult
import com.viktoriagavrosh.repositories.utils.ShelfGenre
import com.viktoriagavrosh.repositories.utils.toFolk
import com.viktoriagavrosh.repositories.utils.toRiddle
import com.viktoriagavrosh.repositories.utils.toTale
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class ReadRepositoryTest {

    @get:Rule
    val testDispatcher = RepositoryTestDispatcherRule()

    private val repository = OfflineReadRepository(FakeDb(), FakePreferencesManager())

    @Test
    fun readRepository_getTextSize_returnTextSize() {
        runTest {
            val expectedTextSize = FakeSource.fakeSettingsDs.textSize
            val actualTextSize = repository.getTextSize().first().data ?: 0.0F
            assertEquals(expectedTextSize, actualTextSize)
        }
    }

    @Test
    fun readRepository_getTextSize_returnRequestResultSuccess() {
        runTest {
            val result = repository.getTextSize().first()
            assert(result is RequestResult.Success)
        }
    }

    @Test
    fun readRepository_getBookById_returnAnimalTale() {
        runTest {
            val expectedTale = FakeSource.fakeListTaleDb[0].toTale()
            val retailing = repository.getBookById(0, ShelfGenre.Tales.Animal).first().data
            val actualTale = retailing as? Tale ?: Tale()
            assertEquals(expectedTale, actualTale)
        }
    }

    @Test
    fun readRepository_getBookById_returnFairyTale() {
        runTest {
            val expectedTale = FakeSource.fakeListTaleDb[1].toTale()
            val retailing = repository.getBookById(1, ShelfGenre.Tales.Fairy).first().data
            val actualTale = retailing as? Tale ?: Tale()
            assertEquals(expectedTale, actualTale)
        }
    }

    @Test
    fun readRepository_getBookById_returnPeopleTale() {
        runTest {
            val expectedTale = FakeSource.fakeListTaleDb[2].toTale()
            val retailing = repository.getBookById(2, ShelfGenre.Tales.People).first().data
            val actualTale = retailing as? Tale ?: Tale()
            assertEquals(expectedTale, actualTale)
        }
    }

    @Test
    fun readRepository_getBookById_returnNightTale() {
        runTest {
            val expectedTale = FakeSource.fakeListTaleDb[2].toTale()
            val retailing = repository.getBookById(2, ShelfGenre.Nights).first().data
            val actualTale = retailing as? Tale ?: Tale()
            assertEquals(expectedTale, actualTale)
        }
    }

    @Test
    fun readRepository_getBookById_returnFavoriteTale() {
        runTest {
            val expectedTale = FakeSource.fakeListTaleDb[2].toTale()
            val retailing = repository.getBookById(2, ShelfGenre.Favorites).first().data
            val actualTale = retailing as? Tale ?: Tale()
            assertEquals(expectedTale, actualTale)
        }
    }

    @Test
    fun readRepository_getBookById_returnTaleRequestResultSuccess() {
        runTest {
            val result = repository.getBookById(2, ShelfGenre.Tales.People).first()
            assert(result is RequestResult.Success)
        }
    }

    @Test
    fun readRepository_getBookById_returnTaleRequestResultError() {
        runTest {
            val result = repository.getBookById(10, ShelfGenre.Tales.People).first()
            assert(result is RequestResult.Error)
        }
    }

    @Test
    fun readRepository_getBookById_returnPoemFolk() {
        runTest {
            val expectedFolk = FakeSource.fakeListFolkDb[0].toFolk()
            val retailing = repository.getBookById(0, ShelfGenre.Folks.Poem).first().data
            val actualFolk = retailing as? Folk ?: Folk()
            assertEquals(expectedFolk, actualFolk)
        }
    }

    @Test
    fun readRepository_getBookById_returnLullabyFolk() {
        runTest {
            val expectedFolk = FakeSource.fakeListFolkDb[2].toFolk()
            val retailing = repository.getBookById(2, ShelfGenre.Folks.Lullaby).first().data
            val actualFolk = retailing as? Folk ?: Folk()
            assertEquals(expectedFolk, actualFolk)
        }
    }

    @Test
    fun readRepository_getBookById_returnCountingFolk() {
        runTest {
            val expectedFolk = FakeSource.fakeListFolkDb[1].toFolk()
            val retailing = repository.getBookById(1, ShelfGenre.Folks.Counting).first().data
            val actualFolk = retailing as? Folk ?: Folk()
            assertEquals(expectedFolk, actualFolk)
        }
    }

    @Test
    fun readRepository_getBookById_returnFolkRequestResultSuccess() {
        runTest {
            val result = repository.getBookById(2, ShelfGenre.Folks.Poem).first()
            assert(result is RequestResult.Success)
        }
    }

    @Test
    fun readRepository_getBookById_returnFolkRequestResultError() {
        runTest {
            val result = repository.getBookById(20, ShelfGenre.Folks.Poem).first()
            assert(result is RequestResult.Error)
        }
    }

    @Test
    fun readRepository_getBookById_returnRiddle() {
        runTest {
            val expectedRiddle = FakeSource.fakeListRiddleDb[0].toRiddle()
            val retailing = repository.getBookById(0, ShelfGenre.Riddles).first().data
            val actualRiddle = retailing as? Riddle ?: Riddle()
            assertEquals(expectedRiddle, actualRiddle)
        }
    }

    @Test
    fun readRepository_getBookById_returnRiddleRequestResultSuccess() {
        runTest {
            val result = repository.getBookById(2, ShelfGenre.Riddles).first()
            assert(result is RequestResult.Success)
        }
    }

    @Test
    fun readRepository_getBookById_returnRiddleRequestResultError() {
        runTest {
            val result = repository.getBookById(20, ShelfGenre.Riddles).first()
            assert(result is RequestResult.Error)
        }
    }
}

