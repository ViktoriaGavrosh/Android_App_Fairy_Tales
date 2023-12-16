package com.viktoriagavrosh.fairytales.data

import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.model.Composition.FairyTale
import com.viktoriagavrosh.fairytales.model.Composition.Game
import com.viktoriagavrosh.fairytales.model.Composition.Poem
import com.viktoriagavrosh.fairytales.model.Composition.Puzzle

object CatalogFairyTales {
    val fairyTales = listOf(
        FairyTale(
            R.drawable.bear,
            R.string.bear,
            R.string.bear_short_text
        ),
        FairyTale(
            R.drawable.bee,
            R.string.bee,
            R.string.bee_short_text
        ),
        FairyTale(
            R.drawable.chicken,
            R.string.chicken,
            R.string.chicken_short_text
        ),
        FairyTale(
            R.drawable.crane,
            R.string.crane,
            R.string.crane_short_text
        ),
        FairyTale(
            R.drawable.cuckoo,
            R.string.cuckoo,
            R.string.cuckoo_short_text
        ),
        FairyTale(
            R.drawable.fly,
            R.string.fly,
            R.string.fly_short_text
        ),
        FairyTale(
            R.drawable.fox,
            R.string.fox,
            R.string.fox_short_text
        ),
        FairyTale(
            R.drawable.kitten,
            R.string.kitten,
            R.string.kitten_short_text
        ),
        FairyTale(
            R.drawable.ram,
            R.string.ram,
            R.string.ram_short_text
        ),
        FairyTale(
            R.drawable.rooster,
            R.string.rooster,
            R.string.rooster_short_text
        )
    )

    val poems = listOf(
        Poem(
            R.drawable.kitten,
            R.string.kitten_poem
        ),
        Poem(
            R.drawable.chicken,
            R.string.chicken_poem
        ),
        Poem(
            R.drawable.crane,
            R.string.crane_poem
        ),
        Poem(
            R.drawable.cuckoo,
            R.string.cuckoo_poem
        ),
        Poem(
            R.drawable.ram,
            R.string.ram_poem
        )
    )

    val puzzles = listOf(
        Puzzle(
            R.drawable.kitten,
            R.string.kitten_puzzle,
            R.string.kitten_answer
        ),
        Puzzle(
            R.drawable.rooster,
            R.string.rooster_puzzle,
            R.string.rooster_answer
        ),
        Puzzle(
            R.drawable.crane,
            R.string.crane_puzzle,
            R.string.crane_answer
        ),
        Puzzle(
            R.drawable.bee,
            R.string.bee_puzzle,
            R.string.bee_answer
        )
    )

    val games = listOf(
        Game(
            R.drawable.rooster,
            R.string.rooster_game_title,
            R.string.rooster_game
        ),
        Game(
            R.drawable.cuckoo,
            R.string.cuckoo_game_title,
            R.string.cuckoo_game
        ),
        Game(
            R.drawable.chicken,
            R.string.chicken_game_title,
            R.string.chicken_game
        ),
        Game(
            R.drawable.bear,
            R.string.bear_game_title,
            R.string.bear_game
        ),
        Game(
            R.drawable.kitten,
            R.string.kitten_game_title,
            R.string.kitten_game
        )
    )
}
