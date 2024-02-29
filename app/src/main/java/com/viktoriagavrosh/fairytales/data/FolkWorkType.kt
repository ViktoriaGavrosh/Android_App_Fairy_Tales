package com.viktoriagavrosh.fairytales.data

import androidx.annotation.DrawableRes
import com.viktoriagavrosh.fairytales.R

enum class FolkWorkType(
    @DrawableRes val iconId: Int,
    val textId: Int
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
