package com.viktoriagavrosh.riddle

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.viktoriagavrosh.riddle.model.ReadRiddle
import com.viktoriagavrosh.riddle.utils.onNodeWithTagById
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class RiddleScreenUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun riddleScreen_verticalScreen_error() {
        setScreen(
            isError = true,
            isVerticalScreen = true
        )
        composeTestRule.onNodeWithTagById(R.string.error_screen_test_tag)
            .assertExists("No error screen")
            .assertIsDisplayed()
    }

    @Test
    fun riddleScreen_horizontalScreen_error() {
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
                RiddleScreen(
                    riddleProvider = { ReadRiddle() },
                    textSizeProvider = { 24.0F },
                    isErrorProvider = { isError },
                    isVerticalScreen = isVerticalScreen,
                    onBackClick = {},
                    onSettingsClick = {},
                    onErrorButtonClick = {},
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
