package com.viktoriagavrosh.repositories

import com.viktoriagavrosh.database.AppDatabase
import com.viktoriagavrosh.database.model.TaleDb
import com.viktoriagavrosh.repositories.model.Tale
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Repository that provides insert, update, delete and retrieve of [Tale] from a given data source.
 */

interface TaleRepository {
    /**
     * Retrieve all the items from the given data source by genre
     */
    fun getAllTales(genre: String): Flow<List<Tale>>

    /**
     * Retrieve all the favorite items from the given data source by genre
     */
    fun getAllFavoriteTales(genre: String): Flow<List<Tale>>

    /**
     * Retrieve the item from the given data source by id
     */
    fun getTaleById(id: Int): Flow<Tale>

    /**
     * Update the value of an item field is_favorite in the data source
     */
    suspend fun updateFavoriteTale(id: Int, isFavorite: Boolean)
}

/**
 * [TaleRepository] implementation that provides functions for working with the database
 */
class OfflineTaleRepository @Inject constructor(
    private val appDatabase: AppDatabase
) : TaleRepository {

    /**
     * Retrieve all the items from the given data source by genre
     */
    override fun getAllTales(genre: String): Flow<List<Tale>> {
        return appDatabase.taleDao.getAllTales(genre)
            .map { tales ->
                tales.map { taleDb ->
                    taleDb.toTale()
                }
            }
    }

    /**
     * Retrieve all the favorite items from the given data source by genre
     */
    override fun getAllFavoriteTales(genre: String): Flow<List<Tale>> {
        return appDatabase.taleDao.getAllFavoriteTales(genre)
            .map { tales ->
                tales.map { taleDb ->
                    taleDb.toTale()
                }
            }
    }

    /**
     * Retrieve the item from the given data source by id
     */
    override fun getTaleById(id: Int): Flow<Tale> {
        return appDatabase.taleDao.getTaleById(id)
            .map { taleDb -> taleDb.toTale() }
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
