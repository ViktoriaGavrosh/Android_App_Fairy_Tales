package com.viktoriagavrosh.fairytales

import android.app.Application
import com.viktoriagavrosh.fairytales.data.AppContainer
import com.viktoriagavrosh.fairytales.data.AppDataContainer

class FairyTalesApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}