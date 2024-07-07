package com.viktoriagavrosh.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity data class represents a single row in the database
 */
@Entity(tableName = "library")
data class FolkWorkDB(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val genre: String,
    val title: String,
    val text: String,
    val answer: String?,
    @ColumnInfo(name = "image_uri") val imageUri: String?,
    @ColumnInfo(name = "audio_uri") val audioUri: String?,
    @ColumnInfo(name = "is_favorite") val isFavorite: Boolean
)
