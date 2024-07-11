package com.viktoriagavrosh.fairytales

import android.content.Context
import com.viktoriagavrosh.database.AppDatabase
import com.viktoriagavrosh.database.getDatabase
import com.viktoriagavrosh.repositories.FolkWorkRepository
import com.viktoriagavrosh.repositories.OfflineForkWorkRepository
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
    fun provideRepository(
        appDatabase: AppDatabase
    ): FolkWorkRepository {
        return OfflineForkWorkRepository(appDatabase)
    }
}
