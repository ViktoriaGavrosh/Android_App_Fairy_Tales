package com.viktoriagavrosh.repositories

import jakarta.inject.Inject

/**
 * Repository that provides insert, update, and retrieve settings from a given data source.
 */
interface SettingsRepository {
    /**
     * Retrieve the item from the given data source
     */
    suspend fun getTextSize(): Double

    /**
     * Update the value of an item field textSize in the data source
     */
    suspend fun updateTextSize(textSize: Double)
}

/**
 * [SettingsRepository] implementation that provides functions for working with DatastorePreferences
 */
class DatastoreSettingsRepository @Inject constructor(

) : SettingsRepository {

    private var textSize = 24.0   // TODO переделать всю логику !!!

    /**
     * Retrieve the item from the given data source
     */
    override suspend fun getTextSize(): Double {
        return textSize
    }

    /**
     * Update the value of an item field textSize in the data source
     */
    override suspend fun updateTextSize(textSize: Double) {
        this.textSize = textSize
    }

}
