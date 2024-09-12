package com.viktoriagavrosh.repositories

import com.viktoriagavrosh.database.TaleAppDatabase
import com.viktoriagavrosh.datastore.PreferencesManager
import com.viktoriagavrosh.repositories.model.Folk
import com.viktoriagavrosh.repositories.model.Retaining
import com.viktoriagavrosh.repositories.model.Riddle
import com.viktoriagavrosh.repositories.model.Tale
import com.viktoriagavrosh.repositories.utils.RequestResult
import com.viktoriagavrosh.repositories.utils.ShelfGenre
import com.viktoriagavrosh.repositories.utils.toFolk
import com.viktoriagavrosh.repositories.utils.toRiddle
import com.viktoriagavrosh.repositories.utils.toTale
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

/**
 * Repository that provides insert, update, delete and retrieve of items from a given data source.
 */

interface DetailRepository {

    /**
     * Retrieve the item from the given data source by id
     */
    fun getItemById(id: Int, genre: ShelfGenre): Flow<RequestResult<Retaining>>

    /**
     * Retrieve the item from the given data source
     */
    fun getTextSize(): Flow<RequestResult<Float>>
}

/**
 * [DetailRepository] implementation that provides functions for working with the database
 */
class OfflineDetailRepository @Inject constructor(
    private val appDatabase: TaleAppDatabase,
    private val preferencesManager: PreferencesManager,
) : DetailRepository {

    /**
     * Retrieve the item from db by id
     */
    override fun getItemById(id: Int, genre: ShelfGenre): Flow<RequestResult<Retaining>> {
        return when (genre) {
            ShelfGenre.Riddles -> getRiddleById(id)

            ShelfGenre.Tales.Animal,
            ShelfGenre.Tales.Fairy,
            ShelfGenre.Tales.People -> getTaleById(id)

            ShelfGenre.Folks.Poem,
            ShelfGenre.Folks.Counting,
            ShelfGenre.Folks.Lullaby -> getFolkById(id)
        }
    }

    /**
     * Retrieve the item from the given data source
     */
    override fun getTextSize(): Flow<RequestResult<Float>> {
        return preferencesManager.getSettings()
            .map { it.textSize }
            .map<Float, RequestResult<Float>> { RequestResult.Success(it) }
            .catch {
                emit(RequestResult.Error(error = it))
            }
    }

    private fun getRiddleById(id: Int): Flow<RequestResult<Riddle>> {
        return getDataFromDb(
            getFun = { appDatabase.taleDao.getRiddleById(id) },
            mapFun = { it.toRiddle() }
        )
    }

    private fun getTaleById(id: Int): Flow<RequestResult<Tale>> {
        return getDataFromDb(
            getFun = { appDatabase.taleDao.getTaleById(id) },
            mapFun = { it.toTale() }
        )
    }

    private fun getFolkById(id: Int): Flow<RequestResult<Folk>> {
        return getDataFromDb(
            getFun = { appDatabase.taleDao.getFolkById(id) },
            mapFun = { it.toFolk() }
        )
    }

    private fun <T, S : Any> getDataFromDb(
        getFun: () -> Flow<T>,
        mapFun: (T) -> S
    ): Flow<RequestResult<S>> {
        val request = getFun()
            .map { mapFun(it) }
            .map<S, RequestResult<S>> { RequestResult.Success(it) }
            .catch {
                emit(RequestResult.Error(error = it))
            }
        return request
    }
}
