package com.viktoriagavrosh.fairytales.viewmodel

import com.viktoriagavrosh.fairytales.data.FolkWorkRepository
import com.viktoriagavrosh.fairytales.fake.FakeSource
import com.viktoriagavrosh.fairytales.model.FolkWork
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeFolkWorkRepository : FolkWorkRepository {

    //private val fakeFlow = MutableSharedFlow<List<FolkWork>>()
    //suspend fun emit(value: List<FolkWork>) = fakeFlow.emit(value)
    private val fakeListFolkWork = FakeSource().fakeListFolkWork

    override fun getAllWorks(genre: String): Flow<List<FolkWork>> = flow {
        emit(fakeListFolkWork.filter { it.genre == genre })
    }

    override fun getAllFavoriteWorks(genre: String): Flow<List<FolkWork>> = flow {
        emit(fakeListFolkWork.filter { it.genre == genre && it.isFavorite })
    }

    override suspend fun updateFavoriteWork(id: Int, isFavorite: Boolean) {
        fakeListFolkWork[id] = fakeListFolkWork[id]
            .copy(isFavorite = isFavorite)
    }
}