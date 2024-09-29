package com.viktoriagavrosh.repositories

import com.viktoriagavrosh.database.TaleAppDatabase
import com.viktoriagavrosh.database.dao.FolkDao
import com.viktoriagavrosh.database.model.TaleDb
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal object FakeSource {
    val fakeListTaleDb = List(4) {
        TaleDb(
            id = it,
            genre = "story",
            title = "Title$it",
            text = "Text",
            answer = null,
            imageUrl = null,
            audioUrl = null,
            isFavorite = it / 2 == 0
        )
    }

    internal class FakeDb : TaleAppDatabase {
        override val taleDao: FolkDao = FakeDao()

    }

    private class FakeDao : FolkDao {
        override fun getAllTales(genre: String): Flow<List<TaleDb>> {
            return if (genre == "error") {
                throw IllegalArgumentException()
            } else {
                flow { emit(fakeListTaleDb.filter { it.genre == genre }) }
            }
        }

        override fun getAllFavoriteTales(genre: String): Flow<List<TaleDb>> {
            return flow { emit(fakeListTaleDb.filter { it.isFavorite }) }
        }

        override fun getTaleById(id: Int): Flow<TaleDb> {
            return flow {
                emit(fakeListTaleDb.first { it.id == id })
            }
        }

        override suspend fun updateFavoriteTale(id: Int, isFavorite: Int) {
            TODO("Not yet implemented")
        }

        override suspend fun insert(tale: TaleDb) {
            TODO("Not yet implemented")
        }

    }
}
