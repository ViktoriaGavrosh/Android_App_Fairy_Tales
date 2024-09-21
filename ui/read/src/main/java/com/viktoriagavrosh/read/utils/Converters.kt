package com.viktoriagavrosh.read.utils

import com.viktoriagavrosh.read.model.ReadBook
import com.viktoriagavrosh.repositories.model.Folk
import com.viktoriagavrosh.repositories.model.Retaining
import com.viktoriagavrosh.repositories.model.Tale

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
