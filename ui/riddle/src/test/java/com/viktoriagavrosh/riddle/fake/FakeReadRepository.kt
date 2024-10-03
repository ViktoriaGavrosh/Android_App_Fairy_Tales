package com.viktoriagavrosh.riddle.fake

import com.viktoriagavrosh.repositories.ReadRepository
import com.viktoriagavrosh.repositories.model.Retaining
import com.viktoriagavrosh.repositories.utils.RequestResult
import com.viktoriagavrosh.repositories.utils.ShelfGenre
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class FakeReadRepository : ReadRepository {

    override fun getBookById(id: Int, genre: ShelfGenre): Flow<RequestResult<Retaining>> {
        return try {
            val riddle = FakeSource.fakeRiddles.first { it.id == id }
            flow { emit(RequestResult.Success(riddle)) }
        } catch (e: Exception) {
            flow { emit(RequestResult.Error()) }
        }
    }

    override fun getTextSize(): Flow<RequestResult<Float>> {
        return flow {
            emit(RequestResult.Success(FakeSource.fakeTextSize))
        }
    }
}
