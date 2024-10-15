package com.viktoriagavrosh.shelf.utils

import com.viktoriagavrosh.repositories.utils.ShelfGenre
import com.viktoriagavrosh.shelf.utils.Tabs.Favorite
import com.viktoriagavrosh.shelf.utils.Tabs.Favorite.genre
import com.viktoriagavrosh.shelf.utils.Tabs.Favorite.iconId
import com.viktoriagavrosh.shelf.utils.Tabs.Favorite.textId
import com.viktoriagavrosh.shelf.utils.Tabs.FolkTab
import com.viktoriagavrosh.shelf.utils.Tabs.FolkTab.Counting
import com.viktoriagavrosh.shelf.utils.Tabs.FolkTab.Lullaby
import com.viktoriagavrosh.shelf.utils.Tabs.FolkTab.Poem
import com.viktoriagavrosh.shelf.utils.Tabs.Night
import com.viktoriagavrosh.shelf.utils.Tabs.Night.genre
import com.viktoriagavrosh.shelf.utils.Tabs.Night.iconId
import com.viktoriagavrosh.shelf.utils.Tabs.Night.textId
import com.viktoriagavrosh.shelf.utils.Tabs.Riddle
import com.viktoriagavrosh.shelf.utils.Tabs.Riddle.genre
import com.viktoriagavrosh.shelf.utils.Tabs.Riddle.iconId
import com.viktoriagavrosh.shelf.utils.Tabs.Riddle.textId
import com.viktoriagavrosh.shelf.utils.Tabs.TaleTab
import com.viktoriagavrosh.shelf.utils.Tabs.TaleTab.Animal
import com.viktoriagavrosh.shelf.utils.Tabs.TaleTab.Fairy
import com.viktoriagavrosh.shelf.utils.Tabs.TaleTab.People
import com.viktoriagavrosh.uikit.R

/**
 * Sealed class to describe destinations of tab navigation
 *
 * @property genre genre of book
 * @property iconId id of icon for tab (drawable res)
 * @property textId id of title for tab (string res)
 * @see TaleTab
 * @see FolkTab
 * @see Riddle
 * @see Favorite
 * @see Night
 */
internal sealed interface Tabs {

    val genre: ShelfGenre
    val iconId: Int?
    val textId: Int

    /**
     * describes destination of tab navigation (tale)
     *
     * @see Animal
     * @see Fairy
     * @see People
     */
    enum class TaleTab : Tabs {

        /**
         * describes destination of tab navigation (tale)
         *
         * @property genre genre of tale
         * @property iconId id of icon for tab (drawable res)
         * @property textId id of title for tab (string res)
         */
        Animal {
            override val genre: ShelfGenre = ShelfGenre.Tales.Animal
            override val iconId: Int = R.drawable.ic_animal
            override val textId: Int = R.string.title_animal_tales
        },

        /**
         * describes destination of tab navigation (tale)
         *
         * @property genre genre of tale
         * @property iconId id of icon for tab (drawable res)
         * @property textId id of title for tab (string res)
         */
        Fairy {
            override val genre: ShelfGenre = ShelfGenre.Tales.Fairy
            override val iconId: Int = R.drawable.ic_fairy
            override val textId: Int = R.string.title_fairy_tales
        },

        /**
         * describes destination of tab navigation (tale)
         *
         * @property genre genre of tale
         * @property iconId id of icon for tab (drawable res)
         * @property textId id of title for tab (string res)
         */
        People {
            override val genre: ShelfGenre = ShelfGenre.Tales.People
            override val iconId: Int = R.drawable.ic_people
            override val textId: Int = R.string.title_people_tales
        }
    }

    /**
     * describes destination of tab navigation (folk)
     *
     * @see Poem
     * @see Counting
     * @see Lullaby
     */
    enum class FolkTab : Tabs {

        /**
         * describes destination of tab navigation (folk)
         *
         * @property genre genre of folk
         * @property iconId id of icon for tab (drawable res)
         * @property textId id of title for tab (string res)
         */
        Poem {
            override val genre: ShelfGenre = ShelfGenre.Folks.Poem
            override val iconId: Int = R.drawable.ic_poem
            override val textId: Int = R.string.title_poem_folk
        },

        /**
         * describes destination of tab navigation (folk)
         *
         * @property genre genre of folk
         * @property iconId id of icon for tab (drawable res)
         * @property textId id of title for tab (string res)
         */
        Counting {
            override val genre: ShelfGenre = ShelfGenre.Folks.Counting
            override val iconId: Int = R.drawable.ic_counting
            override val textId: Int = R.string.title_counting_folk
        },

        /**
         * describes destination of tab navigation (folk)
         *
         * @property genre genre of folk
         * @property iconId id of icon for tab (drawable res)
         * @property textId id of title for tab (string res)
         */
        Lullaby {
            override val genre: ShelfGenre = ShelfGenre.Folks.Lullaby
            override val iconId: Int = R.drawable.ic_lullaby
            override val textId: Int = R.string.title_lullaby_folk
        }
    }

    /**
     * describes destination of tab navigation (riddle)
     *
     * @property genre riddle genre
     * @property iconId id of icon for tab (drawable res)
     * @property textId id of title for tab (string res)
     */
    data object Riddle : Tabs {
        override val genre: ShelfGenre = ShelfGenre.Riddles
        override val iconId: Int? = null
        override val textId: Int = R.string.title_riddles
    }

    /**
     * describes destination of tab navigation (favorite tale)
     *
     * @property genre favorite tale genre
     * @property iconId id of icon for tab (drawable res)
     * @property textId id of title for tab (string res)
     */
    data object Favorite : Tabs {
        override val genre: ShelfGenre = ShelfGenre.Favorites
        override val iconId: Int? = null
        override val textId: Int = R.string.title_favorite
    }

    /**
     * describes destination of tab navigation (night tale)
     *
     * @property genre night tale genre
     * @property iconId id of icon for tab (drawable res)
     * @property textId id of title for tab (string res)
     */
    data object Night : Tabs {
        override val genre: ShelfGenre = ShelfGenre.Nights
        override val iconId: Int? = null
        override val textId: Int = R.string.title_night
    }
}


