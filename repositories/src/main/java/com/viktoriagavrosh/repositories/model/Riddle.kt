package com.viktoriagavrosh.repositories.model

import com.viktoriagavrosh.repositories.utils.ShelfGenre

/**
 * Model represents a single riddle for Repositories module
 */
data class Riddle(
    val id: Int = 0,
    val genre: ShelfGenre = ShelfGenre.Riddles,
    val title: String = "",
    val text: String = "",
    val answer: String = "",
    val imageUrl: String? = null,
) : Retaining
