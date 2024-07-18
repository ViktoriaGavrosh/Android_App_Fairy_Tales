package com.viktoriagavrosh.details.viewmodel

import com.viktoriagavrosh.details.fake.FakeData
import com.viktoriagavrosh.fairytales.model.Tale
import com.viktoriagavrosh.repositories.FolkWorkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeFolkWorkUiDetailsRepository : FolkWorkRepository {

    override fun getAllWorks(genre: String): Flow<List<Tale>> {
        TODO("Not yet implemented")
    }

    override fun getAllFavoriteWorks(genre: String): Flow<List<Tale>> {
        TODO("Not yet implemented")
    }

    override fun getWorkById(id: Int): Flow<Tale> = flow {
        emit(FakeData.fakeTale)
    }

    override suspend fun updateFavoriteWork(id: Int, isFavorite: Boolean) {
        TODO("Not yet implemented")
    }
}
