package com.viktoriagavrosh.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.viktoriagavrosh.database.dao.TaleDao
import com.viktoriagavrosh.database.model.TaleDb

// Database interface for testing
interface TaleAppDatabase {
    val taleDao: TaleDao
}

/**
 * Database class for working with other modules
 */
class AppDatabase internal constructor(
    private val database: AppRoomDatabase
) : TaleAppDatabase {
    override val taleDao: TaleDao
        get() = database.taleDao()
}

/**
 * Database class with a singleton Instance object.
 */
@Database(
    entities = [TaleDb::class],
    version = 2
)
internal abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun taleDao(): TaleDao
}

/**
 *  Function build [AppDatabase] object
 */
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
