package com.viktoriagavrosh.repositories.fake

import com.viktoriagavrosh.datastore.PreferencesManager
import com.viktoriagavrosh.datastore.model.SettingsDs
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class FakePreferencesManager : PreferencesManager {
    private var settings = FakeSource.fakeSettingsDs

    override fun getSettings(): Flow<SettingsDs> {
        return flow { emit(settings) }
    }

    override suspend fun updateTextSize(textSize: String) {
        settings = settings.copy(textSize = textSize.toFloat())
    }

    override suspend fun updateLastTaleId(lastTaleId: String) {
        settings = settings.copy(lastTaleId = lastTaleId.toInt())
    }


}
