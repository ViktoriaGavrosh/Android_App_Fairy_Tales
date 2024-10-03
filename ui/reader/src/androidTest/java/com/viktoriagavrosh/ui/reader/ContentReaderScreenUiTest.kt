package com.viktoriagavrosh.ui.reader

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.viktoriagavrosh.reader.elements.ContentReaderScreen
import com.viktoriagavrosh.reader.model.ReadBook
import com.viktoriagavrosh.repositories.utils.ShelfGenre
import com.viktoriagavrosh.ui.reader.utils.onNodeWithContentDescriptionForStringId
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class ContentReaderScreenUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun readerScreen_verticalScreen_backButtonOnTopBarIsDisplayed() {
        setContentScreen(
            book = ReadBook(),
            isVerticalScreen = true
        )
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.back)
            .assertExists("No back button on topBar")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun readerScreen_horizontalScreen_backButtonOnTopBarIsDisplayed() {
        setContentScreen(
            book = ReadBook(),
            isVerticalScreen = false
        )
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.back)
            .assertExists("No back button on topBar")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun readerScreen_verticalScreen_taleTitleOnTopBarIsDisplayed() {
        setContentScreen(
            book = ReadBook(genre = ShelfGenre.Tales.Animal, title = "Title"),
            isVerticalScreen = true
        )
        val expectedTitle = "Title..."
        composeTestRule.onNodeWithText(expectedTitle)
            .assertExists("No tale title on topBar")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun readerScreen_horizontalScreen_taleTitleOnTopBarIsDisplayed() {
        setContentScreen(
            book = ReadBook(genre = ShelfGenre.Tales.Animal, title = "Title"),
            isVerticalScreen = false
        )
        composeTestRule.onNodeWithText("Title")
            .assertExists("No title on topBar")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun readerScreen_verticalScreen_folkTitleOnTopBarDoesNotDisplayed() {
        setContentScreen(
            book = ReadBook(genre = ShelfGenre.Folks.Poem, title = "Title"),
            isVerticalScreen = true
        )
        composeTestRule.onNodeWithText("Title")
            .assertDoesNotExist()
    }

    @Test
    fun readerScreen_horizontalScreen_folkTitleOnTopBarDoesNotDisplayed() {
        setContentScreen(
            book = ReadBook(genre = ShelfGenre.Folks.Poem, title = "Title"),
            isVerticalScreen = false
        )
        composeTestRule.onNodeWithText("Title")
            .assertDoesNotExist()
    }

    @Test
    fun readerScreen_verticalScreen_taleDropdownMenuButtonOnTopBarIsDisplayed() {
        setContentScreen(
            book = ReadBook(genre = ShelfGenre.Tales.Animal),
            isVerticalScreen = true
        )
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.actions)
            .assertExists("No dropdownMenu button on topBar")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun readerScreen_horizontalScreen_taleDropdownMenuButtonOnTopBarIsDisplayed() {
        setContentScreen(
            book = ReadBook(genre = ShelfGenre.Tales.Animal),
            isVerticalScreen = false
        )
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.actions)
            .assertExists("No dropdownMenu button on topBar")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun readerScreen_verticalScreen_folkDropdownMenuButtonOnTopBarDoesNotDisplayed() {
        setContentScreen(
            book = ReadBook(genre = ShelfGenre.Folks.Poem),
            isVerticalScreen = true
        )
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.actions)
            .assertDoesNotExist()
    }

    @Test
    fun readerScreen_horizontalScreen_folkDropdownMenuButtonOnTopBarDoesNotDisplayed() {
        setContentScreen(
            book = ReadBook(genre = ShelfGenre.Folks.Poem),
            isVerticalScreen = false
        )
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.actions)
            .assertDoesNotExist()
    }

    @Test
    fun readerScreen_verticalScreen_folkSettingsButtonOnTopBarIsDisplayed() {
        setContentScreen(
            book = ReadBook(genre = ShelfGenre.Folks.Poem),
            isVerticalScreen = true
        )
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.settings)
            .assertExists("No settings button on topBar")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun readerScreen_horizontalScreen_folkSettingsButtonOnTopBarIsDisplayed() {
        setContentScreen(
            book = ReadBook(genre = ShelfGenre.Folks.Poem),
            isVerticalScreen = false
        )
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.settings)
            .assertExists("No settings button on topBar")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun readerScreen_verticalScreen_infoButtonOnDropDownMenuIsDisplayed() {
        setContentScreen(
            book = ReadBook(genre = ShelfGenre.Tales.Animal),
            isVerticalScreen = true
        )
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.actions)
            .performClick()
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.info)
            .assertExists("No info button on dropdownMenu")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun readerScreen_verticalScreen_settingsButtonOnDropDownMenuIsDisplayed() {
        setContentScreen(
            book = ReadBook(genre = ShelfGenre.Tales.Animal),
            isVerticalScreen = true
        )
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.actions)
            .performClick()
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.settings)
            .assertExists("No settings button on dropdownMenu")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun readerScreen_horizontalScreen_infoButtonOnDropDownMenuIsDisplayed() {
        setContentScreen(
            book = ReadBook(genre = ShelfGenre.Tales.Animal),
            isVerticalScreen = false
        )
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.actions)
            .performClick()
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.info)
            .assertExists("No info button on dropdownMenu")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun readerScreen_horizontalScreen_settingsButtonOnDropDownMenuIsDisplayed() {
        setContentScreen(
            book = ReadBook(genre = ShelfGenre.Tales.Animal),
            isVerticalScreen = false
        )
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.actions)
            .performClick()
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.settings)
            .assertExists("No settings button on dropdownMenu")
            .assertIsDisplayed()
            .assertHasClickAction()
    }


    private fun setContentScreen(book: ReadBook, isVerticalScreen: Boolean) {
        composeTestRule.setContent {
            FairyTalesTheme {
                ContentReaderScreen(
                    bookProvider = { book },
                    textSizeProvider = { 24.0F },
                    isVerticalScreen = isVerticalScreen,
                    onBackClick = {},
                    onSettingsClick = {},
                    onInfoClick = {},
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
