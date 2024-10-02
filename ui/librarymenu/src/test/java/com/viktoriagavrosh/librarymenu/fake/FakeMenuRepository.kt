package com.viktoriagavrosh.librarymenu.fake

import com.viktoriagavrosh.repositories.MenuRepository
import com.viktoriagavrosh.repositories.model.Tale
import com.viktoriagavrosh.repositories.utils.RequestResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class FakeMenuRepository : MenuRepository {

    private val fakeListTales = FakeSource.fakeTales.toMutableList()
    private var fakeLastTaleId = FakeSource.fakeLastTaleId

    override fun getTaleById(id: Int): Flow<RequestResult<Tale>> {
        val a: Flow<RequestResult<Tale>> =
            flow {
                try {
                    emit(RequestResult.Success(fakeListTales.first { it.id == id }))
                } catch (e: Exception) {
                    emit(RequestResult.Error())
                }
            }
        return a
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
