package com.viktoriagavrosh.details.fake

import com.viktoriagavrosh.fairytales.model.Tale

object FakeData {
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
