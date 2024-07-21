package com.viktoriagavrosh.home.elements

import androidx.annotation.DrawableRes
import com.viktoriagavrosh.home.R

/**
 * Enum class to describe the destination of tab navigation
 */
enum class TaleType(
    @DrawableRes val iconId: Int,
    val textId: Int   // TODO рассмтреть возможность заменить на String
) {
    Story(
        iconId = R.drawable.ic_text,
        textId = R.string.title_fairy_tales
    ),
    Puzzle(
        iconId = R.drawable.ic_puzzle,
        textId = R.string.title_puzzle
    ),
    Poem(
        iconId = R.drawable.ic_poem,
        textId = R.string.title_poem
    ),
    Game(
        iconId = R.drawable.ic_game,
        textId = R.string.title_game
    ),
    Lullaby(
        iconId = R.drawable.ic_lullaby,
        textId = R.string.lullaby
    );
}



