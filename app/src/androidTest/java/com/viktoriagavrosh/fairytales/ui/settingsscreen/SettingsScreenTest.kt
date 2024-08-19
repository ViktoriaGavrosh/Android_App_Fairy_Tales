package com.viktoriagavrosh.fairytales.ui.settingsscreen

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme
import com.viktoriagavrosh.fairytales.ui.utils.onNodeWithContentDescriptionById
import com.viktoriagavrosh.fairytales.ui.utils.onNodeWithTextById
import org.junit.Rule
import org.junit.Test

class SettingsScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun settingsScreen_verticalScreen_backButtonIsDisplayed() {
        setSettingsScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.back)
            .assertExists("No icon back")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun settingsScreen_verticalScreen_titleOnTopBarIsDisplayed() {
        setSettingsScreen()
        composeTestRule.onNodeWithTextById(R.string.settings_title)
            .assertExists("No settings top bar title")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    private fun setSettingsScreen() {
        composeTestRule.setContent {
            FairyTalesTheme {
                SettingsScreen(
                    uiState = SettingsState(textSize = 24.0F),
                    onTextSizeUpdate = {},
                    onBackClick = {},
                )
            }
        }
    }
}
