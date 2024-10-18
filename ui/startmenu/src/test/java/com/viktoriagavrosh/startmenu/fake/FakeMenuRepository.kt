package com.viktoriagavrosh.startmenu.fake

import com.viktoriagavrosh.repositories.MenuRepository
import com.viktoriagavrosh.repositories.model.Tale
import com.viktoriagavrosh.repositories.utils.RequestResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class FakeMenuRepository : MenuRepository {

    private val fakeListTales = FakeSource.fakeTales.toMutableList()
    private var fakeLastTaleId = FakeSource.fakeLastTaleId

    override fun getTaleById(id: Int): Flow<RequestResult<Tale>> {
        return try {
            val tale = fakeListTales.first { it.id == id }
            flow { emit(RequestResult.Success(tale)) }
        } catch (e: Exception) {
            flow { emit(RequestResult.Error()) }
        }
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
