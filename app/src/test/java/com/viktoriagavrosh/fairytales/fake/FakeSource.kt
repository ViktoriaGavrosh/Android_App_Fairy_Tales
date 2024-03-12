package com.viktoriagavrosh.fairytales.fake

import com.viktoriagavrosh.fairytales.model.FolkWork

class FakeSource(
    val fakeListFolkWork: MutableList<FolkWork> = MutableList(4) {
        FolkWork(
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