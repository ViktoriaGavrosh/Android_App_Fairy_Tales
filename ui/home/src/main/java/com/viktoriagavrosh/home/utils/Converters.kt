package com.viktoriagavrosh.home.utils

import com.viktoriagavrosh.home.HomeScreenState
import com.viktoriagavrosh.home.elements.Genre
import com.viktoriagavrosh.home.model.TaleUiHome
import com.viktoriagavrosh.repositories.model.Tale
import com.viktoriagavrosh.repositories.tale.RequestResult
import java.util.Locale

internal fun RequestResult<List<Tale>>.toHomeScreenState(): HomeScreenState {
    return when (this) {
        is RequestResult.Success -> HomeScreenState.Success(
            tales = data.map { it.toTaleUiHome() }
        )

        is RequestResult.Error -> HomeScreenState.Error()
    }
}

fun Tale.toTaleUiHome(): TaleUiHome {
    val taleGenre = genre.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
    }
    return TaleUiHome(
        id = id,
        genre = Genre.valueOf(taleGenre),   // TODO IllegalArgumentException may be here
        title = title,
        text = text,
        answer = answer,
        imageUri = imageUri,
        audioUri = audioUri,
        isFavorite = isFavorite
    )
}

