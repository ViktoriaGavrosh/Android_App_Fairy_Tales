package com.viktoriagavrosh.riddle.utils

import com.viktoriagavrosh.repositories.model.Retaining
import com.viktoriagavrosh.repositories.model.Riddle
import com.viktoriagavrosh.riddle.model.ReadRiddle

/**
 * convert model of repository to model for RiddleScreen
 *
 * @return [ReadRiddle]
 */
fun Retaining.toReadRiddle(): ReadRiddle {
    return when (this) {
        is Riddle -> ReadRiddle(
            id = id,
            title = title,
            text = text,
            answer = answer,
            imageUrl = imageUrl,
        )

        else -> ReadRiddle()
    }
}
