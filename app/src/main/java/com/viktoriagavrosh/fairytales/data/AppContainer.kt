package com.viktoriagavrosh.fairytales.data

import android.content.Context

interface AppContainer {
    val folkWorkRepository: FolkWorkRepository
}

class AppDataContainer(private val context: Context) : AppContainer {

    override val folkWorkRepository: FolkWorkRepository by lazy {
        OfflineForkWorkRepository(AppDatabase.getDatabase(context).folkWorkDao())
    }
}