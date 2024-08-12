package com.viktoriagavrosh.fairytales.viewmodel

import com.viktoriagavrosh.fairytales.data.TaleRepository
import com.viktoriagavrosh.fairytales.fake.FakeSource
import com.viktoriagavrosh.fairytales.model.FolkWork
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeFolkWorkRepository : TaleRepository {

    private val fakeListFolkWork = FakeSource().fakeListFolkWork

    override fun getAllTales(genre: String): Flow<List<FolkWork>> = flow {
        emit(fakeListFolkWork.filter { it.genre == genre })
    }

    override fun getAllFavoriteTales(genre: String): Flow<List<FolkWork>> = flow {
        emit(fakeListFolkWork.filter { it.genre == genre && it.isFavorite })
    }

    override suspend fun updateFavoriteTale(id: Int, isFavorite: Boolean) {
        fakeListFolkWork[id] = fakeListFolkWork[id]
            .copy(isFavorite = isFavorite)
    }
}
