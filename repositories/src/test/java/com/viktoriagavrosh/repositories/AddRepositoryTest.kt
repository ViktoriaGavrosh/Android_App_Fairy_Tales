package com.viktoriagavrosh.repositories

import com.viktoriagavrosh.repositories.fake.FakeDb
import com.viktoriagavrosh.repositories.fake.FakeSource
import com.viktoriagavrosh.repositories.model.Tale
import com.viktoriagavrosh.repositories.utils.RequestResult
import com.viktoriagavrosh.repositories.utils.toTale
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AddRepositoryTest {

    @get:Rule
    internal val testDispatcher = RepositoryTestDispatcherRule()

    private lateinit var repository: AddRepository

    @Before
    fun initRepository() {
        repository = OfflineAddRepository(FakeDb())
    }

    @Test
    fun addRepository_addTale_taleAdded() {
        runTest {
            val expectedTale = FakeSource.fakeListTaleDb[1]
                .copy(id = 5).toTale()
            repository.addTale(expectedTale)
            val retailing = repository.getTaleById(5).first().data
            val actualTale = retailing as? Tale ?: Tale()
            assertEquals(expectedTale, actualTale)
        }
    }

    @Test
    fun settingsRepository_getTaleById_returnTale() {
        runTest {
            val expectedTale = FakeSource.fakeListTaleDb[1].toTale()
            val retailing = repository.getTaleById(1).first().data
            val actualTale = retailing as? Tale ?: Tale()
            assertEquals(expectedTale, actualTale)
        }
    }

    @Test
    fun settingsRepository_getTaleById_returnRequestResultSuccess() {
        runTest {
            val result = repository.getTaleById(1).first()
            assert(result is RequestResult.Success)
        }
    }

    @Test
    fun settingsRepository_getTaleById_returnRequestResultError() {
        runTest {
            val result = repository.getTaleById(10).first()
            assert(result is RequestResult.Error)
        }
    }
}

