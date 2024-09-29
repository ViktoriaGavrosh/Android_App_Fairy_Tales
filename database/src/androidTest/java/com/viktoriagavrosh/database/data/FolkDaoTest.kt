package com.viktoriagavrosh.database.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.viktoriagavrosh.database.AppRoomDatabase
import com.viktoriagavrosh.database.dao.FolkDao
import com.viktoriagavrosh.database.model.TaleDb
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
    private lateinit var taleDao: FolkDao
    private lateinit var appDatabase: AppRoomDatabase
    private val tale1 = TaleDb(
        id = 1,
        genre = "story",
        title = "Title",
        text = "Text",
        answer = null,
        imageUrl = "",
        audioUrl = null,
        isFavorite = false
    )
    private val tale2 = TaleDb(
        id = 2,
        genre = "story",
        title = "Title2",
        text = "Text2",
        answer = null,
        imageUrl = "",
        audioUrl = null,
        isFavorite = true
    )
    private val tale3 = TaleDb(
        id = 3,
        genre = "puzzle",
        title = "Title3",
        text = "Text3",
        answer = "answer3",
        imageUrl = "",
        audioUrl = null,
        isFavorite = false
    )

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppRoomDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        taleDao = appDatabase.taleDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        appDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_addTaleIntoDb() = runBlocking {
        addItemToDb()
        val allItems = taleDao.getAllTales("story").first()
        assertEquals(allItems[0], tale1)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllTales_returnsAllTalesFromDB() = runBlocking {
        addTwoItemsToDb()
        val allItems = taleDao.getAllTales("story").first()
        assertEquals(allItems[0], tale1)
        assertEquals(allItems[1], tale2)

    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllTales_returnAllPuzzlesFromDB() = runBlocking {
        addThreeItemsToDb()
        val allItems = taleDao.getAllTales("puzzle").first()
        assertEquals(allItems[0], tale3)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllFavoriteTales_returnAllFavoriteTalesFromDb() = runBlocking {
        addTwoItemsToDb()
        val allItems = taleDao.getAllFavoriteTales("story").first()
        assertEquals(allItems[0], tale2)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetTaleById_returnTaleFromDB() = runBlocking {
        addThreeItemsToDb()
        val actualTale = taleDao.getTaleById(3).first()
        assertEquals(actualTale, tale3)
    }

    @Test
    @Throws(Exception::class)
    fun daoUpdateFavoriteTale_updatedItemInDb() = runBlocking {
        val isTrueFavorite = 1
        val isFavoriteExpected = true
        addItemToDb()
        taleDao.updateFavoriteTale(1, isTrueFavorite)
        val allItems = taleDao.getAllFavoriteTales("story").first()
        assertEquals(allItems[0].isFavorite, isFavoriteExpected)
    }

    private suspend fun addItemToDb() {
        taleDao.insert(tale1)
    }

    private suspend fun addTwoItemsToDb() {
        taleDao.insert(tale1)
        taleDao.insert(tale2)
    }

    private suspend fun addThreeItemsToDb() {
        taleDao.insert(tale1)
        taleDao.insert(tale2)
        taleDao.insert(tale3)
    }
}
