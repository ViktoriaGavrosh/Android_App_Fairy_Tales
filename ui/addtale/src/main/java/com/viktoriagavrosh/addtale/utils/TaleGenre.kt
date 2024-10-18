package com.viktoriagavrosh.addtale.utils

import com.viktoriagavrosh.addtale.utils.TaleGenre.Animal
import com.viktoriagavrosh.addtale.utils.TaleGenre.Fairy
import com.viktoriagavrosh.addtale.utils.TaleGenre.People
import com.viktoriagavrosh.repositories.utils.ShelfGenre

/**
 *  Describes types of tale genre only for AddTaleScreen
 *
 *  @property genre genre of all app
 *  @property title String - title to display on ui
 *  @see Animal
 *  @see Fairy
 *  @see People
 */
internal enum class TaleGenre(
    val genre: ShelfGenre,
    val title: String,
) {
    /**
     * Genre of tales only for AddTaleScreen
     *
     * @property genre genre of all app
     * @property title String - title to display on ui
     * @see TaleGenre
     */
    Animal(
        genre = ShelfGenre.Tales.Animal,
        title = "Казка пра жывёл",
    ),

    /**
     * Genre of tales only for AddTaleScreen
     *
     * @property genre genre of all app
     * @property title String - title to display on ui
     * @see TaleGenre
     */
    Fairy(
        genre = ShelfGenre.Tales.Fairy,
        title = "Чаро\u045Eная казка",
    ),

    /**
     * Genre of tales only for AddTaleScreen
     *
     * @property genre genre of all app
     * @property title String - title to display on ui
     * @see TaleGenre
     */
    People(
        genre = ShelfGenre.Tales.People,
        title = "Бытавая казка",
    );
}
