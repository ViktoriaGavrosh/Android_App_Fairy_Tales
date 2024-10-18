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
 * provide data for ui from data source
 */
interface MenuRepository {

    /**
     * Update favorite value of tale by id
     *
     * @param id unique object identifier
     * @param isFavorite new value of tale
     */
    suspend fun updateFavoriteTale(id: Int, isFavorite: Boolean)

    /**
     * Update lastTaleId setting value into given datasource
     *
     * @param id unique object identifier
     */
    suspend fun updateLastTaleId(id: Int)

    /**
     * Retrieve lastTaleId setting from given datasource
     *
     * @return flow of [RequestResult] of id
     */
    fun getLastTaleId(): Flow<RequestResult<Int>>

    /**
     * Retrieve id of random item from the given data source
     *
     * @return flow of [RequestResult] of id
     */
    fun getRandomTaleId(): Flow<RequestResult<Int>>

    /**
     * Retrieve tale from the given data source by id
     *
     * @return flow of [RequestResult] of [Retaining]
     */
    fun getTaleById(id: Int): Flow<RequestResult<Retaining>>
}

/**
 * provide data for ui from local database and Datastore
 *
 * @param appDatabase instance of [TaleAppDatabase]
 * @param preferencesManager instance of [PreferencesManager] for working with Datastore
 */
class OfflineMenuRepository @Inject constructor(
    private val appDatabase: TaleAppDatabase,
    private val preferencesManager: PreferencesManager,
) : MenuRepository {

    /**
     * Update favorite value of tale by id into [TaleAppDatabase]
     *
     * @param id unique object identifier
     * @param isFavorite new value of tale
     */
    override suspend fun updateFavoriteTale(id: Int, isFavorite: Boolean) {
        val isFavoriteValue = if (isFavorite) 1 else 0
        appDatabase.taleDao.updateFavoriteTale(id = id, isFavorite = isFavoriteValue)
    }

    /**
     * Retrieve id of random item from [TaleAppDatabase]
     *
     * @return flow of [RequestResult] of id
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
     * Retrieve lastTaleId setting from Datastore
     *
     * @return flow of [RequestResult] of id
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
     * Update lastTaleId setting value into Datastore
     *
     * @param id unique object identifier
     */
    override suspend fun updateLastTaleId(id: Int) {
        val idString = id.toString()
        preferencesManager.updateLastTaleId(idString)
    }

    /**
     * Retrieve tale from [TaleAppDatabase] by id
     *
     * @return flow of [RequestResult] of [Retaining]
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
