package com.viktoriagavrosh.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.viktoriagavrosh.datastore.model.SettingsDs
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val TEXT_SIZE_KEY = stringPreferencesKey("text_size_key")
private val LAST_TALE_KEY = stringPreferencesKey("last_tale_key")

interface PreferencesManager {
    fun getSettings(): Flow<SettingsDs>

    suspend fun updateTextSize(textSize: String)

    suspend fun updateLastTaleId(lastTaleId: String)


}

/**
 * Class for working with DataStore<Preferences>. DataStore provides data for Settings
 */
class AppPreferencesManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : PreferencesManager {

    override fun getSettings(): Flow<SettingsDs> {
        return dataStore.data.map { preferences ->
            preferences.toSettingsDs()
        }
    }

    override suspend fun updateTextSize(textSize: String) {
        dataStore.edit { preferences: MutablePreferences ->
            preferences[TEXT_SIZE_KEY] = textSize
        }
    }

    override suspend fun updateLastTaleId(lastTaleId: String) {
        dataStore.edit { preferences: MutablePreferences ->
            preferences[LAST_TALE_KEY] = lastTaleId
        }
    }
}

private fun Preferences.toSettingsDs(): SettingsDs {
    return SettingsDs(
        textSize = this[TEXT_SIZE_KEY]?.toFloat() ?: 0.0F,
        lastTaleId = this[LAST_TALE_KEY]?.toInt() ?: 1,
    )
}
