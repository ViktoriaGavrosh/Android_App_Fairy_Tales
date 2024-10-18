package com.viktoriagavrosh.repositories

import com.viktoriagavrosh.datastore.PreferencesManager
import com.viktoriagavrosh.repositories.model.Settings
import com.viktoriagavrosh.repositories.utils.RequestResult
import com.viktoriagavrosh.repositories.utils.toSettings
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

/**
 * provide data for ui from data source
 */
interface SettingsRepository {

    /**
     * Retrieve settings from the given data source
     *
     * @return flow of [RequestResult] of [Settings]
     */
    fun getSettings(): Flow<RequestResult<Settings>>

    /**
     * Update textSize setting value into given datasource
     *
     * @param textSize new value
     */
    suspend fun updateTextSize(textSize: Float)
}

/**
 * provide data for ui from Datastore
 *
 * @param preferencesManager instance of [PreferencesManager] for working with Datastore
 */
class DatastoreSettingsRepository @Inject constructor(
    private val preferencesManager: PreferencesManager,
) : SettingsRepository {

    /**
     * Retrieve settings from Datastore
     *
     * @return flow of [RequestResult] of [Settings]
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
     * Update textSize setting value into Datastore
     *
     * @param textSize new value
     */
    override suspend fun updateTextSize(textSize: Float) {
        val textSizeString = textSize.toString()
        preferencesManager.updateTextSize(textSizeString)
    }

}
