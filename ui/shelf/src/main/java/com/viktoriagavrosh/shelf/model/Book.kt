package com.viktoriagavrosh.shelf.model

import com.viktoriagavrosh.repositories.utils.ShelfGenre

/**
 * Model represents a single book for ui - ShelfScreen
 *
 * @param id unique object identifier
 * @param genre genre of book
 * @param title book`s title
 * @param imageUrl url of image for book
 * @param isFavorite boolean parameter describes tale is favorite
 */
data class Book(
    val id: Int = 0,
    val genre: ShelfGenre = ShelfGenre.Riddles,
    val title: String = "",
    val imageUrl: String? = null,
    val isFavorite: Boolean = false
)
