package com.viktoriagavrosh.startmenu.fake

import com.viktoriagavrosh.repositories.model.Tale

internal object FakeSource {
    val fakeTales = List(3) {
        val id = it + 1
        Tale(
            id = id,
            title = "Title $id",
            text = "Text $id",
            isFavorite = id % 2 == 0,
        )
    }
    val fakeLastTaleId = 1
}