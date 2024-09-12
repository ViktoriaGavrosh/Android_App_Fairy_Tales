package com.viktoriagavrosh.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity data class represents a single Riddle for database module
 */
@Entity(tableName = "riddle")
data class RiddleDb(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "text") val text: String,
    @ColumnInfo(name = "answer") val answer: String,
    @ColumnInfo(name = "image_url") val imageUrl: String?,
)
