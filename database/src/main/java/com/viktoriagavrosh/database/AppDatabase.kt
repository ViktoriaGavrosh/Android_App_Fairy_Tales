package com.viktoriagavrosh.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.viktoriagavrosh.database.dao.FolkWorkDao
import com.viktoriagavrosh.database.model.FolkWorkDB


class AppDatabase internal constructor(
    private val database: AppRoomDatabase
) {
    val folkWorkDao: FolkWorkDao
        get() = database.folkWorkDao()
}

/**
 * Database class with a singleton Instance object.
 */
@Database(
    entities = [FolkWorkDB::class],
    version = 2
)
internal abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun folkWorkDao(): FolkWorkDao
    /*
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

     */
}

fun getDatabase(context: Context): AppDatabase {
    val appRoomDatabase = Room.databaseBuilder(
        context = context,
        klass = AppRoomDatabase::class.java,
        name = "fairytales"
    )
        .createFromAsset("database/fairytales2.db")
        .fallbackToDestructiveMigration()
        .build()

    return AppDatabase(appRoomDatabase)
}
