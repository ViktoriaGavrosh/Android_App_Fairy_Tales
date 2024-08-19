package com.viktoriagavrosh.fairytales.repositories

import com.viktoriagavrosh.fairytales.TestDispatcherRule
import com.viktoriagavrosh.fairytales.data.repositories.OfflineTaleRepository
import com.viktoriagavrosh.fairytales.data.repositories.utils.RequestResult
import com.viktoriagavrosh.fairytales.data.repositories.utils.toTale
import com.viktoriagavrosh.fairytales.fake.FakeDatabaseSource
import com.viktoriagavrosh.fairytales.model.Tale
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class TaleRepositoryTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private val repository = OfflineTaleRepository(FakeDatabaseSource.FakeDb())

    @Test
    fun taleRepository_getTales_returnAllTales() {
        runTest {
            val expectedList = FakeDatabaseSource.fakeListTaleDb.map { it.toTale() }
            val actualList = repository.getTales(
                genre = "story",
                isFavorite = false
            ).first().data ?: emptyList()

            assertEquals(expectedList, actualList)
        }
    }

    @Test
    fun taleRepository_getTales_returnFavoriteTales() {
        runTest {
            val expectedList = FakeDatabaseSource.fakeListTaleDb
                .filter { it.isFavorite }
                .map { it.toTale() }
            val actualList = repository.getTales(
                genre = "story",
                isFavorite = true
            ).first().data ?: emptyList()

            assertEquals(expectedList, actualList)
        }
    }

    @Test
    fun taleRepository_getTales_returnRequestResultSuccess() {
        runTest {
            val actual = repository.getTales(
                genre = "story",
                isFavorite = false
            ).first() is RequestResult.Success

            assert(actual)
        }
    }

    @Test
    fun taleRepository_getTales_returnRequestResultError() {
        runTest {
            val actual = repository.getTales(
                genre = "error",
                isFavorite = false
            ).first() is RequestResult.Error

            assert(actual)
        }
    }

    @Test
    fun taleRepository_getTales_returnEmptyList() {
        runTest {
            val expectedList = emptyList<Tale>()
            val actualList = repository.getTales(
                genre = "game",
                isFavorite = false
            ).first().data ?: listOf(FakeDatabaseSource.fakeListTaleDb[2])

            assertEquals(expectedList, actualList)
        }
    }

    @Test
    fun taleRepository_getTaleById_returnTale() {
        runTest {
            val expectedTale = FakeDatabaseSource.fakeListTaleDb[1].toTale()
            val actualTale = repository.getTaleById(1)
                .first().data ?: FakeDatabaseSource.fakeListTaleDb[0].toTale()

            assertEquals(expectedTale, actualTale)
        }
    }

    @Test
    fun taleRepository_getTaleById_returnRequestResultSuccess() {
        runTest {
            val actual = repository.getTaleById(1)
                .first() is RequestResult.Success

            assert(actual)
        }
    }

    @Test
    fun taleRepository_getTaleById_returnRequestResultError() {
        runTest {
            val actual = repository.getTaleById(7)
                .first() is RequestResult.Error

            assert(actual)
        }
    }
}
