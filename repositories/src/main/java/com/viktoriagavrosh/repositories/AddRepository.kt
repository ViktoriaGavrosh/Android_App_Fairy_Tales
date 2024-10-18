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
 * provide data for ui from data source
 */
interface AddRepository {

    /**
     * Insert tale into given data source
     *
     * @param tale instance of [Tale], that user created
     */
    suspend fun addTale(tale: Tale)

    /**
     * Retrieve item from given data source by id
     *
     * @param id unique object identifier
     * @return flow of [RequestResult] with list [Retaining]
     */
    fun getTaleById(id: Int): Flow<RequestResult<Retaining>>
}

/**
 * provide data for ui from local database
 *
 * @param appDatabase instance of [TaleAppDatabase]
 */
class OfflineAddRepository @Inject constructor(
    private val appDatabase: TaleAppDatabase
) : AddRepository {

    /**
     * Insert tale into [TaleAppDatabase]
     *
     * @param tale instance of [Tale], that user created
     */
    override suspend fun addTale(tale: Tale) {
        val taleDb = tale.toTaleDb()
        appDatabase.taleDao.insert(taleDb)
    }

    /**
     * Retrieve item from [TaleAppDatabase] by id
     *
     * @param id unique object identifier
     * @return flow of [RequestResult] with list [Retaining]
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
