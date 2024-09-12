package com.viktoriagavrosh.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.viktoriagavrosh.database.model.FolkDb
import com.viktoriagavrosh.database.model.RiddleDb
import com.viktoriagavrosh.database.model.TaleDb
import kotlinx.coroutines.flow.Flow

/**
 * Database access object to access the App Database
 */
@Dao
interface AppDao {
    // Tale table
    @Query("SELECT * FROM tale WHERE genre = :genre")
    fun getAllTales(genre: String): Flow<List<TaleDb>>

    @Query("SELECT * FROM tale WHERE genre = :genre AND is_favorite = 1")
    fun getAllFavoriteTales(genre: String): Flow<List<TaleDb>>

    @Query("SELECT * FROM tale WHERE id = :id")
    fun getTaleById(id: Int): Flow<TaleDb>

    @Query("SELECT id FROM tale")
    fun getAllTaleId(): Flow<List<Int>>

    @Query("UPDATE tale SET is_favorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteTale(id: Int, isFavorite: Int)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(tale: TaleDb)

    // Riddle table
    @Query("SELECT * FROM riddle")
    fun getAllRiddles(): Flow<List<RiddleDb>>

    @Query("SELECT * FROM riddle WHERE id = :id")
    fun getRiddleById(id: Int): Flow<RiddleDb>

    // Folk table
    @Query("SELECT * FROM folk WHERE genre = :genre")
    fun getAllFolks(genre: String): Flow<List<FolkDb>>

    @Query("SELECT * FROM folk WHERE id = :id")
    fun getFolkById(id: Int): Flow<FolkDb>

}
