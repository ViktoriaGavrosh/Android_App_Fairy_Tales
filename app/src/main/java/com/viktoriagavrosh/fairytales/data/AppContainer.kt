package com.viktoriagavrosh.fairytales.data

import android.content.Context

/**
 * App container for Dependency injection
 */
interface AppContainer {
    val taleRepository: TaleRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineTaleRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {

    /**
     * Implementation for [TaleRepository]
     */
    override val taleRepository: TaleRepository by lazy {
        OfflineTaleRepository(AppDatabase.getDatabase(context).taleDao())
    }
}
