package com.viktoriagavrosh.repositories

import com.viktoriagavrosh.database.TaleAppDatabase
import com.viktoriagavrosh.datastore.PreferencesManager
import com.viktoriagavrosh.repositories.model.Retaining
import com.viktoriagavrosh.repositories.model.Tale
import com.viktoriagavrosh.repositories.utils.RequestResult
import com.viktoriagavrosh.repositories.utils.toTale
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

/**
 * Repository that provides insert, update, delete and retrieve of items from a given data source.
 */

interface MenuRepository {

    /**
     * Update the value of an Tale field is_favorite in the data source
     */
    suspend fun updateFavoriteTale(id: Int, isFavorite: Boolean)

    /**
     * Update the value of lastTale in the data source
     */
    suspend fun updateLastTaleId(id: Int)

    /**
     * Retrieve the item from the given data source
     */
    fun getLastTaleId(): Flow<RequestResult<Int>>

    /**
     * Retrieve id of random item from the given data source
     */
    fun getRandomTaleId(): Flow<RequestResult<Int>>

    /**
     * Retrieve the item from the given data source by id
     */
    fun getTaleById(id: Int): Flow<RequestResult<Retaining>>
}

/**
 * [MenuRepository] implementation that provides functions for working with the database
 */
class OfflineMenuRepository @Inject constructor(
    private val appDatabase: TaleAppDatabase,
    private val preferencesManager: PreferencesManager,
) : MenuRepository {

    /**
     * Update the value of Tale field is_favorite in db
     */
    override suspend fun updateFavoriteTale(id: Int, isFavorite: Boolean) {
        val isFavoriteValue = if (isFavorite) 1 else 0
        appDatabase.taleDao.updateFavoriteTale(id = id, isFavorite = isFavoriteValue)
    }

    /**
     * Retrieve id of random Tale from db
     */
    override fun getRandomTaleId(): Flow<RequestResult<Int>> {
        return appDatabase.taleDao.getAllTaleId()
            .map { it.random() }
            .map<Int, RequestResult<Int>> { RequestResult.Success(it) }
            .catch {
                emit(RequestResult.Error(error = it))
            }
    }

    /**
     * Retrieve item from datastore
     */
    override fun getLastTaleId(): Flow<RequestResult<Int>> {
        return preferencesManager.getSettings()
            .map { it.lastTaleId }
            .map<Int, RequestResult<Int>> { RequestResult.Success(it) }
            .catch {
                emit(RequestResult.Error(error = it))
            }
    }

    /**
     * Update the value of an item field textSize in datastore
     */
    override suspend fun updateLastTaleId(id: Int) {
        val idString = id.toString()
        preferencesManager.updateLastTaleId(idString)
    }

    /**
     * Retrieve the item from the given data source by id
     */
    override fun getTaleById(id: Int): Flow<RequestResult<Tale>> {
        val request = appDatabase.taleDao.getTaleById(id)
            .map { it.toTale() }
            .map<Tale, RequestResult<Tale>> { RequestResult.Success(it) }
            .catch {
                emit(RequestResult.Error(error = it))
            }

        return request
    }

}
