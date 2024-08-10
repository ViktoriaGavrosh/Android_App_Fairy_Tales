package com.viktoriagavrosh.fairytales

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.viktoriagavrosh.database.AppDatabase
import com.viktoriagavrosh.database.getDatabase
import com.viktoriagavrosh.datastore.PreferencesManager
import com.viktoriagavrosh.repositories.settings.DatastoreSettingsRepository
import com.viktoriagavrosh.repositories.settings.SettingsRepository
import com.viktoriagavrosh.repositories.tale.OfflineTaleRepository
import com.viktoriagavrosh.repositories.tale.TaleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

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
