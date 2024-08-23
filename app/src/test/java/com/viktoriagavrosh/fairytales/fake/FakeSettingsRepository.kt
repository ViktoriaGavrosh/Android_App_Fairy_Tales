package com.viktoriagavrosh.fairytales.fake

import com.viktoriagavrosh.fairytales.data.repositories.SettingsRepository
import com.viktoriagavrosh.fairytales.ui.settingsscreen.SettingsState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeSettingsRepository(
    fakeSettingsState: SettingsState = FakeData.fakeSettingsState
) : SettingsRepository {

    private var fakeTextSize = fakeSettingsState.textSize

    override fun getTextSize(): Flow<Float> {
        return flow { emit(fakeTextSize) }
    }

    override suspend fun updateTextSize(textSize: Float) {
        fakeTextSize = textSize
    }
}
