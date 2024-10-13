package com.viktoriagavrosh.repositories.model

import com.viktoriagavrosh.repositories.utils.ShelfGenre

/**
 * Model represents a single riddle for repository
 *
 * @param id unique object identifier
 * @param genre genre of riddle
 * @param title riddle`s title
 * @param text riddle`s text
 * @param answer riddle`s answer
 * @param imageUrl url of image for riddle
 */
data class Riddle(
    val id: Int = 0,
    val genre: ShelfGenre = ShelfGenre.Riddles,
    val title: String = "",
    val text: String = "",
    val answer: String = "",
    val imageUrl: String? = null,
) : Retaining
