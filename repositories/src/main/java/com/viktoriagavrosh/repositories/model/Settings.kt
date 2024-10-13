package com.viktoriagavrosh.repositories.model

/**
 * Model represents a settings item for repository
 *
 * @param textSize parameter for text
 * @param lastTaleId id of tale that was shown last
 */
data class Settings(
    val textSize: Float = 0.0F,
    val lastTaleId: Int = 0,
)
