package com.viktoriagavrosh.settings

import com.viktoriagavrosh.repositories.settings.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class FakeSettingsRepository(
    private val fakeSettingsState: SettingsState
) : SettingsRepository {
    override fun getTextSize(): Flow<Float> {
        return flow { emit(fakeSettingsState.textSize) }
    }

    override suspend fun updateTextSize(textSize: Float) {}
}
