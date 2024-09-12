package com.viktoriagavrosh.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity data class represents a single Tale for database module
 */
@Entity(tableName = "tale")
data class TaleDb(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")val id: Int = 0,
    @ColumnInfo(name = "genre") val genre: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "text") val text: String,
    @ColumnInfo(name = "is_favorite") val isFavorite: Boolean,
    @ColumnInfo(name = "is_night") val isNight: Boolean,
    @ColumnInfo(name = "image_url") val imageUrl: String?,
    @ColumnInfo(name = "audio_url") val audioUrl: String?,
    @ColumnInfo(name = "quiz_url") val quizUrl: String?,
    @ColumnInfo(name = "questions_url") val questionsUrl: String?,
    @ColumnInfo(name = "game_url") val gameUrl: String?,
)
