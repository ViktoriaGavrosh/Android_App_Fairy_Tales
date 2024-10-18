package com.viktoriagavrosh.reader.model

import com.viktoriagavrosh.repositories.utils.ShelfGenre

/**
 * Model represents a single book for ui - ReaderScreen
 *
 * @param id unique object identifier
 * @param genre genre of book
 * @param title book`s title
 * @param text  book's text
 * @param imageUrl url of image for book
 */
data class ReadBook(
    val id: Int = 1,
    val genre: ShelfGenre = ShelfGenre.Tales.Fairy,
    val title: String = "",
    val text: String = "",
    val imageUrl: String? = null,
)
