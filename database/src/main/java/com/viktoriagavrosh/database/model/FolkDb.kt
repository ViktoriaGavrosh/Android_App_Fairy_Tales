package com.viktoriagavrosh.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Model represents a single folk given from database
 *
 * @param id unique object identifier
 * @param genre name of folk genre
 * @param title folk`s title
 * @param text folk`s text
 * @param imageUrl url of image for folk
 */
@Entity(tableName = "folk")
data class FolkDb(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "genre") val genre: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "text") val text: String,
    @ColumnInfo(name = "image_url") val imageUrl: String?,
)
