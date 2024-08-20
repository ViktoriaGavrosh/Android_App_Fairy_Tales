package com.viktoriagavrosh.fairytales.model

import com.viktoriagavrosh.fairytales.ui.elements.Genre

/**
 * Model represents a single tale for UI
 */
data class TaleUi(
    val id: Int = 0,
    val genre: Genre = Genre.Story,
    val title: String = "",
    val text: String = "",
    val answer: String? = null,
    val imageUrl: String? = null,
    val audioUrl: String? = null,
    val isFavorite: Boolean = false,
)
