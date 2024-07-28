package com.viktoriagavrosh.details.viewmodel

import com.viktoriagavrosh.details.fake.FakeData
import com.viktoriagavrosh.repositories.RequestResult
import com.viktoriagavrosh.repositories.TaleRepository
import com.viktoriagavrosh.repositories.model.Tale
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class FakeTaleRepository : TaleRepository {

    override fun getTales(genre: String, isFavorite: Boolean): Flow<RequestResult<List<Tale>>> {
        TODO("Not yet implemented")
    }

    override fun getTaleById(id: Int): Flow<RequestResult<Tale>> = flow {
        emit(RequestResult.Success(FakeData.fakeTale))
    }

    override suspend fun updateFavoriteTale(id: Int, isFavorite: Boolean) {
        TODO("Not yet implemented")
    }
}
