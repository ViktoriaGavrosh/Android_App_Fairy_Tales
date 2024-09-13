package com.viktoriagavrosh.shelf.utils

import com.viktoriagavrosh.repositories.model.Folk
import com.viktoriagavrosh.repositories.model.Retaining
import com.viktoriagavrosh.repositories.model.Riddle
import com.viktoriagavrosh.shelf.model.Book
import com.viktoriagavrosh.repositories.model.Tale
import com.viktoriagavrosh.repositories.utils.RequestResult
import com.viktoriagavrosh.repositories.utils.ShelfGenre
import com.viktoriagavrosh.uikit.utils.ScreenState

internal fun <T: Any, S: Any> RequestResult<T>.toScreenState(convertData: (T) -> S): ScreenState<S> {
    return when (this) {
        is RequestResult.Success -> ScreenState.Success(
            data = convertData(data)
        )

        is RequestResult.Error -> ScreenState.Error()
    }
}

fun Retaining.toBook(): Book {
    return when(this) {
        is Folk -> Book(
            id = id,
            genre = genre,
            title = title,
            imageUrl = imageUrl,
        )
        is Riddle -> Book(
            id = id,
            genre = ShelfGenre.Riddles,
            title = title,
            imageUrl = imageUrl,
        )
        is Tale -> Book(
            id = id,
            genre = genre,
            title = title,
            imageUrl = imageUrl,
            isFavorite = isFavorite,
        )
        else -> Book()
    }
}

