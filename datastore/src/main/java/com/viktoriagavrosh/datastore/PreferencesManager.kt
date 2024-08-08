package com.viktoriagavrosh.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferencesManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
){
    private val TEXT_SIZE_KEY = stringPreferencesKey("text_size_key")

    fun getTextSize(): Flow<String?> {
        return dataStore.data
            .map { preferences ->
                preferences[TEXT_SIZE_KEY]
            }
    }

    suspend fun updateTextSize(textSize: String) {
        dataStore.edit { preferences ->
            preferences[TEXT_SIZE_KEY] = textSize
        }
    }
}
