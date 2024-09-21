package com.viktoriagavrosh.riddle.model

/**
 * Model represents a single tale
 */
data class ReadRiddle(
    val id: Int = 1,
    val title: String = "",
    val text: String = "",
    val answer: String = "",
    val imageUrl: String? = null,
)
