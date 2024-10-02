package com.viktoriagavrosh.home

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.viktoriagavrosh.home.fake.FakeSource
import com.viktoriagavrosh.home.utils.onNodeWithTagById
import com.viktoriagavrosh.shelf.ShelfScreen
import com.viktoriagavrosh.shelf.utils.Tabs
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class ShelfScreenUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun shelfScreen_verticalScreen_error() {
        setScreen(
            isError = true,
            isVerticalScreen = true
        )
        composeTestRule.onNodeWithTagById(R.string.error_screen_test_tag)
            .assertExists("No error screen")
            .assertIsDisplayed()
    }

    @Test
    fun shelfScreen_horizontalScreen_error() {
        setScreen(
            isError = true,
            isVerticalScreen = false
        )
        composeTestRule.onNodeWithTagById(R.string.error_screen_test_tag)
            .assertExists("No error screen")
            .assertIsDisplayed()
    }

    private fun setScreen(isError: Boolean, isVerticalScreen: Boolean) {
        composeTestRule.setContent {
            FairyTalesTheme {
                ShelfScreen(
                    booksProvider = { FakeSource.books },
                    selectedTabProvider = { Tabs.TaleTab.Animal },
                    isErrorProvider = { isError },
                    isVerticalScreen = isVerticalScreen,
                    onCardClick = {},
                    onHeartClick = {},
                    onBackClick = {},
                    onErrorButtonClick = {},
                    onTabClick = {},
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}
