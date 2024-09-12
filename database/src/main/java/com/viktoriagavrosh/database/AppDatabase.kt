package com.viktoriagavrosh.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.viktoriagavrosh.database.dao.AppDao
import com.viktoriagavrosh.database.model.FolkDb
import com.viktoriagavrosh.database.model.RiddleDb
import com.viktoriagavrosh.database.model.TaleDb

// Database interface for testing
interface TaleAppDatabase {
    val taleDao: AppDao
}

/**
 * Database class for working with other modules
 */
class AppDatabase internal constructor(
    private val database: AppRoomDatabase
) : TaleAppDatabase {
    override val taleDao: AppDao
        get() = database.taleDao()
}

/**
 * Database class with a singleton Instance object.
 */
@Database(
    entities = [TaleDb::class, FolkDb::class, RiddleDb::class],
    version = 3
)
internal abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun taleDao(): AppDao
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
        .createFromAsset("database/fairytales3.db")
        .fallbackToDestructiveMigration()  // TODO 111
        .build()

    return AppDatabase(appRoomDatabase)
}
