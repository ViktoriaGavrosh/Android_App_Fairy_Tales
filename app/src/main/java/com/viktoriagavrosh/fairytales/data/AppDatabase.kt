package com.viktoriagavrosh.fairytales.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.viktoriagavrosh.fairytales.model.FolkWork

@Database(
    entities = [FolkWork::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun folkWorkDao(): FolkWorkDao

    companion object {
        @Volatile
        private var Instance: AppDatabase? = null

        /*
        private val migration_1_2 = object : Migration(1,2) {
            override fun migrate(db: SupportSQLiteDatabase) {
            }
        }

         */

        fun getDatabase(context: Context): AppDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "fairytales"
                )
                    .createFromAsset("database/fairytales2.db")
                    //.addMigrations(migration_1_2)
                    .build()
                    .also { Instance = it }
            }
        }
    }
}