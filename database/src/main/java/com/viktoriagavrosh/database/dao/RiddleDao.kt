package com.viktoriagavrosh.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.viktoriagavrosh.database.model.RiddleDb
import kotlinx.coroutines.flow.Flow

/**
 * Interface for working with Room. It works with "riddle" table from DB
 */
@Dao
interface RiddleDao {

    /**
     * Return all rows from riddle table by genre
     *
     * @return flow of list [RiddleDb]
     */
    @Query("SELECT * FROM riddle")
    fun getAllRiddles(): Flow<List<RiddleDb>>

    /**
     * Return one row from riddle table by id
     *
     * @param id unique object identifier
     * @return flow of [RiddleDb]
     */
    @Query("SELECT * FROM riddle WHERE id = :id")
    fun getRiddleById(id: Int): Flow<RiddleDb>

    /**
     * will insert element into the database (riddle table)
     *
     * @param riddleDb object [RiddleDb] that will be insert
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(riddleDb: RiddleDb)
}
