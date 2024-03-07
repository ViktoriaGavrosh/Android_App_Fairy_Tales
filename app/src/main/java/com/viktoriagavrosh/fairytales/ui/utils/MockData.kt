package com.viktoriagavrosh.fairytales.ui.utils

import com.viktoriagavrosh.fairytales.model.FolkWork

object MockData {
    val fakeFolkWork = FolkWork(
        id = 0,
        genre = "story",
        title = "Story cat cat cat cat cat",
        text = "This is a very large story about one day.",
        answer = null,
        imageUri = null,
        audioUri = null,
        isFavorite = false
    )
}