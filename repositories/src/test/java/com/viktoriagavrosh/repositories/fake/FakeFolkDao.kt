package com.viktoriagavrosh.repositories.fake

import com.viktoriagavrosh.database.dao.FolkDao
import com.viktoriagavrosh.database.model.FolkDb
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class FakeFolkDao : FolkDao {

    private val folks = FakeSource.fakeListFolkDb.toMutableList()

    override fun getAllFolks(genre: String): Flow<List<FolkDb>> {
        return if (genre == "error") {
            throw IllegalArgumentException()
        } else {
            flow { emit(folks.filter { it.genre == genre }) }
        }
    }

    override fun getFolkById(id: Int): Flow<FolkDb> {
        return flow {
            emit(folks.first { it.id == id })
        }
    }

    override suspend fun insert(folkDb: FolkDb) {
        folks.add(folkDb)
    }
}
