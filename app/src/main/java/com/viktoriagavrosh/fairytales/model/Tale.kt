package com.viktoriagavrosh.fairytales.model

import com.viktoriagavrosh.fairytales.ui.elements.Genre

/**
 * Model represents a single tale in [TaleRepository]
 */
data class Tale(
    val id: Int = 0,
    val genre: Genre,
    val title: String,
    val text: String,
    val answer: String?,
    val imageUrl: String?,
    val audioUrl: String?,
    val isFavorite: Boolean,
)
