package com.viktoriagavrosh.home.viewmodel

import com.viktoriagavrosh.repositories.model.Tale
import com.viktoriagavrosh.home.fake.FakeSource
import com.viktoriagavrosh.repositories.TaleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeTaleRepository : TaleRepository {

    private val fakeListTales = FakeSource().fakeListTales

    override fun getAllTales(genre: String): Flow<List<Tale>> = flow {
        emit(fakeListTales.filter { it.genre == genre })
    }

    override fun getAllFavoriteTales(genre: String): Flow<List<Tale>> = flow {
        emit(fakeListTales.filter { it.genre == genre && it.isFavorite })
    }

    override fun getTaleById(id: Int): Flow<Tale> = flow {
        emit(fakeListTales.first { it.id == id })
    }

    override suspend fun updateFavoriteTale(id: Int, isFavorite: Boolean) {
        fakeListTales[id] = fakeListTales[id]
            .copy(isFavorite = isFavorite)
    }
}

