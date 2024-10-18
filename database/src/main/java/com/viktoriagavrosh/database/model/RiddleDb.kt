package com.viktoriagavrosh.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Model represents a single riddle given from database
 *
 * @param id unique object identifier
 * @param title riddle`s title
 * @param text riddle`s text
 * @param answer riddle`s answer
 * @param imageUrl url of image for riddle
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
