package com.viktoriagavrosh.repositories.utils

import com.viktoriagavrosh.repositories.utils.ShelfGenre.Favorites
import com.viktoriagavrosh.repositories.utils.ShelfGenre.Favorites.genreName
import com.viktoriagavrosh.repositories.utils.ShelfGenre.Folks
import com.viktoriagavrosh.repositories.utils.ShelfGenre.Folks.Counting
import com.viktoriagavrosh.repositories.utils.ShelfGenre.Folks.Lullaby
import com.viktoriagavrosh.repositories.utils.ShelfGenre.Folks.Poem
import com.viktoriagavrosh.repositories.utils.ShelfGenre.Nights
import com.viktoriagavrosh.repositories.utils.ShelfGenre.Nights.genreName
import com.viktoriagavrosh.repositories.utils.ShelfGenre.Riddles
import com.viktoriagavrosh.repositories.utils.ShelfGenre.Riddles.genreName
import com.viktoriagavrosh.repositories.utils.ShelfGenre.Tales
import com.viktoriagavrosh.repositories.utils.ShelfGenre.Tales.Animal
import com.viktoriagavrosh.repositories.utils.ShelfGenre.Tales.Fairy
import com.viktoriagavrosh.repositories.utils.ShelfGenre.Tales.People

/**
 *  Describes types of genre
 *
 *  @property genreName Name of genre (String). Use in Database.
 *  @see Tales
 *  @see Riddles
 *  @see Nights
 *  @see Favorites
 *  @see Folks
 */
sealed interface ShelfGenre {

    val genreName: String

    /**
     * Genres of tales
     *
     * @see Animal
     * @see Fairy
     * @see People
     */
    enum class Tales : ShelfGenre {

        /**
         * Genre of tales
         *
         * @property genreName Name of genre (String). Use in Database.
         * @see ShelfGenre
         */
        Animal {
            override val genreName: String
                get() = "animal"
        },

        /**
         * Genre of tales
         *
         * @property genreName Name of genre (String). Use in Database.
         * @see ShelfGenre
         */
        Fairy {
            override val genreName: String
                get() = "fairy"
        },

        /**
         * Genre of tales
         *
         * @property genreName Name of genre (String). Use in Database.
         * @see ShelfGenre
         */
        People {
            override val genreName: String
                get() = "people"
        },
    }

    /**
     * Genre for riddle
     *
     * @property genreName Name of genre (String). Use in Database.
     * @see ShelfGenre
     */
    data object Riddles : ShelfGenre {
        override val genreName: String
            get() = "riddle"
    }

    /**
     * Genre for night tale
     *
     * @property genreName Name of genre (String). Use in Database.
     * @see ShelfGenre
     */
    data object Nights : ShelfGenre {
        override val genreName: String
            get() = "night"
    }

    /**
     * Genre for night tale
     *
     * @property genreName Name of genre (String). Use in Database.
     * @see ShelfGenre
     */
    data object Favorites : ShelfGenre {
        override val genreName: String
            get() = "favorite"
    }

    /**
     * Genres of folks
     *
     * @see Poem
     * @see Counting
     * @see Lullaby
     */
    enum class Folks : ShelfGenre {

        /**
         * Genre of folk
         *
         * @property genreName Name of genre (String). Use in Database.
         * @see ShelfGenre
         */
        Poem {
            override val genreName: String
                get() = "poem"
        },

        /**
         * Genre of folk
         *
         * @property genreName Name of genre (String). Use in Database.
         * @see ShelfGenre
         */
        Counting {
            override val genreName: String
                get() = "counting"
        },

        /**
         * Genre of folk
         *
         * @property genreName Name of genre (String). Use in Database.
         * @see ShelfGenre
         */
        Lullaby {
            override val genreName: String
                get() = "lullaby"
        }
    }
}
