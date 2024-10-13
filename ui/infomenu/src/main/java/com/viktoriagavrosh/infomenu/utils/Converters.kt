package com.viktoriagavrosh.infomenu.utils

import com.viktoriagavrosh.infomenu.model.TaleInfo
import com.viktoriagavrosh.repositories.model.Retaining
import com.viktoriagavrosh.repositories.model.Tale

/**
 * convert model of repository to model for InfoScreen
 *
 * @return [TaleInfo]
 */
fun Retaining.toTaleInfo(): TaleInfo {
    return when (this) {
        is Tale -> TaleInfo(
            id = id,
            title = title,
            imageUrl = imageUrl ?: "",
            isFavorite = isFavorite,
        )

        else -> TaleInfo()
    }
}
