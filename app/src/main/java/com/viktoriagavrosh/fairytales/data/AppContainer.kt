package com.viktoriagavrosh.fairytales.data

import android.content.Context

/**
 * App container for Dependency injection
 */
interface AppContainer {
    val folkWorkRepository: FolkWorkRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineForkWorkRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {

    /**
     * Implementation for [FolkWorkRepository]
     */
    override val folkWorkRepository: FolkWorkRepository by lazy {
        OfflineForkWorkRepository(AppDatabase.getDatabase(context).folkWorkDao())
    }
}