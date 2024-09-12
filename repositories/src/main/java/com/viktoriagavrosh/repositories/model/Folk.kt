package com.viktoriagavrosh.repositories.model

import com.viktoriagavrosh.repositories.utils.ShelfGenre

/**
 * Model represents a single folk for Repositories module
 */
data class Folk(
    val id: Int = 0,
    val genre: ShelfGenre = ShelfGenre.Folks.Poem,
    val title: String = "",
    val text: String = "",
    val imageUrl: String? = null,
) : Retaining

interface Retaining
