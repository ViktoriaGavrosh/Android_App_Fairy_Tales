package com.viktoriagavrosh.fairytales

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.viktoriagavrosh.database.AppDatabase
import com.viktoriagavrosh.database.getDatabase
import com.viktoriagavrosh.datastore.AppPreferencesManager
import com.viktoriagavrosh.datastore.PreferencesManager
import com.viktoriagavrosh.repositories.AddRepository
import com.viktoriagavrosh.repositories.DatastoreSettingsRepository
import com.viktoriagavrosh.repositories.MenuRepository
import com.viktoriagavrosh.repositories.OfflineAddRepository
import com.viktoriagavrosh.repositories.OfflineMenuRepository
import com.viktoriagavrosh.repositories.OfflineReadRepository
import com.viktoriagavrosh.repositories.OfflineShelfRepository
import com.viktoriagavrosh.repositories.ReadRepository
import com.viktoriagavrosh.repositories.SettingsRepository
import com.viktoriagavrosh.repositories.ShelfRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * for getting settings from [DataStore]
 */
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

/**
 * Module for dependency injection (dagger hilt)
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
    fun providePreferencesManager(
        dataStore: DataStore<Preferences>
    ): PreferencesManager {
        return AppPreferencesManager(dataStore)
    }

    @Provides
    @Singleton
    fun provideShelfRepository(
        appDatabase: AppDatabase
    ): ShelfRepository {
        return OfflineShelfRepository(appDatabase)
    }

    @Provides
    @Singleton
    fun provideAddRepository(
        appDatabase: AppDatabase
    ): AddRepository {
        return OfflineAddRepository(appDatabase)
    }

    @Provides
    @Singleton
    fun provideReadRepository(
        appDatabase: AppDatabase,
        preferencesManager: PreferencesManager
    ): ReadRepository {
        return OfflineReadRepository(appDatabase, preferencesManager)
    }

    @Provides
    @Singleton
    fun provideMenuRepository(
        appDatabase: AppDatabase,
        preferencesManager: PreferencesManager
    ): MenuRepository {
        return OfflineMenuRepository(appDatabase, preferencesManager)
    }

    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext
        context: Context
    ): DataStore<Preferences> {
        return context.dataStore
    }

    @Provides
    @Singleton
    fun provideSettingsRepository(
        preferencesManager: PreferencesManager
    ): SettingsRepository {
        return DatastoreSettingsRepository(preferencesManager)
    }
}
