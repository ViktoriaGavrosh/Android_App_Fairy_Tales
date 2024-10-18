package com.viktoriagavrosh.repositories.fake

import com.viktoriagavrosh.database.dao.TaleDao
import com.viktoriagavrosh.database.model.TaleDb
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class FakeTaleDao : TaleDao {

    private val tales = FakeSource.fakeListTaleDb.toMutableList()

    override fun getAllTales(genre: String): Flow<List<TaleDb>> {
        return if (genre == "error") {
            throw IllegalArgumentException()
        } else {
            flow { emit(tales.filter { it.genre == genre }) }
        }
    }

    override fun getFavoriteTales(): Flow<List<TaleDb>> {
        return flow { emit(tales.filter { it.isFavorite }) }
    }

    override fun getNightTales(): Flow<List<TaleDb>> {
        return flow { emit(tales.filter { it.isNight }) }
    }


    override fun getTaleById(id: Int): Flow<TaleDb> {
        return flow {
            emit(tales.first { it.id == id })
        }
    }

    override fun getAllTaleId(): Flow<List<Int>> {
        return flow {
            emit(tales.map { it.id })
        }
    }

    override suspend fun updateFavoriteTale(id: Int, isFavorite: Int) {
        val newValue = isFavorite == 1
        val newTale = tales[id].copy(isFavorite = newValue)
        tales[id] = newTale
    }

    override suspend fun insert(tale: TaleDb) {
        tales.add(tale)
    }

}
