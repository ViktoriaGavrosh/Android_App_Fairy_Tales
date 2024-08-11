package com.viktoriagavrosh.home.model

/**
 * Model represents a single tale on HomeScreen
 */
data class TaleUiHome(
    val id: Int = 0,
    val genre: String = "",
    val title: String = "",
    val text: String = "",
    val answer: String? = null,
    val imageUri: String? = null,
    val audioUri: String? = null,
    val isFavorite: Boolean = false
)
