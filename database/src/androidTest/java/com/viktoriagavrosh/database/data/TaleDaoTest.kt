package com.viktoriagavrosh.database.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.viktoriagavrosh.database.AppRoomDatabase
import com.viktoriagavrosh.database.dao.TaleDao
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
class TaleDaoTest {
    private lateinit var taleDao: TaleDao
    private lateinit var appDatabase: AppRoomDatabase
    private val tales = List(3) {
        val id = it + 1
        TaleDb(
            id = id,
            genre = if (id % 2 == 0) "animal" else "fairy",
            title = "Title $id",
            text = "Text $id",
            isFavorite = id % 2 == 0,
            isNight = id % 2 == 0,
            isChangeable = id % 2 == 0,
            imageUrl = "",
            audioUrl = null,
            quizUrl = null,
            gameUrl = null,
            questionsUrl = null,
        )
    }

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
    fun taleDao_insert_addTaleIntoDb() = runBlocking {
        addItemToDb()
        val allItems = taleDao.getAllTales("fairy").first()
        assertEquals(tales[0], allItems[0])
    }

    @Test
    @Throws(Exception::class)
    fun taleDao_getAllTales_returnAllFairyFromDB() = runBlocking {
        addThreeItemsToDb()
        val expectedTales = tales.filter { it.genre == "fairy" }
        val actualTales = taleDao.getAllTales("fairy").first()
        assertEquals(expectedTales, actualTales)
    }

    @Test
    @Throws(Exception::class)
    fun taleDao_getAllTales_returnAllAnimalFromDB() = runBlocking {
        addThreeItemsToDb()
        val expectedTales = tales.filter { it.genre == "animal" }
        val actualTales = taleDao.getAllTales("animal").first()
        assertEquals(expectedTales, actualTales)
    }

    @Test
    @Throws(Exception::class)
    fun taleDao_getTaleById_returnTaleFromDB() = runBlocking {
        addThreeItemsToDb()
        val actualTale = taleDao.getTaleById(3).first()
        assertEquals(tales[2], actualTale)
    }

    @Test
    @Throws(Exception::class)
    fun taleDao_getFavoriteTales_returnFavoriteTalesFromDB() = runBlocking {
        addThreeItemsToDb()
        val expectedTales = tales.filter { it.isFavorite }
        val actualTales = taleDao.getFavoriteTales().first()
        assertEquals(expectedTales, actualTales)
    }

    @Test
    @Throws(Exception::class)
    fun taleDao_getNightTales_returnNightTalesFromDB() = runBlocking {
        addThreeItemsToDb()
        val expectedTales = tales.filter { it.isNight }
        val actualTales = taleDao.getNightTales().first()
        assertEquals(expectedTales, actualTales)
    }

    @Test
    @Throws(Exception::class)
    fun taleDao_getAllTaleId_returnAllIdFromDB() = runBlocking {
        addThreeItemsToDb()
        val expectedTales = tales.map { it.id }
        val actualTales = taleDao.getAllTaleId().first()
        assertEquals(expectedTales, actualTales)
    }

    @Test
    @Throws(Exception::class)
    fun taleDao_updateFavoriteTale_updateItemInDb() = runBlocking {
        val numberTrueFavorite = 1
        val expectedValue = true
        addItemToDb()
        taleDao.updateFavoriteTale(
            id = 1,
            isFavorite = numberTrueFavorite,
        )
        val actualValue = taleDao.getFavoriteTales().first()[0].isFavorite
        assertEquals(expectedValue, actualValue)
    }

    private suspend fun addItemToDb() {
        taleDao.insert(tales[0])
    }

    private suspend fun addThreeItemsToDb() {
        taleDao.insert(tales[0])
        taleDao.insert(tales[1])
        taleDao.insert(tales[2])
    }

}
