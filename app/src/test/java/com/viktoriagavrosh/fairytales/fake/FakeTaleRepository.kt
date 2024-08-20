package com.viktoriagavrosh.fairytales.fake

import com.viktoriagavrosh.fairytales.data.repositories.TaleRepository
import com.viktoriagavrosh.fairytales.data.repositories.utils.RequestResult
import com.viktoriagavrosh.fairytales.model.Tale
import com.viktoriagavrosh.fairytales.ui.elements.Genre
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeTaleRepository : TaleRepository {
    override fun getTales(genre: Genre, isFavorite: Boolean): Flow<RequestResult<List<Tale>>> {
        if (genre == Genre.Lullaby) throw IllegalArgumentException()
        return flow {
            emit(
                RequestResult.Success(
                    FakeData.fakeListTales.filter { it.genre == genre })
            )
        }
    }

    override fun getTaleById(id: Int): Flow<RequestResult<Tale>> {
        return flow {
            emit(RequestResult.Success(FakeData.fakeListTales[id]))
        }
    }

    override suspend fun updateFavoriteTale(id: Int, isFavorite: Boolean) {
        TODO("Not yet implemented")
    }
}
