package com.viktoriagavrosh.addtale.utils

import com.viktoriagavrosh.addtale.model.NewTale
import com.viktoriagavrosh.repositories.model.Tale

internal fun NewTale.toTale(): Tale {
    return Tale(
        id = id,
        genre = taleGenre.genre,
        title = title,
        text = text,
        isNight = isNight,
        isChangeable = isChangeable,
    )
}
