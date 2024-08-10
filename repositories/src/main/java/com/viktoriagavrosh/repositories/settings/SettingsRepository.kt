package com.viktoriagavrosh.repositories.settings

import com.viktoriagavrosh.datastore.PreferencesManager
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map

/**
 * Repository that provides insert, update, and retrieve settings from a given data source.
 */
interface SettingsRepository {
    /**
     * Retrieve the item from the given data source
     */
    fun getTextSize(): Flow<Float>

    /**
     * Update the value of an item field textSize in the data source
     */
    suspend fun updateTextSize(textSize: Float)
}

/**
 * [SettingsRepository] implementation that provides functions for working with DatastorePreferences
 */
class DatastoreSettingsRepository @Inject constructor(
    private val preferencesManager: PreferencesManager,
) : SettingsRepository {

    /**
     * Retrieve the item from the given data source
     */
    override fun getTextSize(): Flow<Float> {
        return preferencesManager.getTextSize()
            .filterNotNull()
            .map { it.toFloat() }
    }

    /**
     * Update the value of an item field textSize in the data source
     */
    override suspend fun updateTextSize(textSize: Float) {
        val textSizeString = textSize.toString()
        preferencesManager.updateTextSize(textSizeString)
    }

}
