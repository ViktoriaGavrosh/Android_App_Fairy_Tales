package com.viktoriagavrosh.database.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.viktoriagavrosh.database.AppRoomDatabase
import com.viktoriagavrosh.database.dao.RiddleDao
import com.viktoriagavrosh.database.model.RiddleDb
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class RiddleDaoTest {
    private lateinit var riddleDao: RiddleDao
    private lateinit var appDatabase: AppRoomDatabase
    private val riddles = List(3) {
        val id = it + 1
        RiddleDb(
            id = id,
            title = "Title $id",
            text = "Text $id",
            answer = "Answer $id",
            imageUrl = "",
        )
    }

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppRoomDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        riddleDao = appDatabase.riddleDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        appDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun riddleDao_insert_addRiddleIntoDb() = runBlocking {
        addItemToDb()
        val allItems = riddleDao.getAllRiddles().first()
        assertEquals(allItems[0], riddles[0])
    }

    @Test
    @Throws(Exception::class)
    fun riddleDao_getAllRiddles_returnAllRiddlesFromDB() = runBlocking {
        addThreeItemsToDb()
        val allItems = riddleDao.getAllRiddles().first()
        assertEquals(allItems, riddles)
    }

    @Test
    @Throws(Exception::class)
    fun riddleDao_getRiddleById_returnRiddleFromDB() = runBlocking {
        addThreeItemsToDb()
        val actualTale = riddleDao.getRiddleById(3).first()
        assertEquals(actualTale, riddles[2])
    }

    private suspend fun addItemToDb() {
        riddleDao.insert(riddles[0])
    }

    private suspend fun addThreeItemsToDb() {
        riddleDao.insert(riddles[0])
        riddleDao.insert(riddles[1])
        riddleDao.insert(riddles[2])
    }
}
