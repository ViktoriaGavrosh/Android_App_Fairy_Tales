package com.viktoriagavrosh.details.model

import com.viktoriagavrosh.repositories.utils.ShelfGenre

/**
 * Model represents a single tale
 */
data class ReadBook(
    val id: Int = 1,
    val genre: ShelfGenre = ShelfGenre.Tales.Fairy,
    val title: String = "",
    val text: String = "",
    val imageUrl: String? = null,
)
