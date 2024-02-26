package com.viktoriagavrosh.fairytales.data

import android.util.Log
import com.viktoriagavrosh.fairytales.model.FolkWork
import kotlinx.coroutines.flow.Flow

interface FolkWorkRepository {
    fun getAllStories(): Flow<List<FolkWork>>

    fun getAllGames(): Flow<List<FolkWork>>
}

class OfflineForkWorkRepository(private val folkWorkDao: FolkWorkDao) : FolkWorkRepository {
    override fun getAllStories(): Flow<List<FolkWork>> = folkWorkDao.getAllStories()

    override fun getAllGames(): Flow<List<FolkWork>> = folkWorkDao.getAllGames()

}