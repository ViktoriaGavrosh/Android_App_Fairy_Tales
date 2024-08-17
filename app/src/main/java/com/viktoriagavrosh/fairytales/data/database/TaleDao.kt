package com.viktoriagavrosh.fairytales.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.viktoriagavrosh.fairytales.model.TaleDb
import kotlinx.coroutines.flow.Flow

/**
 * Database access object to access to App Database
 */
@Dao
interface TaleDao {

    @Query("SELECT * FROM library WHERE genre = :genre")
    fun getAllTales(genre: String): Flow<List<TaleDb>>

    @Query("SELECT * FROM library WHERE genre = :genre AND is_favorite = 1")
    fun getAllFavoriteTales(genre: String): Flow<List<TaleDb>>

    @Query("SELECT * FROM library WHERE id = :id")
    fun getTaleById(id: Int): Flow<TaleDb>

    @Query("UPDATE library SET is_favorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteTale(id: Int, isFavorite: Int)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(tale: TaleDb)
}
