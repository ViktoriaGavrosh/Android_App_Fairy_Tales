package com.viktoriagavrosh.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.viktoriagavrosh.database.model.RiddleDb
import kotlinx.coroutines.flow.Flow

/**
 * Database access object to access the App Database
 */
@Dao
interface RiddleDao {

    @Query("SELECT * FROM riddle")
    fun getAllRiddles(): Flow<List<RiddleDb>>

    @Query("SELECT * FROM riddle WHERE id = :id")
    fun getRiddleById(id: Int): Flow<RiddleDb>
}
