package com.viktoriagavrosh.fairytales.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.viktoriagavrosh.fairytales.model.TaleDb

// Database interface for testing  - TODO do through Hilt
interface TaleAppDatabase {
    val taleDao: TaleDao
}

/**
 * Database class
 */
class AppDatabase internal constructor(      // TODO maybe delete?
    private val database: AppRoomDatabase
) : TaleAppDatabase {
    override val taleDao: TaleDao
        get() = database.taleDao()
}

/**
 * Database class with a singleton Instance object
 */
@Database(
    entities = [TaleDb::class],
    version = 2
)
internal abstract class AppRoomDatabase : RoomDatabase() {   // TODO maybe private ?
    abstract fun taleDao(): TaleDao
}

/**
 * Function build [AppDatabase] object
 */
internal fun getDatabase(context: Context): AppDatabase {
    val appRoomDatabase = Room.databaseBuilder(
        context = context,
        klass = AppRoomDatabase::class.java,
        name = "fairytales"
    )
        .createFromAsset("database/fairytales2.db")
        .build()
    return AppDatabase(appRoomDatabase)
}
