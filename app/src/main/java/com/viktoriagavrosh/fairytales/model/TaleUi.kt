package com.viktoriagavrosh.fairytales.model

/**
 * Model represents a single tale for UI
 */
data class TaleUi (
    val id: Int = 0,
    val genre: String = "",
    val title: String = "",
    val text: String = "",
    val answer: String? = null,
    val imageUrl: String? = null,
    val audioUrl: String? = null,
    val isFavorite: Boolean = false,
)
