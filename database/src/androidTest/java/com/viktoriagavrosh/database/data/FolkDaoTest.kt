package com.viktoriagavrosh.database.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.viktoriagavrosh.database.AppRoomDatabase
import com.viktoriagavrosh.database.dao.FolkDao
import com.viktoriagavrosh.database.model.FolkDb
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class FolkDaoTest {
    private lateinit var folkDao: FolkDao
    private lateinit var appDatabase: AppRoomDatabase
    private val folk1 = FolkDb(
        id = 1,
        genre = "lullaby",
        title = "Title",
        text = "Text",
        imageUrl = "",
    )
    private val folk2 = FolkDb(
        id = 2,
        genre = "poem",
        title = "Title2",
        text = "Text2",
        imageUrl = "",
    )
    private val folk3 = FolkDb(
        id = 3,
        genre = "counting",
        title = "Title3",
        text = "Text3",
        imageUrl = "",
    )

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppRoomDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        folkDao = appDatabase.folkDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        appDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun folkDao_insert_addFolkIntoDb() = runBlocking {
        addItemToDb()
        val allItems = folkDao.getAllFolks("lullaby").first()
        assertEquals(allItems[0], folk1)
    }

    @Test
    @Throws(Exception::class)
    fun folkDao_getAllFolks_returnAllLullabyFromDB() = runBlocking {
        addTwoItemsToDb()
        val allItems = folkDao.getAllFolks("lullaby").first()
        assertEquals(allItems[0], folk1)
    }

    @Test
    @Throws(Exception::class)
    fun folkDao_getAllTales_returnAllPoemsFromDB() = runBlocking {
        addThreeItemsToDb()
        val allItems = folkDao.getAllFolks("poem").first()
        assertEquals(allItems[0], folk2)
    }

    @Test
    @Throws(Exception::class)
    fun folkDao_getAllTales_returnAllCountingFromDB() = runBlocking {
        addThreeItemsToDb()
        val allItems = folkDao.getAllFolks("counting").first()
        assertEquals(allItems[0], folk3)
    }

    @Test
    @Throws(Exception::class)
    fun folkDao_getFolkById_returnFolkFromDB() = runBlocking {
        addThreeItemsToDb()
        val actualTale = folkDao.getFolkById(3).first()
        assertEquals(actualTale, folk3)
    }

    private suspend fun addItemToDb() {
        folkDao.insert(folk1)
    }

    private suspend fun addTwoItemsToDb() {
        folkDao.insert(folk1)
        folkDao.insert(folk2)
    }

    private suspend fun addThreeItemsToDb() {
        folkDao.insert(folk1)
        folkDao.insert(folk2)
        folkDao.insert(folk3)
    }
}
