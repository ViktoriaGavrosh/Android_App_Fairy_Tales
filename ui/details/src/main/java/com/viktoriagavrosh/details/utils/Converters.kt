package com.viktoriagavrosh.details.utils

import com.viktoriagavrosh.details.DetailScreenState
import com.viktoriagavrosh.details.model.TaleUiDetail
import com.viktoriagavrosh.repositories.model.Tale
import com.viktoriagavrosh.repositories.utils.RequestResult

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
        genre = genre.genreName,    //genre,   TODO 111
        title = title,
        text = text,
        answer = title,   //  answer,   TODO 111
        imageUri = imageUrl,
        audioUri = audioUrl,
        isFavorite = isFavorite
    )
}
