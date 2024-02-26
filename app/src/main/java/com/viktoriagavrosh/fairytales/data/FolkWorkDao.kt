package com.viktoriagavrosh.fairytales.data

import androidx.room.Dao
import androidx.room.Query
import com.viktoriagavrosh.fairytales.model.FolkWork
import kotlinx.coroutines.flow.Flow

@Dao
interface FolkWorkDao {
    @Query("SELECT * FROM library WHERE genre = :genre")
    fun getAllWorks(genre: String): Flow<List<FolkWork>>

}