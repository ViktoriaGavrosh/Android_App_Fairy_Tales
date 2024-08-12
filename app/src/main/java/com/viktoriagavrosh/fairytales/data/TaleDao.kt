package com.viktoriagavrosh.fairytales.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.viktoriagavrosh.fairytales.model.FolkWork
import kotlinx.coroutines.flow.Flow

/**
 * Database access object to access the App Database
 */
@Dao
interface TaleDao {
    @Query("SELECT * FROM library WHERE genre = :genre")
    fun getAllTales(genre: String): Flow<List<FolkWork>>

    @Query("SELECT * FROM library WHERE genre = :genre AND is_favorite = 1")
    fun getAllFavoriteTales(genre: String): Flow<List<FolkWork>>

    @Query("UPDATE library SET is_favorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteTale(id: Int, isFavorite: Int)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(folkWork: FolkWork)
}
