package com.viktoriagavrosh.repositories

import com.viktoriagavrosh.repositories.fake.FakePreferencesManager
import com.viktoriagavrosh.repositories.fake.FakeSource
import com.viktoriagavrosh.repositories.model.Settings
import com.viktoriagavrosh.repositories.utils.RequestResult
import com.viktoriagavrosh.repositories.utils.toSettings
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SettingsRepositoryTest {

    @get:Rule
    internal val testDispatcher = RepositoryTestDispatcherRule()

    private lateinit var repository: SettingsRepository

    @Before
    fun initRepository() {
        repository = DatastoreSettingsRepository(FakePreferencesManager())
    }

    @Test
    fun settingsRepository_getSettings_returnSettings() {
        runTest {
            val expectedSettings = FakeSource.fakeSettingsDs.toSettings()
            val actualSettings = repository.getSettings().first().data ?: Settings()
            assertEquals(expectedSettings, actualSettings)
        }
    }

    @Test
    fun settingsRepository_getSettings_returnRequestResultSuccess() {
        runTest {
            val result = repository.getSettings().first()
            assert(result is RequestResult.Success)
        }
    }

    @Test
    fun settingsRepository_updateTextSize_textSizeUpdated() {
        runTest {
            val expectedTextSize = 40.0F
            repository.updateTextSize(expectedTextSize)
            val actualTextSize = repository.getSettings().first().data?.textSize ?: 0.0F
            assertEquals(expectedTextSize, actualTextSize)
        }
    }
}

