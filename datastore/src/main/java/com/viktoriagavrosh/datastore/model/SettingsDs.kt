package com.viktoriagavrosh.datastore.model

/**
 * Model represents a settings item given from datastore
 *
 * @param textSize parameter for text
 * @param lastTaleId id of tale that was shown last
 */
data class SettingsDs(
    val textSize: Float,
    val lastTaleId: Int,
)
