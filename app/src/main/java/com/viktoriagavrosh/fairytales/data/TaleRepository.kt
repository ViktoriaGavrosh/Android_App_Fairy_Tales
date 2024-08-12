package com.viktoriagavrosh.fairytales.data

import com.viktoriagavrosh.fairytales.model.FolkWork
import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete and retrieve of [FolkWork] from a given data source.
 */
interface TaleRepository {
    /**
     * Retrieve all the items from the given data source by genre
     */
    fun getAllTales(genre: String): Flow<List<FolkWork>>

    /**
     * Retrieve all the favorite items from the given data source by genre
     */
    fun getAllFavoriteTales(genre: String): Flow<List<FolkWork>>

    /**
     * Update the value of an item field is_favorite in the data source
     */
    suspend fun updateFavoriteTale(id: Int, isFavorite: Boolean)
}

/**
 * [TaleRepository] implementation that provides functions for working with the database
 */
class OfflineTaleRepository(private val folkWorkDao: TaleDao) : TaleRepository {
    /**
     * Retrieve all the items from the given data source by genre
     */
    override fun getAllTales(genre: String): Flow<List<FolkWork>> = folkWorkDao.getAllTales(genre)

    /**
     * Retrieve all the favorite items from the given data source by genre
     */
    override fun getAllFavoriteTales(genre: String): Flow<List<FolkWork>> = folkWorkDao
        .getAllFavoriteTales(genre)

    /**
     * Update the value of an item field is_favorite in the data source
     */
    override suspend fun updateFavoriteTale(id: Int, isFavorite: Boolean) {
        val isFavoriteValue = if (isFavorite) 1 else 0
        folkWorkDao.updateFavoriteTale(id = id, isFavorite = isFavoriteValue)
    }
}
