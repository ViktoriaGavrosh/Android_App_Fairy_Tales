package com.viktoriagavrosh.navigation

import com.viktoriagavrosh.repositories.utils.ShelfGenre
import kotlinx.serialization.Serializable

@Serializable
internal object StartMenu

@Serializable
internal object LibraryMenu

@Serializable
internal data class Shelf(
    val navGenre: NavGenre = NavGenre.Night,
)

@Serializable
internal data class BookInfo(
    val bookId: Int = 0,
    val navGenre: NavGenre = NavGenre.Night,
)

@Serializable
internal data class Reader(
    val bookId: Int = 0,
    val navGenre: NavGenre = NavGenre.Night,
)

@Serializable
internal data class Riddle(
    val riddleId: Int = 0,
)

@Serializable
internal object Settings

@Serializable
internal object AddTale

internal enum class NavGenre(val genre: ShelfGenre) {
    Animal(ShelfGenre.Tales.Animal),
    Fairy(ShelfGenre.Tales.Fairy),
    People(ShelfGenre.Tales.People),
    Poem(ShelfGenre.Folks.Poem),
    Counting(ShelfGenre.Folks.Counting),
    Lullaby(ShelfGenre.Folks.Lullaby),
    Night(ShelfGenre.Nights),
    Favorite(ShelfGenre.Favorites),
    Riddle(ShelfGenre.Riddles),
}
