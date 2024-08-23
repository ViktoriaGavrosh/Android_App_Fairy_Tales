package com.viktoriagavrosh.fairytales.fake

import com.viktoriagavrosh.fairytales.data.repositories.TaleRepository
import com.viktoriagavrosh.fairytales.data.repositories.utils.RequestResult
import com.viktoriagavrosh.fairytales.model.Tale
import com.viktoriagavrosh.fairytales.ui.elements.Genre
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeTaleRepository : TaleRepository {

    private val fakeTales = FakeData.fakeListTales.toMutableList()

    override fun getTales(genre: Genre, isFavorite: Boolean): Flow<RequestResult<List<Tale>>> {
        if (genre == Genre.Lullaby) throw IllegalArgumentException()
        return flow {
            emit(
                if (isFavorite) {
                    RequestResult.Success(
                        fakeTales.filter { it.genre == genre }
                            .filter { it.isFavorite }
                    )
                } else {
                    RequestResult.Success(
                        fakeTales.filter { it.genre == genre }
                    )
                }

            )
        }
    }

    override fun getTaleById(id: Int): Flow<RequestResult<Tale>> {
        return flow {
            emit(RequestResult.Success(fakeTales[id]))
        }
    }

    override suspend fun updateFavoriteTale(id: Int, isFavorite: Boolean) {
        fakeTales[id] = fakeTales[id].copy(isFavorite = isFavorite)
    }
}
