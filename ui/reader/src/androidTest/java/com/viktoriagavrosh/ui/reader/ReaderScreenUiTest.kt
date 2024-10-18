package com.viktoriagavrosh.ui.reader

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.viktoriagavrosh.reader.ReaderScreen
import com.viktoriagavrosh.reader.model.ReadBook
import com.viktoriagavrosh.ui.reader.utils.onNodeWithTagById
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class ReaderScreenUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun readerScreen_verticalScreen_error() {
        setScreen(
            isError = true,
            isVerticalScreen = true
        )
        composeTestRule.onNodeWithTagById(R.string.error_screen_test_tag)
            .assertExists("No error screen")
            .assertIsDisplayed()
    }

    @Test
    fun readerScreen_horizontalScreen_error() {
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
                ReaderScreen(
                    bookProvider = { ReadBook() },
                    textSizeProvider = { 24.0F },
                    isErrorProvider = { isError },
                    isVerticalScreen = isVerticalScreen,
                    onBackClick = {},
                    onSettingsClick = {},
                    onInfoClick = {},
                    onErrorButtonClick = {},
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
