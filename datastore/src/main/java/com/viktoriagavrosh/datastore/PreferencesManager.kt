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

/**
 * key for textSize setting from [DataStore]
 */
private val TEXT_SIZE_KEY = stringPreferencesKey("text_size_key")

/**
 * key for lastTale setting from [DataStore]
 */
private val LAST_TALE_KEY = stringPreferencesKey("last_tale_key")

/**
 * Interface for working with [DataStore]
 */
interface PreferencesManager {

    /**
     * Return all settings from [DataStore]
     *
     * @return flow of [SettingsDs]
     */
    fun getSettings(): Flow<SettingsDs>

    /**
     * Update textSize setting in [DataStore]
     *
     * @param textSize new value
     */
    suspend fun updateTextSize(textSize: String)

    /**
     * Update lastTale setting in [DataStore]
     *
     * @param lastTaleId new value
     */
    suspend fun updateLastTaleId(lastTaleId: String)
}

/**
 * Class for working with DataStore<Preferences>. DataStore provides data for Settings
 *
 * @param dataStore instance of [DataStore]
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

/**
 * extension fun of [Preferences] to convert to [SettingsDs]
 *
 * @return instance of [SettingsDs]
 */
private fun Preferences.toSettingsDs(): SettingsDs {
    return SettingsDs(
        textSize = this[TEXT_SIZE_KEY]?.toFloat() ?: 0.0F,
        lastTaleId = this[LAST_TALE_KEY]?.toInt() ?: 1,
    )
}
