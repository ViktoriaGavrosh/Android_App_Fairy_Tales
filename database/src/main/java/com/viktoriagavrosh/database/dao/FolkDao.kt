package com.viktoriagavrosh.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.viktoriagavrosh.database.model.FolkDb
import kotlinx.coroutines.flow.Flow

/**
 * Interface for working with Room. It works with "folk" table from DB
 */
@Dao
interface FolkDao {

    /**
     * Return all rows from folk table by genre
     *
     * @param genre name of folk genre
     * @return flow of list [FolkDb]
     */
    @Query("SELECT * FROM folk WHERE genre = :genre")
    fun getAllFolks(genre: String): Flow<List<FolkDb>>

    /**
     * Return one row from folk table by id
     *
     * @param id unique object identifier
     * @return flow of [FolkDb]
     */
    @Query("SELECT * FROM folk WHERE id = :id")
    fun getFolkById(id: Int): Flow<FolkDb>

    /**
     * will insert element into the database (folk table)
     *
     * @param folkDb object [FolkDb] that will be insert
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(folkDb: FolkDb)
}
