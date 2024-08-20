package com.viktoriagavrosh.fairytales.fake

import com.viktoriagavrosh.fairytales.data.database.TaleAppDatabase
import com.viktoriagavrosh.fairytales.data.database.TaleDao
import com.viktoriagavrosh.fairytales.model.TaleDb
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object FakeDatabaseSource {

    val fakeListTaleDb: List<TaleDb> = List(4) {
        TaleDb(
            id = it,
            genre = "story",
            title = "Title$it",
            text = "Text",
            answer = null,
            imageUrl = null,
            audioUrl = null,
            isFavorite = it / 2 == 0,
        )
    }

    class FakeDb : TaleAppDatabase {
        override val taleDao: TaleDao = FakeDao()
    }

    class FakeDao : TaleDao {
        override fun getAllTales(genre: String): Flow<List<TaleDb>> {
            return if (genre == "lullaby") {
                throw IllegalArgumentException()
            } else {
                flow { emit(fakeListTaleDb.filter { it.genre == genre }) }
            }
        }

        override fun getAllFavoriteTales(genre: String): Flow<List<TaleDb>> {
            return flow { emit(fakeListTaleDb.filter { it.isFavorite }) }
        }

        override fun getTaleById(id: Int): Flow<TaleDb> {
            return flow { emit(fakeListTaleDb.first { it.id == id }) }
        }

        override suspend fun insert(tale: TaleDb) {
            TODO("Not yet implemented")
        }

        override suspend fun updateFavoriteTale(id: Int, isFavorite: Int) {
            TODO("Not yet implemented")
        }
    }
}
