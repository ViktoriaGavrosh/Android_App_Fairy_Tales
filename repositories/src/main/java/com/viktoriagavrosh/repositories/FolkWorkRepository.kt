package com.viktoriagavrosh.repositories

import com.viktoriagavrosh.database.AppDatabase
import com.viktoriagavrosh.database.model.FolkWorkDB
import com.viktoriagavrosh.fairytales.model.Tale
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Repository that provides insert, update, delete and retrieve of [Tale] from a given data source.
 */

interface FolkWorkRepository {
    /**
     * Retrieve all the items from the given data source by genre
     */
    fun getAllWorks(genre: String): Flow<List<Tale>>

    /**
     * Retrieve all the favorite items from the given data source by genre
     */
    fun getAllFavoriteWorks(genre: String): Flow<List<Tale>>

    /**
     * Update the value of an item field is_favorite in the data source
     */
    suspend fun updateFavoriteWork(id: Int, isFavorite: Boolean)
}

/**
 * [FolkWorkRepository] implementation that provides functions for working with the database
 */
class OfflineForkWorkRepository @Inject constructor(
    private val appDatabase: AppDatabase
) : FolkWorkRepository {

    /**
     * Retrieve all the items from the given data source by genre
     */
    override fun getAllWorks(genre: String): Flow<List<Tale>> {
        return appDatabase.folkWorkDao.getAllWorks(genre)
            .map { folkWorks ->
                folkWorks.map { folkWorkDB ->
                    folkWorkDB.toTale()
                }
            }
    }

    /**
     * Retrieve all the favorite items from the given data source by genre
     */
    override fun getAllFavoriteWorks(genre: String): Flow<List<Tale>> {
        return appDatabase.folkWorkDao.getAllFavoriteWorks(genre)
            .map { folkWorks ->
                folkWorks.map { folkWorkDB ->
                    folkWorkDB.toTale()
                }
            }
    }

    /**
     * Update the value of an item field is_favorite in the data source
     */
    override suspend fun updateFavoriteWork(id: Int, isFavorite: Boolean) {
        val isFavoriteValue = if (isFavorite) 1 else 0
        appDatabase.folkWorkDao.updateFavoriteWork(id = id, isFavorite = isFavoriteValue)
    }
}

fun FolkWorkDB.toTale(): Tale {
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
