package com.viktoriagavrosh.home.utils

import com.viktoriagavrosh.home.HomeScreenState
import com.viktoriagavrosh.home.model.TaleUiHome
import com.viktoriagavrosh.repositories.model.Tale
import com.viktoriagavrosh.repositories.tale.RequestResult

internal fun RequestResult<List<Tale>>.toHomeScreenState(): HomeScreenState {
    return when (this) {
        is RequestResult.Success -> HomeScreenState.Success(
            tales = data.map { it.toTaleUiHome() }
        )

        is RequestResult.Error -> HomeScreenState.Error()
    }
}

fun Tale.toTaleUiHome(): TaleUiHome {
    return TaleUiHome(
        id = id,
        genre = genre,
        title = title,
        text = text,
        answer = answer,
        imageUri = imageUri,
        audioUri = audioUri,
        isFavorite = isFavorite
    )
}

