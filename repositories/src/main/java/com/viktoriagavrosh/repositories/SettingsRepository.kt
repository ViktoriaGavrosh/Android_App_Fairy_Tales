package com.viktoriagavrosh.repositories

import com.viktoriagavrosh.datastore.PreferencesManager
import com.viktoriagavrosh.repositories.model.Settings
import com.viktoriagavrosh.repositories.utils.RequestResult
import com.viktoriagavrosh.repositories.utils.toSettings
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map

/**
 * Repository that provides insert, update, and retrieve settings from a given data source.
 */
interface SettingsRepository {
    /**
     * Retrieve the item from the given data source
     */
    fun getSettings(): Flow<RequestResult<Settings>>

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
     * Retrieve settings from datastore
     */
    override fun getSettings(): Flow<RequestResult<Settings>> {
        return preferencesManager.getSettings()
            .map { it.toSettings() }
            .map<Settings, RequestResult<Settings>> { RequestResult.Success(it) }
            .catch {
                emit(RequestResult.Error(error = it))
            }
    }

    /**
     * Update the value of an item field textSize in datastore
     */
    override suspend fun updateTextSize(textSize: Float) {
        val textSizeString = textSize.toString()
        preferencesManager.updateTextSize(textSizeString)
    }

}
