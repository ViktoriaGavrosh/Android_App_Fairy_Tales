package com.viktoriagavrosh.fairytales.model

/**
 * Model represents a single tale        TODO describe that for repository
 */
data class Tale(
    val id: Int = 0,
    val genre: String,
    val title: String,
    val text: String,
    val answer: String?,
    val imageUrl: String?,
    val audioUrl: String?,
    val isFavorite: Boolean,
)
