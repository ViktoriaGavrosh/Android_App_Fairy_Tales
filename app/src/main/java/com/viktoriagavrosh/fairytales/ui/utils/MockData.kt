package com.viktoriagavrosh.fairytales.ui.utils

import com.viktoriagavrosh.fairytales.model.FolkWork

/**
 * Object for preview and testing
 */
object MockData {
    val fakeTale = FolkWork(
        id = 0,
        genre = "story",
        title = "Story",
        text = "This is a very large story about one day.",
        answer = null,
        imageUri = null,
        audioUri = null,
        isFavorite = false
    )
}
