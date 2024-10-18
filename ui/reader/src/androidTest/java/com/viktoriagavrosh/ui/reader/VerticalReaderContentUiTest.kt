package com.viktoriagavrosh.ui.reader

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.viktoriagavrosh.reader.elements.VerticalReaderContent
import com.viktoriagavrosh.reader.model.ReadBook
import com.viktoriagavrosh.repositories.utils.ShelfGenre
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class VerticalReaderContentUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun readerContent_verticalScreen_imageIsDisplayed() {
        val book = ReadBook(title = "Book title")
        setVerticalContent(book = book)
        composeTestRule.onNodeWithContentDescription(book.title)
            .assertExists("No image")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun readerContent_verticalScreen_textIsDisplayed() {
        val book = ReadBook(text = "Book text")
        setVerticalContent(book = book)
        composeTestRule.onNodeWithText(book.text)
            .assertExists("No text")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun readerContent_verticalScreen_taleTitleIsDisplayed() {
        val book = ReadBook(
            genre = ShelfGenre.Tales.Animal,
            title = "Book title"
        )
        setVerticalContent(book = book)
        composeTestRule.onNodeWithText(book.title)
            .assertExists("No title")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun readerContent_verticalScreen_folkTitleDoesNotDisplayed() {
        val book = ReadBook(
            genre = ShelfGenre.Folks.Poem,
            title = "Book title"
        )
        setVerticalContent(book = book)
        composeTestRule.onNodeWithText(book.title)
            .assertDoesNotExist()
    }

    private fun setVerticalContent(book: ReadBook) {
        composeTestRule.setContent {
            FairyTalesTheme {
                VerticalReaderContent(
                    bookProvider = { book },
                    textSizeProvider = { 24.0F },
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
