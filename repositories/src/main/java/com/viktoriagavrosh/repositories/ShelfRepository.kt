package com.viktoriagavrosh.repositories

import com.viktoriagavrosh.database.TaleAppDatabase
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
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

/**
 * Repository that provides insert, update, delete and retrieve of items from a given data source.
 */

interface ShelfRepository {
    /**
     * Retrieve all the items from the given data source by genre
     */
    fun getItems(
        genre: ShelfGenre
    ): Flow<RequestResult<List<Retaining>>>

    /**
     * Update the value of an item field is_favorite in the data source
     */
    suspend fun updateFavoriteTale(id: Int, isFavorite: Boolean)
}

/**
 * [ShelfRepository] implementation that provides functions for working with the database
 */
class OfflineShelfRepository @Inject constructor(
    private val appDatabase: TaleAppDatabase
) : ShelfRepository {

    /**
     * Retrieve all the items from db by genre
     */
    override fun getItems(
        genre: ShelfGenre
    ): Flow<RequestResult<List<Retaining>>> {
        return when (genre) {
            ShelfGenre.Riddles -> getAllRiddles()
            ShelfGenre.Favorites -> getFavoriteTales()
            ShelfGenre.Nights -> getNightTales()

            ShelfGenre.Tales.Animal,
            ShelfGenre.Tales.Fairy,
            ShelfGenre.Tales.People -> getAllTales(genre)

            ShelfGenre.Folks.Poem,
            ShelfGenre.Folks.Counting,
            ShelfGenre.Folks.Lullaby -> getAllFolks(genre)
        }
    }

    /**
     * Update the value of Tale field is_favorite in db
     */
    override suspend fun updateFavoriteTale(id: Int, isFavorite: Boolean) {
        val isFavoriteValue = if (isFavorite) 1 else 0
        appDatabase.taleDao.updateFavoriteTale(id = id, isFavorite = isFavoriteValue)
    }

    private fun getAllRiddles(): Flow<RequestResult<List<Riddle>>> {
        return getDataFromDb(
            getFun = { appDatabase.taleDao.getAllRiddles() },
            mapFun = { it.map { riddleDb -> riddleDb.toRiddle() } }
        )
    }

    private fun getAllTales(
        genre: ShelfGenre
    ): Flow<RequestResult<List<Tale>>> {
        return getDataFromDb(
            getFun = { appDatabase.taleDao.getAllTales(genre.genreName) },
            mapFun = { it.map { taleDb -> taleDb.toTale() } }
        )
    }

    private fun getFavoriteTales(): Flow<RequestResult<List<Tale>>> {
        return getDataFromDb(
            getFun = { appDatabase.taleDao.getFavoriteTales() },
            mapFun = { it.map { taleDb -> taleDb.toTale() } }
        )
    }

    private fun getNightTales(): Flow<RequestResult<List<Tale>>> {
        return getDataFromDb(
            getFun = { appDatabase.taleDao.getNightTales() },
            mapFun = { it.map { taleDb -> taleDb.toTale() } }
        )
    }

    private fun getAllFolks(genre: ShelfGenre): Flow<RequestResult<List<Folk>>> {
        return getDataFromDb(
            getFun = { appDatabase.taleDao.getAllFolks(genre.genreName) },
            mapFun = { it.map { folkDb -> folkDb.toFolk() } }
        )
    }

    private fun <T, S : Any> getDataFromDb(
        getFun: () -> Flow<T>,
        mapFun: (T) -> S
    ): Flow<RequestResult<S>> {
        val request = try {
            getFun()
                .map { mapFun(it) }
                .map<S, RequestResult<S>> { RequestResult.Success(it) }
        } catch (e: Exception) {
            flow {
                emit(RequestResult.Error(error = e))
            }
        }
        return request
    }
}
