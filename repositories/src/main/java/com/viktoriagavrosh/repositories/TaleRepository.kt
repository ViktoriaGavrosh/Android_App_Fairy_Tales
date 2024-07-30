package com.viktoriagavrosh.repositories

import com.viktoriagavrosh.database.TaleAppDatabase
import com.viktoriagavrosh.database.model.TaleDb
import com.viktoriagavrosh.repositories.model.Tale
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

/**
 * Repository that provides insert, update, delete and retrieve of [Tale] from a given data source.
 */

interface TaleRepository {
    /**
     * Retrieve all the items from the given data source by genre
     */
    fun getTales(genre: String, isFavorite: Boolean): Flow<RequestResult<List<Tale>>>

    /**
     * Retrieve the item from the given data source by id
     */
    fun getTaleById(id: Int): Flow<RequestResult<Tale>>

    /**
     * Update the value of an item field is_favorite in the data source
     */
    suspend fun updateFavoriteTale(id: Int, isFavorite: Boolean)
}

/**
 * [TaleRepository] implementation that provides functions for working with the database
 */
class OfflineTaleRepository @Inject constructor(
    private val appDatabase: TaleAppDatabase
) : TaleRepository {

    /**
     * Retrieve all the items from the given data source by genre
     */
    override fun getTales(genre: String, isFavorite: Boolean): Flow<RequestResult<List<Tale>>> {

        val request = try {
            if (isFavorite) {
                appDatabase.taleDao.getAllFavoriteTales(genre)
            } else {
                appDatabase.taleDao.getAllTales(genre)
            }
                .map { tales ->
                    tales.map { taleDb -> taleDb.toTale() }
                }
                .map<List<Tale>, RequestResult<List<Tale>>> { RequestResult.Success(it) }
        } catch (e: Exception) {
            flow {
                emit(RequestResult.Error(error = e))
            }
        }

        return request
    }

    /**
     * Retrieve the item from the given data source by id
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
     * Update the value of an item field is_favorite in the data source
     */
    override suspend fun updateFavoriteTale(id: Int, isFavorite: Boolean) {
        val isFavoriteValue = if (isFavorite) 1 else 0
        appDatabase.taleDao.updateFavoriteTale(id = id, isFavorite = isFavoriteValue)
    }
}

fun TaleDb.toTale(): Tale {
    return Tale(
        id = id,
        genre = genre,
        title = title,
        text = text,
        answer = answer,
        imageUri = imageUri,
        audioUri = audioUri,
        isFavorite = isFavorite
    )
}
