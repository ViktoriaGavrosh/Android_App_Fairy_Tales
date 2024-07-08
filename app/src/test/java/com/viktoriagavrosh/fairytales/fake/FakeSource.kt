package com.viktoriagavrosh.fairytales.fake

import com.viktoriagavrosh.fairytales.model.Tale

class FakeSource(
    val fakeListFolkWork: MutableList<Tale> = MutableList(4) {
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
