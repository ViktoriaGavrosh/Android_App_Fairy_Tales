package com.viktoriagavrosh.fairytales.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity data class represents a single row in te database
 */
@Entity(tableName = "library")
data class TaleDb(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "genre") val genre: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "text") val text: String,
    @ColumnInfo(name = "answer") val answer: String?,
    @ColumnInfo(name = "image_uri") val imageUrl: String?,
    @ColumnInfo(name = "audio_uri") val audioUrl: String?,
    @ColumnInfo(name = "is_favorite") val isFavorite: Boolean,
)
