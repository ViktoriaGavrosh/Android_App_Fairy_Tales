package com.viktoriagavrosh.repositories.model

import com.viktoriagavrosh.repositories.utils.ShelfGenre

/**
 * Model represents a single tale for repository
 *
 * @param id unique object identifier
 * @param genre genre of tale
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
data class Tale(
    val id: Int = 0,
    val genre: ShelfGenre = ShelfGenre.Tales.Animal,
    val title: String = "",
    val text: String = "",
    val isFavorite: Boolean = false,
    val isNight: Boolean = false,
    val isChangeable: Boolean = false,
    val imageUrl: String? = null,
    val audioUrl: String? = null,
    val quizUrl: String? = null,
    val questionsUrl: String? = null,
    val gameUrl: String? = null,
) : Retaining
