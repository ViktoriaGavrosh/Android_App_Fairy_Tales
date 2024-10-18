package com.viktoriagavrosh.repositories.fake

import com.viktoriagavrosh.database.model.FolkDb
import com.viktoriagavrosh.database.model.RiddleDb
import com.viktoriagavrosh.database.model.TaleDb
import com.viktoriagavrosh.datastore.model.SettingsDs

internal object FakeSource {

    private val listTaleGenre = listOf("animal", "fairy", "people", "animal")
    private val listFolkGenre = listOf("poem", "counting", "lullaby", "poem")

    val fakeListTaleDb = List(4) {
        TaleDb(
            id = it,
            genre = listTaleGenre[it],
            title = "Title $it",
            text = "Text $it",
            imageUrl = null,
            audioUrl = null,
            isFavorite = it % 2 == 0,
            isNight = it % 2 == 0,
            isChangeable = it % 2 == 0,
            quizUrl = null,
            questionsUrl = null,
            gameUrl = null,
        )
    }

    val fakeListFolkDb = List(4) {
        FolkDb(
            id = it,
            genre = listFolkGenre[it],
            title = "Title $it",
            text = "Text $it",
            imageUrl = null,
        )
    }

    val fakeListRiddleDb = List(4) {
        RiddleDb(
            id = it,
            title = "Title $it",
            text = "Text $it",
            answer = "Answer $it",
            imageUrl = null,
        )
    }

    val fakeSettingsDs = SettingsDs(
        textSize = 24.0F,
        lastTaleId = 0,
    )
}
