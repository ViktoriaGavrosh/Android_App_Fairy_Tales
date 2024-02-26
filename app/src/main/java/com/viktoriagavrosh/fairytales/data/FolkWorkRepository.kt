package com.viktoriagavrosh.fairytales.data

import com.viktoriagavrosh.fairytales.model.FolkWork
import kotlinx.coroutines.flow.Flow

interface FolkWorkRepository {
    fun getAllWorks(genre: String): Flow<List<FolkWork>>

}

class OfflineForkWorkRepository(private val folkWorkDao: FolkWorkDao) : FolkWorkRepository {
    override fun getAllWorks(genre: String): Flow<List<FolkWork>> = folkWorkDao.getAllWorks(genre)

}