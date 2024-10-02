package com.viktoriagavrosh.home.fake

import com.viktoriagavrosh.repositories.utils.ShelfGenre
import com.viktoriagavrosh.shelf.model.Book

internal object FakeSource {

    private val genres: List<ShelfGenre> = listOf(
        ShelfGenre.Tales.Animal,
        ShelfGenre.Tales.Fairy,
        ShelfGenre.Tales.People,
        ShelfGenre.Folks.Poem,
        ShelfGenre.Folks.Counting,
        ShelfGenre.Folks.Lullaby,
        ShelfGenre.Riddles,
    )

    val books = List(7) {
        Book(
            id = it,
            genre = genres[it],
            title = "Title $it",
            isFavorite = it % 2 == 0,
        )
    }
}
