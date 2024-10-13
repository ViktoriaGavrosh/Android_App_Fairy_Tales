package com.viktoriagavrosh.reader.utils

import com.viktoriagavrosh.reader.model.ReadBook
import com.viktoriagavrosh.repositories.model.Folk
import com.viktoriagavrosh.repositories.model.Retaining
import com.viktoriagavrosh.repositories.model.Tale

/**
 * convert model of repository to model for ReaderScreen
 *
 * @return [ReadBook]
 */
fun Retaining.toReadBook(): ReadBook {
    return when (this) {
        is Folk -> ReadBook(
            id = id,
            genre = genre,
            title = title,
            text = text,
            imageUrl = imageUrl,
        )

        is Tale -> ReadBook(
            id = id,
            genre = genre,
            title = title,
            text = text,
            imageUrl = imageUrl,
        )

        else -> ReadBook()
    }
}
