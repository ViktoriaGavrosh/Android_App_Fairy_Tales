package com.viktoriagavrosh.repositories.model

import com.viktoriagavrosh.repositories.utils.ShelfGenre

/**
 * Model represents a single tale for Repositories module
 */
data class Tale(
    val id: Int = 0,
    val genre: ShelfGenre = ShelfGenre.Tales.Animal,
    val title: String = "",
    val text: String = "",
    val isFavorite: Boolean = false,
    val isNight: Boolean = false,
    val imageUrl: String? = null,
    val audioUrl: String? = null,
    val quizUrl: String? = null,
    val questionsUrl: String? = null,
    val gameUrl: String? = null,
) : Retaining
