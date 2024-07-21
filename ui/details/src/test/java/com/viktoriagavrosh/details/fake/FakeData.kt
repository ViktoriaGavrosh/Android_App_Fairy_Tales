package com.viktoriagavrosh.details.fake

import com.viktoriagavrosh.repositories.model.Tale

internal object FakeData {
    val fakeTale = Tale(
        id = 0,
        genre = "story",
        title = "title",
        text = "text",
        answer = null,
        imageUri = null,
        audioUri = null,
        isFavorite = false
    )
}
