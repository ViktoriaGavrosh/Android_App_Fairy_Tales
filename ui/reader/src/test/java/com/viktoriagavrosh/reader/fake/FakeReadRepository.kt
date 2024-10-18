package com.viktoriagavrosh.reader.fake

import com.viktoriagavrosh.repositories.ReadRepository
import com.viktoriagavrosh.repositories.model.Retaining
import com.viktoriagavrosh.repositories.utils.RequestResult
import com.viktoriagavrosh.repositories.utils.ShelfGenre
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class FakeReadRepository : ReadRepository {

    override fun getBookById(id: Int, genre: ShelfGenre): Flow<RequestResult<Retaining>> {
        return try {
            val book = if (genre is ShelfGenre.Tales) {
                FakeSource.fakeTales.first { it.id == id }
            } else {
                FakeSource.fakeFolks.first { it.id == id }
            }
            flow { emit(RequestResult.Success(book)) }
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
