package com.viktoriagavrosh.repositories.utils

import com.viktoriagavrosh.database.model.FolkDb
import com.viktoriagavrosh.database.model.RiddleDb
import com.viktoriagavrosh.database.model.TaleDb
import com.viktoriagavrosh.datastore.model.SettingsDs
import com.viktoriagavrosh.repositories.model.Folk
import com.viktoriagavrosh.repositories.model.Riddle
import com.viktoriagavrosh.repositories.model.Settings
import com.viktoriagavrosh.repositories.model.Tale

internal fun TaleDb.toTale(): Tale {
    return Tale(
        id = id,
        genre = genre.toShelfGenre(),
        title = title,
        text = text,
        isFavorite = isFavorite,
        isNight = isNight,
        imageUrl = imageUrl,
        audioUrl = audioUrl,
        quizUrl = quizUrl,
        questionsUrl = questionsUrl,
        gameUrl = gameUrl,
    )
}

internal fun Tale.toTaleDb(): TaleDb {
    return TaleDb(
        id = id,
        genre = genre.genreName,
        title = title,
        text = text,
        isFavorite = isFavorite,
        isNight = isNight,
        imageUrl = imageUrl,
        audioUrl = audioUrl,
        quizUrl = quizUrl,
        questionsUrl = questionsUrl,
        gameUrl = gameUrl,
    )
}

internal fun FolkDb.toFolk(): Folk {
    return Folk(
        id = id,
        genre = genre.toShelfGenre(),
        title = title,
        text = text,
        imageUrl = imageUrl,
    )
}

internal fun RiddleDb.toRiddle(): Riddle {
    return Riddle(
        id = id,
        title = title,
        text = text,
        answer = answer,
        imageUrl = imageUrl,
    )
}

internal fun SettingsDs.toSettings(): Settings {
    return Settings(
        textSize = textSize,
        lastTaleId = lastTaleId,
    )
}

fun String.toShelfGenre(): ShelfGenre {
    return when(this) {
        "animal" -> ShelfGenre.Tales.Animal
        "fairy" -> ShelfGenre.Tales.Fairy
        "people" -> ShelfGenre.Tales.People
        "poem" -> ShelfGenre.Folks.Poem
        "counting" -> ShelfGenre.Folks.Counting
        "lullaby" -> ShelfGenre.Folks.Lullaby
        "night" -> ShelfGenre.Nights
        "favorite" -> ShelfGenre.Favorites
        "riddle" -> ShelfGenre.Riddles
        else -> ShelfGenre.Riddles             // TODO change it ???
    }
}
