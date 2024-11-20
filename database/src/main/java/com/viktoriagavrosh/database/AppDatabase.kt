package com.viktoriagavrosh.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.viktoriagavrosh.database.dao.FolkDao
import com.viktoriagavrosh.database.dao.RiddleDao
import com.viktoriagavrosh.database.dao.TaleDao
import com.viktoriagavrosh.database.model.FolkDb
import com.viktoriagavrosh.database.model.RiddleDb
import com.viktoriagavrosh.database.model.TaleDb

interface TaleAppDatabase {
    val taleDao: TaleDao
    val folkDao: FolkDao
    val riddleDao: RiddleDao
}

/**
 * Database class for working with other modules
 *
 * @param database instance [AppRoomDatabase]
 * @return [TaleAppDatabase] object
 */
class AppDatabase internal constructor(
    private val database: AppRoomDatabase
) : TaleAppDatabase {
    override val taleDao: TaleDao
        get() = database.taleDao()
    override val folkDao: FolkDao
        get() = database.folkDao()
    override val riddleDao: RiddleDao
        get() = database.riddleDao()
}

/**
 * Database class with a singleton Instance object.
 */
@Database(
    entities = [TaleDb::class, FolkDb::class, RiddleDb::class],
    version = 5,
    exportSchema = false,
)
internal abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun taleDao(): TaleDao
    abstract fun folkDao(): FolkDao
    abstract fun riddleDao(): RiddleDao
}

/**
 *  Function build [AppDatabase] object
 *
 *  @param context local context
 *  @return [AppDatabase] object
 */
fun getDatabase(context: Context): AppDatabase {
    val appRoomDatabase = Room.databaseBuilder(
        context = context,
        klass = AppRoomDatabase::class.java,
        name = "fairytales"
    )
        .createFromAsset("database/fairytales3.db")
        .build()

    return AppDatabase(appRoomDatabase)
}
