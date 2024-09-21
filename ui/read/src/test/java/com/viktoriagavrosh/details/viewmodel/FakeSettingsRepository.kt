package com.viktoriagavrosh.details.viewmodel

import com.viktoriagavrosh.repositories.settings.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class FakeSettingsRepository : SettingsRepository {
    override fun getTextSize(): Flow<Float> {
        return flow { emit(50.0F) }
    }

    override suspend fun updateTextSize(textSize: Float) {}
}
