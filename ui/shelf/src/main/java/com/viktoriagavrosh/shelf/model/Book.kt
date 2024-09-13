package com.viktoriagavrosh.shelf.model

import com.viktoriagavrosh.repositories.utils.ShelfGenre

/**
 * Model represents a single tale on HomeScreen
 */
data class Book(
    val id: Int = 0,
    val genre: ShelfGenre = ShelfGenre.Riddles,
    val title: String = "",
    val imageUrl: String? = null,
    val isFavorite: Boolean = false
)
