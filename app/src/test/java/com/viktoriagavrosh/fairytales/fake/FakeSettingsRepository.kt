package com.viktoriagavrosh.fairytales.fake

import com.viktoriagavrosh.fairytales.data.repositories.SettingsRepository
import com.viktoriagavrosh.fairytales.ui.settingsscreen.SettingsState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeSettingsRepository(
    private val fakeSettingsState: SettingsState = FakeData.fakeSettingsState
) : SettingsRepository {
    override fun getTextSize(): Flow<Float> {
        return flow { emit(fakeSettingsState.textSize) }
    }

    override suspend fun updateTextSize(textSize: Float) {
        TODO("Not yet implemented")
    }
}
