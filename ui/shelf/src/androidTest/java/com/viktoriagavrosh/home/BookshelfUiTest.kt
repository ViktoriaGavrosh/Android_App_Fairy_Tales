package com.viktoriagavrosh.home

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.viktoriagavrosh.home.fake.FakeSource
import com.viktoriagavrosh.home.utils.onNodeWithContentDescriptionForStringId
import com.viktoriagavrosh.shelf.elements.Bookshelf
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class BookshelfUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun bookshelf_verticalScreen_backButtonOnTopBarIsDisplayed() {
        setScreen(
            isVerticalScreen = true
        )
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.back)
            .assertExists("No back button on topBar")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun bookshelf_verticalScreen_titleOnTopBarIsDisplayed() {
        setScreen(
            isVerticalScreen = true
        )
        composeTestRule.onNodeWithText("TopBar title")
            .assertExists("No title on topBar")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun bookshelf_horizontalScreen_backButtonOnTopBarIsDisplayed() {
        setScreen(
            isVerticalScreen = false
        )
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.back)
            .assertExists("No back button on topBar")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun bookshelf_horizontalScreen_titleOnTopBarIsDisplayed() {
        setScreen(
            isVerticalScreen = false
        )
        composeTestRule.onNodeWithText("TopBar title")
            .assertExists("No title on topBar")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    private fun setScreen(isVerticalScreen: Boolean) {
        composeTestRule.setContent {
            FairyTalesTheme {
                Bookshelf(
                    booksProvider = { FakeSource.books },
                    topBarTitle = "TopBar title",
                    isVerticalScreen = isVerticalScreen,
                    isHeartShow = false,
                    isBlurImages = false,
                    onCardClick = {},
                    onBackClick = {},
                    onHeartClick = {},
                )
            }
        }
    }
}
