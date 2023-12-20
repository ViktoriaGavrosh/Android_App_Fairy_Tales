package com.viktoriagavrosh.fairytales.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.viktoriagavrosh.fairytales.R

sealed class Composition(
    @DrawableRes open val imageId: Int,
    @StringRes open val titleId: Int,
    @StringRes open val textId: Int,
    @StringRes val shortTitleId: Int
) {
    class FairyTale(
        @DrawableRes override val imageId: Int,
        @StringRes override val titleId: Int,
        @StringRes override val textId: Int
    ) : Composition(
        imageId = imageId,
        titleId = titleId,
        textId = textId,
        shortTitleId = titleId
    )

    class Poem(
        @DrawableRes override val imageId: Int,
        @StringRes override val textId: Int
    ) : Composition(
        imageId = imageId,
        titleId = textId,
        textId = textId,
        shortTitleId = R.string.title_one_poem
    )

    class Puzzle(
        @DrawableRes override val imageId: Int,
        @StringRes override val textId: Int,
        @StringRes val answerId: Int
    ) : Composition(
        imageId = imageId,
        titleId = textId,
        textId = textId,
        shortTitleId = R.string.title_one_puzzle
    )

    class Game(
        @DrawableRes override val imageId: Int,
        @StringRes override val titleId: Int,
        @StringRes override val textId: Int
    ) : Composition(
        imageId = imageId,
        titleId = titleId,
        textId = textId,
        shortTitleId = R.string.title_one_game
    )
}