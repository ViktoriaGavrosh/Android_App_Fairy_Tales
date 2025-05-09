package com.viktoriagavrosh.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.viktoriagavrosh.database.model.TaleDb
import kotlinx.coroutines.flow.Flow

/**
 * Interface for working with Room. It works with "tale" table from DB
 */
@Dao
interface TaleDao {

    /**
     * Return all rows from tale table by genre
     *
     * @param genre name of tale genre
     * @return flow of list [TaleDb]
     */
    @Query("SELECT * FROM tale WHERE genre = :genre")
    fun getAllTales(genre: String): Flow<List<TaleDb>>

    /**
     * Return favorite items from tale table
     *
     * @return flow of list [TaleDb]
     */
    @Query("SELECT * FROM tale WHERE is_favorite = 1")
    fun getFavoriteTales(): Flow<List<TaleDb>>

    /**
     * Return night items from tale table
     *
     * @return flow of list [TaleDb]
     */
    @Query("SELECT * FROM tale WHERE is_night = 1")
    fun getNightTales(): Flow<List<TaleDb>>

    /**
     * Return one row from tale table by id
     *
     * @param id unique object identifier
     * @return flow of [TaleDb]
     */
    @Query("SELECT * FROM tale WHERE id = :id")
    fun getTaleById(id: Int): Flow<TaleDb>

    /**
     * Return ids of all items from tale table
     *
     * @return flow of list [TaleDb]`s id
     */
    @Query("SELECT id FROM tale")
    fun getAllTaleId(): Flow<List<Int>>

    /**
     * Update favorite value of item from tale table by id
     *
     * @param id unique object identifier
     * @param isFavorite new value of [TaleDb]
     */
    @Query("UPDATE tale SET is_favorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteTale(id: Int, isFavorite: Int)

    /**
     * will insert element into the database (tale table)
     *
     * @param tale object [TaleDb] that will be insert
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(tale: TaleDb)
}
