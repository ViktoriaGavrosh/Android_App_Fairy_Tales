package com.viktoriagavrosh.repositories

import com.viktoriagavrosh.database.TaleAppDatabase
import com.viktoriagavrosh.repositories.model.Retaining
import com.viktoriagavrosh.repositories.model.Tale
import com.viktoriagavrosh.repositories.utils.RequestResult
import com.viktoriagavrosh.repositories.utils.toTale
import com.viktoriagavrosh.repositories.utils.toTaleDb
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

/**
 * Repository that provides insert, update, delete and retrieve of items from a given data source.
 */

interface AddRepository {

    /**
     * Update the value of an item field is_favorite in the data source
     */
    suspend fun addTale(tale: Tale)

    /**
     * Retrieve the item from the given data source by id
     */
    fun getTaleById(id: Int): Flow<RequestResult<Retaining>>
}

/**
 * [AddRepository] implementation that provides functions for working with the database
 */
class OfflineAddRepository @Inject constructor(
    private val appDatabase: TaleAppDatabase
) : AddRepository {

    /**
     * Insert new Tale in db
     */
    override suspend fun addTale(tale: Tale) {
        val taleDb = tale.toTaleDb()
        appDatabase.taleDao.insert(taleDb)
    }

    /**
     * Retrieve the item from the given data source by id
     */
    override fun getTaleById(id: Int): Flow<RequestResult<Tale>> {
        val request = appDatabase.taleDao.getTaleById(id)
            .map { it.toTale() }
            .map<Tale, RequestResult<Tale>> { RequestResult.Success(it) }
            .catch {
                emit(RequestResult.Error(error = it))
            }
        return request
    }
}
