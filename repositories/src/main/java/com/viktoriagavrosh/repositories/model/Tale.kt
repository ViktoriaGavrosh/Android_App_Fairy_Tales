package com.viktoriagavrosh.fairytales.model

/**
 * Model represents a single tale
 */
data class Tale(
    val id: Int = 0,
    val genre: String,
    val title: String,
    val text: String,
    val answer: String?,
    val imageUri: String?,
    val audioUri: String?,
    val isFavorite: Boolean
)
