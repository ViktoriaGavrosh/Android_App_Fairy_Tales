package com.viktoriagavrosh.repositories

import com.viktoriagavrosh.repositories.fake.FakeDb
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
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ShelfRepositoryTest {

    @get:Rule
    internal val testDispatcher = RepositoryTestDispatcherRule()

    private lateinit var repository: ShelfRepository

    @Before
    fun initRepository() {
        repository = OfflineShelfRepository(FakeDb())
    }

    @Test
    fun shelfRepository_updateFavoriteTale_taleUpdated() {
        runTest {
            val expectedTale = FakeSource.fakeListTaleDb[1].copy(isFavorite = true).toTale()
            repository.updateFavoriteTale(1, true)
            val retailing = repository.getItems(ShelfGenre.Favorites).first().data
            val actualListTale = retailing?.map { it as Tale } ?: emptyList()
            assert(expectedTale in actualListTale)
        }
    }

    @Test
    fun shelfRepository_getItems_returnListAnimalTales() {
        runTest {
            val expectedList = FakeSource.fakeListTaleDb
                .filter { it.genre == "animal" }
                .map { it.toTale() }
            val retailing = repository.getItems(ShelfGenre.Tales.Animal).first().data
            val actualList = retailing?.map { it as Tale } ?: emptyList()
            assertEquals(expectedList, actualList)
        }
    }

    @Test
    fun shelfRepository_getItems_returnListFairyTales() {
        runTest {
            val expectedList = FakeSource.fakeListTaleDb
                .filter { it.genre == "fairy" }
                .map { it.toTale() }
            val retailing = repository.getItems(ShelfGenre.Tales.Fairy).first().data
            val actualList = retailing?.map { it as Tale } ?: emptyList()
            assertEquals(expectedList, actualList)
        }
    }

    @Test
    fun shelfRepository_getItems_returnListPeopleTales() {
        runTest {
            val expectedList = FakeSource.fakeListTaleDb
                .filter { it.genre == "people" }
                .map { it.toTale() }
            val retailing = repository.getItems(ShelfGenre.Tales.People).first().data
            val actualList = retailing?.map { it as Tale } ?: emptyList()
            assertEquals(expectedList, actualList)
        }
    }

    @Test
    fun shelfRepository_getItems_returnListFavoriteTales() {
        runTest {
            val expectedList = FakeSource.fakeListTaleDb
                .filter { it.isFavorite }
                .map { it.toTale() }
            val retailing = repository.getItems(ShelfGenre.Favorites).first().data
            val actualList = retailing?.map { it as Tale } ?: emptyList()
            assertEquals(expectedList, actualList)
        }
    }

    @Test
    fun shelfRepository_getItems_returnListNightTales() {
        runTest {
            val expectedList = FakeSource.fakeListTaleDb
                .filter { it.isNight }
                .map { it.toTale() }
            val retailing = repository.getItems(ShelfGenre.Nights).first().data
            val actualList = retailing?.map { it as Tale } ?: emptyList()
            assertEquals(expectedList, actualList)
        }
    }

    @Test
    fun shelfRepository_getItems_returnTaleRequestResultSuccess() {
        runTest {
            val result = repository.getItems(ShelfGenre.Tales.Animal).first()
            assert(result is RequestResult.Success)
        }
    }

    @Test
    fun shelfRepository_getItems_returnPoemFolks() {
        runTest {
            val expectedList = FakeSource.fakeListFolkDb
                .filter { it.genre == "poem" }
                .map { it.toFolk() }
            val retailing = repository.getItems(ShelfGenre.Folks.Poem).first().data
            val actualList = retailing?.map { it as Folk } ?: emptyList()
            assertEquals(expectedList, actualList)
        }
    }

    @Test
    fun shelfRepository_getItems_returnCountingFolks() {
        runTest {
            val expectedList = FakeSource.fakeListFolkDb
                .filter { it.genre == "counting" }
                .map { it.toFolk() }
            val retailing = repository.getItems(ShelfGenre.Folks.Counting).first().data
            val actualList = retailing?.map { it as Folk } ?: emptyList()
            assertEquals(expectedList, actualList)
        }
    }

    @Test
    fun shelfRepository_getItems_returnLullabyFolks() {
        runTest {
            val expectedList = FakeSource.fakeListFolkDb
                .filter { it.genre == "lullaby" }
                .map { it.toFolk() }
            val retailing = repository.getItems(ShelfGenre.Folks.Lullaby).first().data
            val actualList = retailing?.map { it as Folk } ?: emptyList()
            assertEquals(expectedList, actualList)
        }
    }

    @Test
    fun shelfRepository_getItems_returnFolkRequestResultSuccess() {
        runTest {
            val result = repository.getItems(ShelfGenre.Folks.Counting).first()
            assert(result is RequestResult.Success)
        }
    }

    @Test
    fun shelfRepository_getItems_returnRiddles() {
        runTest {
            val expectedList = FakeSource.fakeListRiddleDb
                .map { it.toRiddle() }
            val retailing = repository.getItems(ShelfGenre.Riddles).first().data
            val actualList = retailing?.map { it as Riddle } ?: emptyList()
            assertEquals(expectedList, actualList)
        }
    }

    @Test
    fun shelfRepository_getItems_returnRiddleRequestResultSuccess() {
        runTest {
            val result = repository.getItems(ShelfGenre.Riddles).first()
            assert(result is RequestResult.Success)
        }
    }
}

