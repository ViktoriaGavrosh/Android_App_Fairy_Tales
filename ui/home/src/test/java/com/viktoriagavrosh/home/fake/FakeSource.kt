package com.viktoriagavrosh.home.fake

import com.viktoriagavrosh.repositories.model.Tale

class FakeSource(
    val fakeListTales: MutableList<Tale> = MutableList(4) {
        Tale(
            id = it,
            genre = if (it % 2 == 0) "story" else "game",
            title = "title",
            text = "text",
            answer = null,
            imageUri = null,
            audioUri = null,
            isFavorite = false
        )
    }
)
