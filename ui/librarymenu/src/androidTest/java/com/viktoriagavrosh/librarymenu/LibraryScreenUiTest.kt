package com.viktoriagavrosh.librarymenu

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.viktoriagavrosh.librarymenu.utils.onNodeWithTagById
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class LibraryScreenUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

        @Test
        fun libraryScreen_verticalScreen_error() {
            setLibraryScreen(
                isError = true,
                isVerticalScreen = true
            )
            composeTestRule.onNodeWithTagById(R.string.error_screen_test_tag)
                .assertExists("No error screen")
                .assertIsDisplayed()
        }

        @Test
        fun libraryScreen_horizontalScreen_error() {
            setLibraryScreen(
                isError = true,
                isVerticalScreen = false
            )
            composeTestRule.onNodeWithTagById(R.string.error_screen_test_tag)
                .assertExists("No error screen")
                .assertIsDisplayed()
        }

    private fun setLibraryScreen(isError: Boolean, isVerticalScreen: Boolean) {
        composeTestRule.setContent {
            FairyTalesTheme {
                LibraryScreen(
                    isErrorProvider = { isError },
                    isVerticalScreen = isVerticalScreen,
                    onTaleClick = {},
                    onRiddleClick = {},
                    onFolkClick = {},
                    onAddTaleClick = {},
                    onRandomClick = {},
                    onBackClick = {},
                    onErrorButtonClick = {},
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
