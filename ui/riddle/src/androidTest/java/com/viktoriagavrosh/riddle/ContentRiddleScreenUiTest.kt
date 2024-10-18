package com.viktoriagavrosh.riddle

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.viktoriagavrosh.riddle.elements.ContentRiddleScreen
import com.viktoriagavrosh.riddle.model.ReadRiddle
import com.viktoriagavrosh.riddle.utils.onNodeWithContentDescriptionForStringId
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class ContentRiddleScreenUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun riddleScreen_verticalScreen_backButtonOnTopBarIsDisplayed() {
        setContentScreen(isVerticalScreen = true)
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.back)
            .assertExists("No back button on topBar")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun riddleScreen_horizontalScreen_backButtonOnTopBarIsDisplayed() {
        setContentScreen(isVerticalScreen = false)
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.back)
            .assertExists("No back button on topBar")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun riddleScreen_verticalScreen_SettingsButtonOnTopBarIsDisplayed() {
        setContentScreen(isVerticalScreen = true)
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.settings)
            .assertExists("No settings button on topBar")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun riddleScreen_horizontalScreen_SettingsButtonOnTopBarIsDisplayed() {
        setContentScreen(isVerticalScreen = false)
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.settings)
            .assertExists("No settings button on topBar")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    private fun setContentScreen(isVerticalScreen: Boolean) {
        composeTestRule.setContent {
            FairyTalesTheme {
                ContentRiddleScreen(
                    riddleProvider = { ReadRiddle() },
                    textSizeProvider = { 24.0F },
                    isVerticalScreen = isVerticalScreen,
                    onBackClick = {},
                    onSettingsClick = {},
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
