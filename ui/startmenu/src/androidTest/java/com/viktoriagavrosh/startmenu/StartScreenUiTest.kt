package com.viktoriagavrosh.startmenu

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.viktoriagavrosh.startmenu.utils.onNodeWithTagById
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class StartScreenUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun startScreen_verticalScreen_error() {
        setStartScreen(
            isError = true,
            isVerticalScreen = true
        )
        composeTestRule.onNodeWithTagById(R.string.error_screen_test_tag)
            .assertExists("No error screen")
            .assertIsDisplayed()
    }

    @Test
    fun startScreen_horizontalScreen_error() {
        setStartScreen(
            isError = true,
            isVerticalScreen = false
        )
        composeTestRule.onNodeWithTagById(R.string.error_screen_test_tag)
            .assertExists("No error screen")
            .assertIsDisplayed()
    }


    private fun setStartScreen(isError: Boolean, isVerticalScreen: Boolean) {
        composeTestRule.setContent {
            FairyTalesTheme {
                StartScreen(
                    isErrorProvider = { isError },
                    isVerticalScreen = isVerticalScreen,
                    onSettingsClick = {},
                    onErrorButtonClick = {},
                    onNightClick = {},
                    onRandomClick = {},
                    onLibraryClick = {},
                    onFavoriteClick = {},
                    onLastTaleClick = {},
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
