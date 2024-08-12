package com.viktoriagavrosh.fairytales.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.viktoriagavrosh.fairytales.model.FolkWork

/**
 * Database class with a singleton Instance object.
 */
@Database(
    entities = [FolkWork::class],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taleDao(): TaleDao

    companion object {
        @Volatile
        private var Instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "fairytales"
                )
                    .createFromAsset("database/fairytales2.db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
