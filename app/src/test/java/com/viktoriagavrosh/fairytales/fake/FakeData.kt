package com.viktoriagavrosh.fairytales.fake

import com.viktoriagavrosh.fairytales.model.Tale
import com.viktoriagavrosh.fairytales.ui.settingsscreen.SettingsState

object FakeData {
    val fakeListTales: List<Tale> = List(4) {
        Tale(
            id = it,
            genre = if (it % 2 == 0) "story" else "puzzle",
            title = "Title$it",
            text = "Text",     // TODO maybe "Text$it"  ?
            answer = null,
            imageUrl = null,
            audioUrl = null,
            isFavorite = it % 2 == 0,
        )
    }

    val fakeSettingsState = SettingsState(
        textSize = 25.0F
    )
}
