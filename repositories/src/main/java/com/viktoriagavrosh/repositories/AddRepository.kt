package com.viktoriagavrosh.repositories

import com.viktoriagavrosh.database.TaleAppDatabase
import com.viktoriagavrosh.repositories.model.Tale
import com.viktoriagavrosh.repositories.utils.toTaleDb
import jakarta.inject.Inject

/**
 * Repository that provides insert, update, delete and retrieve of items from a given data source.
 */

interface AddRepository {

    /**
     * Update the value of an item field is_favorite in the data source
     */
    suspend fun addTale(tale: Tale)
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
}
