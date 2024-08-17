package com.viktoriagavrosh.fairytales.ui.elements

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.viktoriagavrosh.fairytales.R

/**
 * Enum to describe destination of tab navigation
 */
enum class TaleType(
    @DrawableRes val iconId: Int,
    @StringRes val textId: Int,     // TODO consider changing to String ?
) {
    Story(
        iconId = R.drawable.ic_text,
        textId = R.string.title_fairy_tales,
    ),
    Puzzle(
        iconId = R.drawable.ic_puzzle,
        textId = R.string.title_puzzle,
    ),
    Poem(
        iconId = R.drawable.ic_poem,
        textId = R.string.title_poem,
    ),
    Game(
        iconId = R.drawable.ic_game,
        textId = R.string.title_game,
    ),
    Lullaby(
        iconId = R.drawable.ic_lullaby,
        textId = R.string.lullaby,
    ),
}
