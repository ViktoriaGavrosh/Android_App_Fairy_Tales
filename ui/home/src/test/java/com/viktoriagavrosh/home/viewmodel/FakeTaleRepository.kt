package com.viktoriagavrosh.home.viewmodel

import com.viktoriagavrosh.home.fake.FakeSource
import com.viktoriagavrosh.repositories.model.Tale
import com.viktoriagavrosh.repositories.tale.RequestResult
import com.viktoriagavrosh.repositories.tale.TaleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeTaleRepository : TaleRepository {

    private val fakeListTales = FakeSource().fakeListTales

    override fun getTales(genre: String, isFavorite: Boolean): Flow<RequestResult<List<Tale>>> =
        flow {
            emit(RequestResult.Success(fakeListTales.filter { it.genre == genre }))
        }

    override fun getTaleById(id: Int): Flow<RequestResult<Tale>> = flow {
        emit(RequestResult.Success(fakeListTales.first { it.id == id }))
    }

    override suspend fun updateFavoriteTale(id: Int, isFavorite: Boolean) {
        fakeListTales[id] = fakeListTales[id]
            .copy(isFavorite = isFavorite)
    }
}

