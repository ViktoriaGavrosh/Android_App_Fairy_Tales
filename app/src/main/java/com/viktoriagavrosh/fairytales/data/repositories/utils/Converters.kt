package com.viktoriagavrosh.fairytales.data.repositories.utils

import com.viktoriagavrosh.fairytales.model.Tale
import com.viktoriagavrosh.fairytales.model.TaleDb
import com.viktoriagavrosh.fairytales.model.TaleUi
import com.viktoriagavrosh.fairytales.ui.detailscreen.DetailScreenState
import com.viktoriagavrosh.fairytales.ui.elements.Genre
import com.viktoriagavrosh.fairytales.ui.homescreen.HomeScreenState
import java.util.Locale

fun TaleDb.toTale(): Tale {
    val taleGenre = genre.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
    }
    return Tale(
        id = id,
        genre = Genre.valueOf(taleGenre),    // TODO IllegalArgumentException may be here
        title = title,
        text = text,
        answer = answer,
        imageUrl = imageUrl,
        audioUrl = audioUrl,
        isFavorite = isFavorite,
    )
}

fun Tale.toTaleUi(): TaleUi {
    return TaleUi(
        id = id,
        genre = genre,
        title = title,
        text = text,
        answer = answer,
        imageUrl = imageUrl,
        audioUrl = audioUrl,
        isFavorite = isFavorite,
    )
}

// TODO two StateScreen need?
fun RequestResult<Tale>.toDetailScreenState(): DetailScreenState {
    return when (this) {
        is RequestResult.Success -> DetailScreenState.Success(tale = data.toTaleUi())
        is RequestResult.Error -> DetailScreenState.Error()
    }
}

fun RequestResult<List<Tale>>.toHomeScreenState(): HomeScreenState {
    return when (this) {
        is RequestResult.Success -> HomeScreenState.Success(
            tales = data.map { it.toTaleUi() }
        )

        is RequestResult.Error -> HomeScreenState.Error()
    }
}
