package com.viktoriagavrosh.riddle.fake

import com.viktoriagavrosh.repositories.model.Riddle

internal object FakeSource {
    val fakeRiddles = List(3) {
        Riddle(
            id = it,
            title = "Title $it",
            text = "Text $it",
            answer = "Answer $it"
        )
    }

    val fakeTextSize = 24.0F
}
