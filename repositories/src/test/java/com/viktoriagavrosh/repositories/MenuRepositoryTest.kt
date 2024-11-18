package com.viktoriagavrosh.repositories

import com.viktoriagavrosh.repositories.fake.FakeDb
import com.viktoriagavrosh.repositories.fake.FakePreferencesManager
import com.viktoriagavrosh.repositories.fake.FakeSource
import com.viktoriagavrosh.repositories.model.Tale
import com.viktoriagavrosh.repositories.utils.RequestResult
import com.viktoriagavrosh.repositories.utils.toTale
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MenuRepositoryTest {

    @get:Rule
    internal val testDispatcher = RepositoryTestDispatcherRule()

    private lateinit var repository: MenuRepository

    @Before
    fun initRepository() {
        repository = OfflineMenuRepository(FakeDb(), FakePreferencesManager())
    }

    @Test
    fun menuRepository_updateFavoriteTale_taleUpdated() {
        runTest {
            val expectedTale = FakeSource.fakeListTaleDb[1].copy(isFavorite = true).toTale()
            repository.updateFavoriteTale(1, true)
            val retailing = repository.getTaleById(1).first().data
            val actualTale = retailing as? Tale ?: Tale()
            assertEquals(expectedTale, actualTale)
        }
    }

    @Test
    fun menuRepository_getRandomTaleId_returnIdFromListTale() {
        runTest {
            val randomId = repository.getRandomTaleId().first().data ?: 125
            assert(randomId in FakeSource.fakeListTaleDb.map { it.id })
        }
    }

    @Test
    fun menuRepository_getRandomTaleId_returnDifferentId() {
        runTest {
            val firstId = repository.getRandomTaleId().first().data ?: 125
            val secondId = repository.getRandomTaleId().first().data ?: 125
            assertNotEquals(firstId, secondId)
        }
    }

    @Test
    fun menuRepository_getRandomTaleId_returnRequestResultSuccess() {
        runTest {
            val result = repository.getRandomTaleId().first()
            assert(result is RequestResult.Success)
        }
    }

    @Test
    fun menuRepository_getTaleById_returnTale() {
        runTest {
            val expectedTale = FakeSource.fakeListTaleDb[1].toTale()
            val retailing = repository.getTaleById(1).first().data
            val actualTale = retailing as? Tale ?: Tale()
            assertEquals(expectedTale, actualTale)
        }
    }

    @Test
    fun menuRepository_getTaleById_returnRequestResultSuccess() {
        runTest {
            val result = repository.getTaleById(1).first()
            assert(result is RequestResult.Success)
        }
    }

    @Test
    fun menuRepository_getTaleById_returnRequestResultError() {
        runTest {
            val result = repository.getTaleById(10).first()
            assert(result is RequestResult.Error)
        }
    }

    @Test
    fun menuRepository_getLastTaleId_returnId() {
        runTest {
            val expectedId = FakeSource.fakeSettingsDs.lastTaleId
            val actualId = repository.getLastTaleId().first().data ?: 125
            assertEquals(expectedId, actualId)
        }
    }

    @Test
    fun menuRepository_getLastTaleId_returnRequestResultSuccess() {
        runTest {
            val result = repository.getLastTaleId().first()
            assert(result is RequestResult.Success)
        }
    }

    @Test
    fun menuRepository_updateLastTaleId_lastTaleIdUpdated() {
        runTest {
            val expectedId = 2
            repository.updateLastTaleId(2)
            val actualId = repository.getLastTaleId().first().data ?: 125
            assertEquals(expectedId, actualId)
        }
    }
}

