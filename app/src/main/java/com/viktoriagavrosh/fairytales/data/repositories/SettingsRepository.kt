package com.viktoriagavrosh.fairytales.data.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Repository that provides insert, update and retrieve settings from a given data source
 */
interface SettingsRepository {
    /**
     * Retrieve the item from the given data source
     */
    fun getTextSize(): Flow<Float>

    /**
     * Update the value of item field textSize in data source
     */
    suspend fun updateTextSize(textSize: Float)
}

/**
 * [SettingsRepository] implementation that provides functions for working with DataStore<Preferences>
 */
class DatastoreSettingsRepository @Inject constructor(
    private val preferencesManager: PreferencesManager,
) : SettingsRepository {

    /**
     * Retrieve the item from the DataStore
     */
    override fun getTextSize(): Flow<Float> {
        return preferencesManager.getTextSize()
            .filterNotNull()
            .map { it.toFloat() }
    }

    /**
     * Update the value of item field textSize in DataStore
     */
    override suspend fun updateTextSize(textSize: Float) {
        val textSizeString = textSize.toString()
        preferencesManager.updateTextSize(textSizeString)
    }
}
