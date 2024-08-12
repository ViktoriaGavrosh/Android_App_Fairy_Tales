package com.viktoriagavrosh.fairytales.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.viktoriagavrosh.fairytales.model.FolkWork
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
    private lateinit var folkWorkDao: TaleDao
    private lateinit var appDatabase: AppDatabase
    private val folkWork1 = FolkWork(
        id = 1,
        genre = "story",
        title = "Title",
        text = "Text",
        answer = null,
        imageUri = "",
        audioUri = null,
        isFavorite = false
    )
    private val folkWork2 = FolkWork(
        id = 2,
        genre = "story",
        title = "Title2",
        text = "Text2",
        answer = null,
        imageUri = "",
        audioUri = null,
        isFavorite = true
    )
    private val folkWork3 = FolkWork(
        id = 3,
        genre = "puzzle",
        title = "Title3",
        text = "Text3",
        answer = "answer3",
        imageUri = "",
        audioUri = null,
        isFavorite = false
    )

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        folkWorkDao = appDatabase.taleDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        appDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertsItemIntoDb() = runBlocking {
        addItemToDb()
        val allItems = folkWorkDao.getAllTales("story").first()
        assertEquals(allItems[0], folkWork1)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllWorks_returnsAllStoriesFromDB() = runBlocking {
        addTwoItemsToDb()
        val allItems = folkWorkDao.getAllTales("story").first()
        assertEquals(allItems[0], folkWork1)
        assertEquals(allItems[1], folkWork2)

    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllWorks_returnsAllPuzzlesFromDB() = runBlocking {
        addThreeItemsToDb()
        val allItems = folkWorkDao.getAllTales("puzzle").first()
        assertEquals(allItems[0], folkWork3)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllFavoriteWorks_returnsAllFavoriteStoriesFromDb() = runBlocking {
        addTwoItemsToDb()
        val allItems = folkWorkDao.getAllFavoriteTales("story").first()
        assertEquals(allItems[0], folkWork2)
    }

    @Test
    @Throws(Exception::class)
    fun daoUpdateFavoriteWork_updatedItemInDb() = runBlocking {
        val isTrueFavorite = 1
        val isFavoriteExpected = true
        addItemToDb()
        folkWorkDao.updateFavoriteTale(1, isTrueFavorite)
        val allItems = folkWorkDao.getAllFavoriteTales("story").first()
        assertEquals(allItems[0].isFavorite, isFavoriteExpected)
    }

    private suspend fun addItemToDb() {
        folkWorkDao.insert(folkWork1)
    }

    private suspend fun addTwoItemsToDb() {
        folkWorkDao.insert(folkWork1)
        folkWorkDao.insert(folkWork2)
    }

    private suspend fun addThreeItemsToDb() {
        folkWorkDao.insert(folkWork1)
        folkWorkDao.insert(folkWork2)
        folkWorkDao.insert(folkWork3)
    }
}
