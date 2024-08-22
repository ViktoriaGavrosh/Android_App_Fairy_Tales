package com.viktoriagavrosh.fairytales.data.repositories.utils

import com.viktoriagavrosh.fairytales.model.Tale
import com.viktoriagavrosh.fairytales.model.TaleDb
import com.viktoriagavrosh.fairytales.model.TaleUi
import com.viktoriagavrosh.fairytales.ui.ScreenState
import com.viktoriagavrosh.fairytales.ui.elements.Genre
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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

fun <T : Any> RequestResult<T>.toScreenState(): ScreenState<T> {
    return when (this) {
        is RequestResult.Success -> ScreenState.Success(data = data)
        is RequestResult.Error -> ScreenState.Error()
    }
}

fun Flow<RequestResult<List<Tale>>>.toFlowRequestResultTalesUi(): Flow<RequestResult<List<TaleUi>>> {
    return this.map { requestResult ->
        requestResult.map { listTales ->
            listTales.map { it.toTaleUi() }
        }
    }
}
