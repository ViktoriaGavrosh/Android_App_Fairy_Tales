package com.viktoriagavrosh.fairytales

import android.content.Context
import com.viktoriagavrosh.database.AppDatabase
import com.viktoriagavrosh.database.getDatabase
import com.viktoriagavrosh.repositories.DatastoreSettingsRepository
import com.viktoriagavrosh.repositories.OfflineTaleRepository
import com.viktoriagavrosh.repositories.SettingsRepository
import com.viktoriagavrosh.repositories.TaleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Object fo dependency injection (dagger hilt)
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext
        context: Context
    ): AppDatabase {
        return getDatabase(
            context = context
        )
    }

    @Provides
    @Singleton
    fun provideTaleRepository(
        appDatabase: AppDatabase
    ): TaleRepository {
        return OfflineTaleRepository(appDatabase)
    }

    @Provides
    @Singleton
    fun provideSettingsRepository(
    ): SettingsRepository {
        return DatastoreSettingsRepository()
    }
}
