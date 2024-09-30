package com.viktoriagavrosh.repositories.fake

import com.viktoriagavrosh.database.dao.FolkDao
import com.viktoriagavrosh.database.dao.RiddleDao
import com.viktoriagavrosh.database.model.FolkDb
import com.viktoriagavrosh.database.model.RiddleDb
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class FakeRiddleDao : RiddleDao {

    private val riddles = FakeSource.fakeListRiddleDb.toMutableList()

    override fun getAllRiddles(): Flow<List<RiddleDb>> {
        return flow { emit(riddles) }
    }

    override fun getRiddleById(id: Int): Flow<RiddleDb> {
        return flow {
            emit(riddles.first { it.id == id })
        }
    }

    override suspend fun insert(riddleDb: RiddleDb) {
        riddles.add(riddleDb)
    }
}
