package com.viktoriagavrosh.fairytales.data

import com.viktoriagavrosh.fairytales.model.FolkWork
import kotlinx.coroutines.flow.Flow

interface FolkWorkRepository {
    fun getAllWorks(genre: String): Flow<List<FolkWork>>

    fun getAllFavoriteWorks(genre: String): Flow<List<FolkWork>>

    suspend fun updateFavoriteWork(id: Int, isFavorite: Boolean)
}

class OfflineForkWorkRepository(private val folkWorkDao: FolkWorkDao) : FolkWorkRepository {
    override fun getAllWorks(genre: String): Flow<List<FolkWork>> = folkWorkDao.getAllWorks(genre)
    override fun getAllFavoriteWorks(genre: String): Flow<List<FolkWork>> = folkWorkDao
        .getAllFavoriteWorks(genre)

    override suspend fun updateFavoriteWork(id: Int, isFavorite: Boolean) {
        val isFavoriteValue = if (isFavorite) 1 else 0
        folkWorkDao.updateFavoriteWork(id = id, isFavorite = isFavoriteValue)
    }
}