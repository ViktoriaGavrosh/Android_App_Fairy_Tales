package com.viktoriagavrosh.fairytales.data.repositories

import com.viktoriagavrosh.fairytales.data.database.TaleAppDatabase
import com.viktoriagavrosh.fairytales.data.repositories.utils.RequestResult
import com.viktoriagavrosh.fairytales.data.repositories.utils.toTale
import com.viktoriagavrosh.fairytales.model.Tale
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Repository that provides insert, update, delete and retrieve of [Tale] from a given data source
 */
interface TaleRepository {

    /**
     * Retrieve all items from given data source by genre
     */
    fun getTales(genre: String, isFavorite: Boolean): Flow<RequestResult<List<Tale>>>

    /**
     * Retrieve item from given data source by id
     */
    fun getTaleById(id: Int): Flow<RequestResult<Tale>>

    /**
     * Update value of item field is_favorite in data source
     */
    suspend fun updateFavoriteTale(id: Int, isFavorite: Boolean)
}

/**
 * [TaleRepository] implementation that provides functions for working with database
 */
class OfflineTaleRepository @Inject constructor(
    private val appDatabase: TaleAppDatabase
) : TaleRepository {

    /**
     * Retrieve all items from database by genre
     */
    override fun getTales(genre: String, isFavorite: Boolean): Flow<RequestResult<List<Tale>>> {
        val request = try {    // TODO change to .catch()
            if (isFavorite) {
                appDatabase.taleDao.getAllFavoriteTales(genre)
            } else {
                appDatabase.taleDao.getAllTales(genre)
            }
                .map { tales ->
                    tales.map { taleDb -> taleDb.toTale() }
                }
                .map<List<Tale>, RequestResult<List<Tale>>> { RequestResult.Success(it) }
        } catch (e: Exception) {   // TODO need concrete exceptions ?
            flow {
                emit(RequestResult.Error(error = e))
            }
        }
        return request
    }

    /**
     * Retrieve item from database by id
     */
    override fun getTaleById(id: Int): Flow<RequestResult<Tale>> {
        val request = appDatabase.taleDao.getTaleById(id)
            .map { taleDb -> taleDb.toTale() }
            .map<Tale, RequestResult<Tale>> { RequestResult.Success(it) }
            .catch {
                emit(RequestResult.Error(error = it))
            }
        return request
    }

    /**
     * Update value of item field is_favorite in database
     */
    override suspend fun updateFavoriteTale(id: Int, isFavorite: Boolean) {
        val isFavoriteValue = if (isFavorite) 1 else 0
        appDatabase.taleDao.updateFavoriteTale(id = id, isFavorite = isFavoriteValue)
    }
}
