package com.viktoriagavrosh.fairytales.data

import androidx.room.Dao
import androidx.room.Query
import com.viktoriagavrosh.fairytales.model.FolkWork
import kotlinx.coroutines.flow.Flow

@Dao
interface FolkWorkDao {
    @Query("SELECT * FROM library WHERE genre = 'story'")
    fun getAllStories(): Flow<List<FolkWork>>

    @Query("SELECT * FROM library WHERE genre = 'game'")
    fun getAllGames(): Flow<List<FolkWork>>
}