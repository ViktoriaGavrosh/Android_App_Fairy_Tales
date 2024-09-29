package com.viktoriagavrosh.shelf.utils

import com.viktoriagavrosh.repositories.utils.ShelfGenre
import com.viktoriagavrosh.uikit.R

/**
 * Sealed class to describe the destination of tab navigation
 */

internal sealed interface Tabs {

    val genre: ShelfGenre
    val iconId: Int?
    val textId: Int

    enum class TaleTab : Tabs {
        Animal {
            override val genre: ShelfGenre = ShelfGenre.Tales.Animal
            override val iconId: Int = R.drawable.ic_animal
            override val textId: Int = R.string.title_animal_tales
        },
        Fairy {
            override val genre: ShelfGenre = ShelfGenre.Tales.Fairy
            override val iconId: Int = R.drawable.ic_fairy
            override val textId: Int = R.string.title_fairy_tales
        },
        People {
            override val genre: ShelfGenre = ShelfGenre.Tales.People
            override val iconId: Int = R.drawable.ic_people
            override val textId: Int = R.string.title_people_tales
        }
    }

    enum class FolkTab : Tabs {
        Poem {
            override val genre: ShelfGenre = ShelfGenre.Folks.Poem
            override val iconId: Int = R.drawable.ic_poem
            override val textId: Int = R.string.title_poem_folk
        },
        Counting {
            override val genre: ShelfGenre = ShelfGenre.Folks.Counting
            override val iconId: Int = R.drawable.ic_counting
            override val textId: Int = R.string.title_counting_folk
        },
        Lullaby {
            override val genre: ShelfGenre = ShelfGenre.Folks.Lullaby
            override val iconId: Int = R.drawable.ic_lullaby
            override val textId: Int = R.string.title_lullaby_folk
        }
    }

    data object Riddle : Tabs {
        override val genre: ShelfGenre = ShelfGenre.Riddles
        override val iconId: Int? = null
        override val textId: Int = R.string.title_riddles
    }

    data object Favorite : Tabs {
        override val genre: ShelfGenre = ShelfGenre.Favorites
        override val iconId: Int? = null
        override val textId: Int = R.string.title_favorite
    }

    data object Night : Tabs {
        override val genre: ShelfGenre = ShelfGenre.Nights
        override val iconId: Int? = null
        override val textId: Int = R.string.title_night
    }
}


