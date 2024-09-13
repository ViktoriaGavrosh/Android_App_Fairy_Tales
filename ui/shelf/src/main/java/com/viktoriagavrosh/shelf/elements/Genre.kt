package com.viktoriagavrosh.shelf.elements

import androidx.annotation.DrawableRes
import com.viktoriagavrosh.home.R

/**
 * Enum class to describe the destination of tab navigation
 */
enum class Genre(
    val genreName: String,
    @DrawableRes val iconId: Int,
    val textId: Int   // TODO consider changing to String
) {
    Story(
        genreName = "story",
        iconId = R.drawable.ic_text,
        textId = R.string.title_fairy_tales
    ),
    Puzzle(
        genreName = "puzzle",
        iconId = R.drawable.ic_puzzle,
        textId = R.string.title_puzzle
    ),
    Poem(
        genreName = "poem",
        iconId = R.drawable.ic_poem,
        textId = R.string.title_poem
    ),
    Game(
        genreName = "game",
        iconId = R.drawable.ic_game,
        textId = R.string.title_game
    ),
    Lullaby(
        genreName = "lullaby",
        iconId = R.drawable.ic_lullaby,
        textId = R.string.lullaby
    );
}



