package com.viktoriagavrosh.details.utils

import com.viktoriagavrosh.details.DetailScreenState
import com.viktoriagavrosh.details.model.TaleUiDetail
import com.viktoriagavrosh.repositories.model.Tale
import com.viktoriagavrosh.repositories.tale.RequestResult

internal fun RequestResult<Tale>.toDetailScreenState(): DetailScreenState {
    return when (this) {
        is RequestResult.Success -> DetailScreenState.Success(
            tale = data.toTaleUiDetail()
        )

        is RequestResult.Error -> DetailScreenState.Error()
    }
}

fun Tale.toTaleUiDetail(): TaleUiDetail {
    return TaleUiDetail(
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
