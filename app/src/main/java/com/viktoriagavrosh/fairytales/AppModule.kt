package com.viktoriagavrosh.fairytales

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.viktoriagavrosh.fairytales.data.AppDatabase
import com.viktoriagavrosh.fairytales.data.DatastoreSettingsRepository
import com.viktoriagavrosh.fairytales.data.OfflineTaleRepository
import com.viktoriagavrosh.fairytales.data.settingsdatabase.PreferencesManager
import com.viktoriagavrosh.fairytales.data.SettingsRepository
import com.viktoriagavrosh.fairytales.data.TaleRepository
import com.viktoriagavrosh.fairytales.data.getDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// TODO may be private ???
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

/**
 * Object to dependency injection (dagger hilt)
 */
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext
        context: Context
    ): AppDatabase {
        return getDatabase(context = context)
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
