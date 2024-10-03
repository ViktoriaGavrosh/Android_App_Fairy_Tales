package com.viktoriagavrosh.ui.reader

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.viktoriagavrosh.reader.elements.HorizontalReaderContent
import com.viktoriagavrosh.reader.model.ReadBook
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class HorizontalReaderContentUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun readerContent_horizontalScreen_imageIsDisplayed() {
        val book = ReadBook(title = "Book title")
        setHorizontalContent(book = book)
        composeTestRule.onNodeWithContentDescription(book.title)
            .assertExists("No image")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun readerContent_horizontalScreen_textIsDisplayed() {
        val book = ReadBook(text = "Book text")
        setHorizontalContent(book = book)
        composeTestRule.onNodeWithText(book.text)
            .assertExists("No text")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    private fun setHorizontalContent(book: ReadBook) {
        composeTestRule.setContent {
            FairyTalesTheme {
                HorizontalReaderContent(
                    bookProvider = { book },
                    textSizeProvider = { 24.0F },
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
