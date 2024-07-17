package com.viktoriagavrosh.details.model

/**
 * Model represents a single tale
 */
data class FolkWorkUiDetails(
    val id: Int = 0,
    val genre: String = "",
    val title: String = "",
    val text: String = "",
    val answer: String? = null,
    val imageUri: String? = null,
    val audioUri: String? = null,
    val isFavorite: Boolean = false
)
