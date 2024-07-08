package com.viktoriagavrosh.fairytales.data

import android.content.Context
import com.viktoriagavrosh.database.getDatabase
import com.viktoriagavrosh.repositories.FolkWorkRepository
import com.viktoriagavrosh.repositories.OfflineForkWorkRepository

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
        OfflineForkWorkRepository(getDatabase(context).folkWorkDao)
    }
}
