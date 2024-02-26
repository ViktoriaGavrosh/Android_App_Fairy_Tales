package com.viktoriagavrosh.fairytales.data

import androidx.annotation.DrawableRes
import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.model.Composition

enum class FolkWorkType(
    @DrawableRes val iconId: Int,
    val textId: Int,
    //val listItems: List<Composition>
) {
    Story(
        iconId = R.drawable.ic_text,
        textId = R.string.title_fairy_tales,
        //listItems = CatalogFairyTales.fairyTales
    ),
    Puzzle(
        iconId = R.drawable.ic_puzzle,
        textId = R.string.title_puzzle,
        //listItems = CatalogFairyTales.puzzles
    ),
    Poem(
        iconId = R.drawable.ic_poem,
        textId = R.string.title_poem,
        //listItems = CatalogFairyTales.poems
    ),
    Game(
        iconId = R.drawable.ic_game,
        textId = R.string.title_game,
        //listItems = CatalogFairyTales.games
    ),
    Lullaby(
        iconId = R.drawable.ic_lullaby,
        textId = R.string.lullaby
    );
}
