package com.viktoriagavrosh.fairytales.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.res.stringResource
import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.model.Composition

enum class CompositionType(
    @DrawableRes val iconId: Int,
    val textId: Int,
    val listItems: List<Composition>
) {
    FairyTales(
        iconId = R.drawable.ic_text,
        textId = R.string.title_fairy_tales,
        listItems = CatalogFairyTales.fairyTales
    ),
    Puzzles(
        iconId = R.drawable.ic_puzzle,
        textId = R.string.title_puzzle,
        listItems = CatalogFairyTales.puzzles
    ),
    Poems(
        iconId = R.drawable.ic_poem,
        textId = R.string.title_poem,
        listItems = CatalogFairyTales.poems
    ),
    Games(
        iconId = R.drawable.ic_game,
        textId = R.string.title_game,
        listItems = CatalogFairyTales.games
    );

}
