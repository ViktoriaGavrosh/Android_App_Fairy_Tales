package com.viktoriagavrosh.addtale.fake

import com.viktoriagavrosh.repositories.AddRepository
import com.viktoriagavrosh.repositories.model.Tale
import com.viktoriagavrosh.repositories.utils.RequestResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class FakeAddRepository : AddRepository {

    private val tales = FakeSource.fakeTales.toMutableList()

    override suspend fun addTale(tale: Tale) {
        tales.add(tale)
    }

    override fun getTaleById(id: Int): Flow<RequestResult<Tale>> {
        return try {
            val tale = tales.first { it.id == id }
            flow { emit(RequestResult.Success(tale)) }
        } catch (e: Exception) {
            flow { emit(RequestResult.Error()) }
        }
    }
}
