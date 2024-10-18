package com.viktoriagavrosh.riddle.model

/**
 * Model represents a single riddle for ui - RiddleScreen
 *
 * @param id unique object identifier
 * @param title riddle`s title
 * @param text  riddle's text
 * @param imageUrl url of image for riddle
 */
data class ReadRiddle(
    val id: Int = 1,
    val title: String = "",
    val text: String = "",
    val answer: String = "",
    val imageUrl: String? = null,
)
