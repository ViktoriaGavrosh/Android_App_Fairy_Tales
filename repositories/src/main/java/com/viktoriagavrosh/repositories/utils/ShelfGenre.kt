package com.viktoriagavrosh.repositories.utils

sealed interface ShelfGenre {

    val genreName: String

    enum class Tales : ShelfGenre {
        Animal {
            override val genreName: String
                get() = "animal"
        },
        Fairy {
            override val genreName: String
                get() = "fairy"
        },
        People {
            override val genreName: String
                get() = "people"
        },
    }

    data object Riddles : ShelfGenre {
        override val genreName: String
            get() = "riddle"
    }

    data object Nights : ShelfGenre {
        override val genreName: String
            get() = "night"
    }

    data object Favorites : ShelfGenre {
        override val genreName: String
            get() = "favorite"
    }

    enum class Folks : ShelfGenre {
        Poem {
            override val genreName: String
                get() = "poem"
        },
        Counting {
            override val genreName: String
                get() = "counting"
        },
        Lullaby {
            override val genreName: String
                get() = "lullaby"
        }
    }
}
