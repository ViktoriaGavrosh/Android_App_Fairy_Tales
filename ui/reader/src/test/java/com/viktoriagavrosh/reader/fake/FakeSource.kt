package com.viktoriagavrosh.reader.fake

import com.viktoriagavrosh.repositories.model.Folk
import com.viktoriagavrosh.repositories.model.Tale
import com.viktoriagavrosh.repositories.utils.ShelfGenre

internal object FakeSource {
    val fakeTales = List(3) {
        Tale(
            id = it,
            genre = ShelfGenre.Tales.entries[it],
            title = "Title $it",
            text = "Text $it",
            isFavorite = it % 2 == 0,
            isNight = it % 2 == 0,
        )
    }
    val fakeFolks = List(3) {
        Folk(
            id = it,
            genre = ShelfGenre.Folks.entries[it],
            title = "Title $it",
            text = "Text $it",
        )
    }

    val fakeTextSize = 24.0F
}
