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
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

/**
 * provide data for ui from data source
 */
interface ReadRepository {

    /**
     * Retrieve book from the given data source by id
     *
     * @param id unique object identifier
     * @param genre name of book`s genre
     * @return flow of [RequestResult] of [Retaining]
     */
    fun getBookById(id: Int, genre: ShelfGenre): Flow<RequestResult<Retaining>>

    /**
     * Retrieve textSize setting value from given datasource
     *
     * @return flow of [RequestResult] of value
     */
    fun getTextSize(): Flow<RequestResult<Float>>
}

/**
 * provide data for ui from local database and Datastore
 *
 * @param appDatabase instance of [TaleAppDatabase]
 * @param preferencesManager instance of [PreferencesManager] for working with Datastore
 */
class OfflineReadRepository @Inject constructor(
    private val appDatabase: TaleAppDatabase,
    private val preferencesManager: PreferencesManager,
) : ReadRepository {

    /**
     * Retrieve book from [TaleAppDatabase] by id
     *
     * @param id unique object identifier
     * @param genre name of book`s genre
     * @return flow of [RequestResult] of [Retaining]
     */
    override fun getBookById(id: Int, genre: ShelfGenre): Flow<RequestResult<Retaining>> {
        return when (genre) {
            ShelfGenre.Riddles -> getRiddleById(id)

            ShelfGenre.Favorites,
            ShelfGenre.Nights,
            ShelfGenre.Tales.Animal,
            ShelfGenre.Tales.Fairy,
            ShelfGenre.Tales.People -> getTaleById(id)

            ShelfGenre.Folks.Poem,
            ShelfGenre.Folks.Counting,
            ShelfGenre.Folks.Lullaby -> getFolkById(id)
        }
    }

    /**
     * Retrieve textSize setting value from Datastore
     *
     * @return flow of [RequestResult] of value
     */
    override fun getTextSize(): Flow<RequestResult<Float>> {
        return try {
            preferencesManager.getSettings()
                .map { it.textSize }
                .map<Float, RequestResult<Float>> { RequestResult.Success(it) }
        } catch (e: Exception) {
            flow {
                emit(RequestResult.Error(error = e))
            }
        }
    }

    private fun getRiddleById(id: Int): Flow<RequestResult<Riddle>> {
        return getDataFromDb(
            getFunction = { appDatabase.riddleDao.getRiddleById(id) },
            mapFunction = { it.toRiddle() }
        )
    }

    private fun getTaleById(id: Int): Flow<RequestResult<Tale>> {
        return getDataFromDb(
            getFunction = { appDatabase.taleDao.getTaleById(id) },
            mapFunction = { it.toTale() }
        )
    }

    private fun getFolkById(id: Int): Flow<RequestResult<Folk>> {
        return getDataFromDb(
            getFunction = { appDatabase.folkDao.getFolkById(id) },
            mapFunction = { it.toFolk() }
        )
    }

    private fun <T, S : Any> getDataFromDb(
        getFunction: () -> Flow<T>,
        mapFunction: (T) -> S
    ): Flow<RequestResult<S>> {
        val request = getFunction()
            .map { mapFunction(it) }
            .map<S, RequestResult<S>> { RequestResult.Success(it) }
            .catch {
                emit(RequestResult.Error(error = it))
            }
        return request
    }
}
