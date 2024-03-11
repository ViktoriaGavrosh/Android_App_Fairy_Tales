package com.viktoriagavrosh.fairytales.data

import com.viktoriagavrosh.fairytales.model.FolkWork
import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete and retrieve of [FolkWork] from a given data source.
 */
interface FolkWorkRepository {
    /**
     * Retrieve all the items from the given data source by genre
     */
    fun getAllWorks(genre: String): Flow<List<FolkWork>>

    /**
     * Retrieve all the favorite items from the given data source by genre
     */
    fun getAllFavoriteWorks(genre: String): Flow<List<FolkWork>>

    /**
     * Update the value of an item field is_favorite in the data source
     */
    suspend fun updateFavoriteWork(id: Int, isFavorite: Boolean)
}

/**
 * [FolkWorkRepository] implementation that provides functions for working with the database
 */
class OfflineForkWorkRepository(private val folkWorkDao: FolkWorkDao) : FolkWorkRepository {
    /**
     * Retrieve all the items from the given data source by genre
     */
    override fun getAllWorks(genre: String): Flow<List<FolkWork>> = folkWorkDao.getAllWorks(genre)

    /**
     * Retrieve all the favorite items from the given data source by genre
     */
    override fun getAllFavoriteWorks(genre: String): Flow<List<FolkWork>> = folkWorkDao
        .getAllFavoriteWorks(genre)

    /**
     * Update the value of an item field is_favorite in the data source
     */
    override suspend fun updateFavoriteWork(id: Int, isFavorite: Boolean) {
        val isFavoriteValue = if (isFavorite) 1 else 0
        folkWorkDao.updateFavoriteWork(id = id, isFavorite = isFavoriteValue)
    }
}