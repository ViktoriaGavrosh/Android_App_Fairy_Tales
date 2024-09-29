package com.viktoriagavrosh.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.viktoriagavrosh.database.model.TaleDb
import kotlinx.coroutines.flow.Flow

/**
 * Database access object to access the App Database
 */
@Dao
interface TaleDao {

    @Query("SELECT * FROM tale WHERE genre = :genre")
    fun getAllTales(genre: String): Flow<List<TaleDb>>

    @Query("SELECT * FROM tale WHERE is_favorite = 1")
    fun getFavoriteTales(): Flow<List<TaleDb>>

    @Query("SELECT * FROM tale WHERE is_night = 1")
    fun getNightTales(): Flow<List<TaleDb>>

    @Query("SELECT * FROM tale WHERE id = :id")
    fun getTaleById(id: Int): Flow<TaleDb>

    @Query("SELECT id FROM tale")
    fun getAllTaleId(): Flow<List<Int>>

    @Query("UPDATE tale SET is_favorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteTale(id: Int, isFavorite: Int)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(tale: TaleDb)
}
