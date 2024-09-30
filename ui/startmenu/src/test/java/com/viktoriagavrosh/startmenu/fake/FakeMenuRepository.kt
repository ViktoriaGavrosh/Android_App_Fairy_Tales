package com.viktoriagavrosh.startmenu.fake

import com.viktoriagavrosh.repositories.MenuRepository
import com.viktoriagavrosh.repositories.model.Tale
import com.viktoriagavrosh.repositories.utils.RequestResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeMenuRepository : MenuRepository {

    private val fakeListTales = FakeSource.fakeTales.toMutableList()
    private var fakeLastTaleId = FakeSource.fakeLastTaleId

    override fun getTaleById(id: Int): Flow<RequestResult<Tale>> = flow {
        emit(RequestResult.Success(fakeListTales.first { it.id == id }))
    }

    override suspend fun updateFavoriteTale(id: Int, isFavorite: Boolean) {
        fakeListTales[id] = fakeListTales[id]
            .copy(isFavorite = isFavorite)
    }

    override suspend fun updateLastTaleId(id: Int) {
        fakeLastTaleId = id
    }

    override fun getLastTaleId(): Flow<RequestResult<Int>> {
        return flow {
            emit(RequestResult.Success(fakeLastTaleId))
        }
    }

    override fun getRandomTaleId(): Flow<RequestResult<Int>> {
        return flow {
            emit(RequestResult.Success(fakeListTales.random().id))
        }
    }
}
