package com.viktoriagavrosh.settings.fake

import com.viktoriagavrosh.repositories.SettingsRepository
import com.viktoriagavrosh.repositories.model.Settings
import com.viktoriagavrosh.repositories.utils.RequestResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class FakeSettingsRepository : SettingsRepository {

    private var settings = FakeSource.fakeSettings

    override fun getSettings(): Flow<RequestResult<Settings>> {
        return flow {
            emit(RequestResult.Success(settings))
        }
    }

    override suspend fun updateTextSize(textSize: Float) {
        settings = settings.copy(textSize = textSize)
    }
}
