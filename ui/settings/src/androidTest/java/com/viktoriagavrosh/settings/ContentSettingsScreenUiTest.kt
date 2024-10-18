package com.viktoriagavrosh.settings

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.viktoriagavrosh.settings.elements.ContentSettingsScreen
import com.viktoriagavrosh.settings.utils.onNodeWithContentDescriptionForStringId
import com.viktoriagavrosh.settings.utils.onNodeWithTextById
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class ContentSettingsScreenUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun settingsScreen_verticalScreen_backButtonOnTopBarIsDisplayed() {
        setContentScreen(isVerticalScreen = true)
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.back)
            .assertExists("No back button on topBar")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun settingsScreen_horizontalScreen_backButtonOnTopBarIsDisplayed() {
        setContentScreen(isVerticalScreen = false)
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.back)
            .assertExists("No back button on topBar")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun settingsScreen_verticalScreen_titleOnTopBarIsDisplayed() {
        setContentScreen(isVerticalScreen = true)
        composeTestRule.onNodeWithTextById(R.string.settings_title)
            .assertExists("No title on topBar")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun settingsScreen_horizontalScreen_titleOnTopBarIsDisplayed() {
        setContentScreen(isVerticalScreen = false)
        composeTestRule.onNodeWithTextById(R.string.settings_title)
            .assertExists("No title on topBar")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    private fun setContentScreen(isVerticalScreen: Boolean) {
        composeTestRule.setContent {
            FairyTalesTheme {
                ContentSettingsScreen(
                    textSizeProvider = { 24.0F },
                    isVerticalScreen = isVerticalScreen,
                    onBackClick = {},
                    onTextSizeUpdate = {},
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
