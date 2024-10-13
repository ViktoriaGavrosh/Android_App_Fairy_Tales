package com.viktoriagavrosh.navigation

import com.viktoriagavrosh.repositories.utils.ShelfGenre
import com.viktoriagavrosh.repositories.utils.ShelfGenre.Favorites
import com.viktoriagavrosh.repositories.utils.ShelfGenre.Folks
import com.viktoriagavrosh.repositories.utils.ShelfGenre.Nights
import com.viktoriagavrosh.repositories.utils.ShelfGenre.Riddles
import com.viktoriagavrosh.repositories.utils.ShelfGenre.Tales
import kotlinx.serialization.Serializable

/**
 * Describes navigation destination of StartScreen
 */
@Serializable
internal object StartMenu

/**
 * Describes navigation destination of LibraryScreen
 */
@Serializable
internal object LibraryMenu

/**
 * Describes navigation destination of ShelfScreen
 *
 * @param navGenre navigation genre of book
 */
@Serializable
internal data class Shelf(
    val navGenre: NavGenre = NavGenre.Night,
)

/**
 * Describes navigation destination of InfoScreen
 *
 * @param bookId id of selected book
 * @param navGenre navigation genre of book
 */
@Serializable
internal data class BookInfo(
    val bookId: Int = 0,
    val navGenre: NavGenre = NavGenre.Night,
)

/**
 * Describes navigation destination of ReaderScreen
 *
 * @param bookId id of selected book
 * @param navGenre navigation genre of book
 */
@Serializable
internal data class Reader(
    val bookId: Int = 0,
    val navGenre: NavGenre = NavGenre.Night,
)

/**
 * Describes navigation destination of RiddleScreen
 *
 * @param riddleId id of selected riddle
 */
@Serializable
internal data class Riddle(
    val riddleId: Int = 0,
)

/**
 * Describes navigation destination of SettingsScreen
 */
@Serializable
internal object Settings

/**
 * Describes navigation destination of AddTaleScreen
 */
@Serializable
internal object AddTale

/**
 *  Describes types of genre only for navigation
 *
 *  @param genre genre of all app
 *  @see Animal
 *  @see Fairy
 *  @see People
 *  @see Poem
 *  @see Counting
 *  @see Lullaby
 *  @see Night
 *  @see Favorite
 *  @see Riddle
 */
internal enum class NavGenre(val genre: ShelfGenre) {

    /**
     * Genre only for navigation
     *
     * @property genre genre of all app [ShelfGenre]
     * @see NavGenre
     */
    Animal(Tales.Animal),

    /**
     * Genre only for navigation
     *
     * @property genre genre of all app [ShelfGenre]
     * @see NavGenre
     */
    Fairy(Tales.Fairy),

    /**
     * Genre only for navigation
     *
     * @property genre genre of all app [ShelfGenre]
     * @see NavGenre
     */
    People(Tales.People),

    /**
     * Genre only for navigation
     *
     * @property genre genre of all app [ShelfGenre]
     * @see NavGenre
     */
    Poem(Folks.Poem),

    /**
     * Genre only for navigation
     *
     * @property genre genre of all app [ShelfGenre]
     * @see NavGenre
     */
    Counting(Folks.Counting),

    /**
     * Genre only for navigation
     *
     * @property genre genre of all app [ShelfGenre]
     * @see NavGenre
     */
    Lullaby(Folks.Lullaby),

    /**
     * Genre only for navigation
     *
     * @property genre genre of all app [ShelfGenre]
     * @see NavGenre
     */
    Night(Nights),

    /**
     * Genre only for navigation
     *
     * @property genre genre of all app [ShelfGenre]
     * @see NavGenre
     */
    Favorite(Favorites),

    /**
     * Genre only for navigation
     *
     * @property genre genre of all app [ShelfGenre]
     * @see NavGenre
     */
    Riddle(Riddles),
}
