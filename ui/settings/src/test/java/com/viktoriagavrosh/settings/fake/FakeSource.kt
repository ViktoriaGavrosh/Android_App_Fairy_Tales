package com.viktoriagavrosh.settings.fake

import com.viktoriagavrosh.repositories.model.Settings

internal object FakeSource {

    val fakeSettings = Settings(
        textSize = 24.0F,
        lastTaleId = 0
    )
}
