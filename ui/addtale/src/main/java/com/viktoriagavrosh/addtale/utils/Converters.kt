package com.viktoriagavrosh.addtale.utils

import com.viktoriagavrosh.addtale.model.NewTale
import com.viktoriagavrosh.repositories.model.Tale

/**
 * convert model for AddTaleScreen to model of repository
 *
 * @return [Tale]
 */
internal fun NewTale.toTale(): Tale {
    return Tale(
        id = id,
        genre = taleGenre.genre,
        title = title,
        text = text,
        isNight = isNight,
        isChangeable = isChangeable,
        imageUrl = "",               // needs to show image on the card in ShelfScreen
    )
}
