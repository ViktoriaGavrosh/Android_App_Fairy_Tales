package com.viktoriagavrosh.fairytales.data

import androidx.annotation.StringRes
import com.viktoriagavrosh.fairytales.R

enum class CompositionType(@StringRes val typeTitle: Int) {
    FairyTales(R.string.title_fairy_tales),
    Puzzles(R.string.title_puzzle),
    Poems(R.string.title_poem),
    Games(R.string.title_game)
}
