package com.viktoriagavrosh.fairytales.data

import com.viktoriagavrosh.database.dao.FolkWorkDao
import com.viktoriagavrosh.database.model.FolkWorkDB
import com.viktoriagavrosh.fairytales.model.FolkWork
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

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
    override fun getAllWorks(genre: String): Flow<List<FolkWork>> {
        return folkWorkDao.getAllWorks(genre)
            .map { folkWorks ->
                folkWorks.map { folkWorkDB ->
                    folkWorkDB.toFolkWork()
                }
            }
    }

    /**
     * Retrieve all the favorite items from the given data source by genre
     */
    override fun getAllFavoriteWorks(genre: String): Flow<List<FolkWork>> {
        return folkWorkDao.getAllFavoriteWorks(genre)
            .map { folkWorks ->
                folkWorks.map { folkWorkDB ->
                    folkWorkDB.toFolkWork()
                }
            }
    }

    /**
     * Update the value of an item field is_favorite in the data source
     */
    override suspend fun updateFavoriteWork(id: Int, isFavorite: Boolean) {
        val isFavoriteValue = if (isFavorite) 1 else 0
        folkWorkDao.updateFavoriteWork(id = id, isFavorite = isFavoriteValue)
    }
}

fun FolkWorkDB.toFolkWork(): FolkWork {
    return FolkWork(
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
