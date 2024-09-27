package com.viktoriagavrosh.addtale.utils

import com.viktoriagavrosh.repositories.utils.ShelfGenre

internal enum class TaleGenre(
    val genre: ShelfGenre,
    val title: String,
) {
    Animal(
        genre = ShelfGenre.Tales.Animal,
        title = "Казка пра жывёл",
    ),
    Fairy(
        genre = ShelfGenre.Tales.Fairy,
        title = "Чаро\u045Eная казка",
    ),
    People(
        genre = ShelfGenre.Tales.People,
        title = "Бытавая казка",
    );
}
