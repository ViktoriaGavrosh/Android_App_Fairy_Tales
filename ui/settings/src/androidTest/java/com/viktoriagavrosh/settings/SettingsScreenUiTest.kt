package com.viktoriagavrosh.settings

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.viktoriagavrosh.settings.utils.onNodeWithTagById
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class SettingsScreenUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun settingsScreen_verticalScreen_error() {
        setSettingsScreen(
            isError = true,
            isVerticalScreen = true
        )
        composeTestRule.onNodeWithTagById(R.string.error_screen_test_tag)
            .assertExists("No error screen")
            .assertIsDisplayed()
    }

    @Test
    fun settingsScreen_horizontalScreen_error() {
        setSettingsScreen(
            isError = true,
            isVerticalScreen = false
        )
        composeTestRule.onNodeWithTagById(R.string.error_screen_test_tag)
            .assertExists("No error screen")
            .assertIsDisplayed()
    }

    private fun setSettingsScreen(isError: Boolean, isVerticalScreen: Boolean) {
        composeTestRule.setContent {
            FairyTalesTheme {
                SettingsScreen(
                    textSizeProvider = { 24.0F },
                    isErrorProvider = { isError },
                    isVerticalScreen = isVerticalScreen,
                    onErrorButtonClick = {},
                    onBackClick = {},
                    onTextSizeUpdate = {},
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
