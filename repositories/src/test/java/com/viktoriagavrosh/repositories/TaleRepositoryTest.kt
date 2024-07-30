package com.viktoriagavrosh.repositories

import com.viktoriagavrosh.repositories.model.Tale
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
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

class TaleRepositoryTest {

    @get:Rule
    val testDispatcher = RepositoryTestDispatcherRule()

    private val repository = OfflineTaleRepository(FakeSource.FakeDb())

    @Test
    fun taleRepository_getTales_returnAllTales() {
        runTest {
            val expectedList = FakeSource.fakeListTaleDb.map { it.toTale() }
            val actualList = repository.getTales(
                genre = "story",
                isFavorite = false
            ).first().data ?: emptyList()

            assertEquals(
                expectedList,
                actualList
            )
        }
    }

    @Test
    fun taleRepository_getTales_returnFavoriteTales() {
        runTest {
            val expectedList = FakeSource.fakeListTaleDb
                .filter { it.isFavorite }
                .map { it.toTale() }
            val actualList = repository.getTales(
                genre = "story",
                isFavorite = true
            ).first().data ?: emptyList()

            assertEquals(
                expectedList,
                actualList
            )
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
            ).first().data ?: listOf(FakeSource.fakeListTaleDb[2])

            assertEquals(
                expectedList,
                actualList
            )
        }
    }

    @Test
    fun taleRepository_getTaleById_returnTale() {
        runTest {
            val expectedTale = FakeSource.fakeListTaleDb[1].toTale()
            val actualTale = repository.getTaleById(id = 1)
                .first().data ?: FakeSource.fakeListTaleDb[0].toTale()

            assertEquals(
                expectedTale,
                actualTale
            )
        }
    }

    @Test
    fun taleRepository_getTaleById_returnRequestResultSuccess() {
        runTest {
            val actual = repository.getTaleById(id = 1)
                .first() is RequestResult.Success

            assert(actual)
        }
    }

    @Test
    fun taleRepository_getTaleById_returnRequestResultError() {
        runTest {
            val actual = repository.getTaleById(id = 7)
                .first() is RequestResult.Error

            assert(actual)
        }
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
class RepositoryTestDispatcherRule(
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
) : TestWatcher() {
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}
