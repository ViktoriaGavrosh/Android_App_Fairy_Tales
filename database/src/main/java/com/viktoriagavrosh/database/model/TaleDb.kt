package com.viktoriagavrosh.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Model represents a single tale given from database
 *
 * @param id unique object identifier
 * @param genre name of tale genre
 * @param title tale`s title
 * @param text tale`s text
 * @param isFavorite boolean parameter describes tale is favorite
 * @param isNight boolean parameter describes tale is night
 * @param isChangeable boolean parameter describes tale can be changed
 * @param imageUrl url of image for tale
 * @param audioUrl url of audio for tale
 * @param quizUrl url of quiz for tale
 * @param questionsUrl url of questions for tale
 * @param gameUrl url of game for tale
 */
@Entity(tableName = "tale")
data class TaleDb(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "genre") val genre: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "text") val text: String,
    @ColumnInfo(name = "is_favorite") val isFavorite: Boolean,
    @ColumnInfo(name = "is_night") val isNight: Boolean,
    @ColumnInfo(name = "is_changeable") val isChangeable: Boolean,
    @ColumnInfo(name = "image_url") val imageUrl: String?,
    @ColumnInfo(name = "audio_url") val audioUrl: String?,
    @ColumnInfo(name = "quiz_url") val quizUrl: String?,
    @ColumnInfo(name = "questions_url") val questionsUrl: String?,
    @ColumnInfo(name = "game_url") val gameUrl: String?,
)
