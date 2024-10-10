package com.viktoriagavrosh.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.viktoriagavrosh.database.model.FolkDb
import kotlinx.coroutines.flow.Flow

/**
 * Database access object to access the App Database
 */
@Dao
interface FolkDao {

    @Query("SELECT * FROM folk WHERE genre = :genre")
    fun getAllFolks(genre: String): Flow<List<FolkDb>>

    @Query("SELECT * FROM folk WHERE id = :id")
    fun getFolkById(id: Int): Flow<FolkDb>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(folkDb: FolkDb)
}
