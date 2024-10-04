package com.viktoriagavrosh.uikit

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.unit.dp
import com.viktoriagavrosh.librarymenu.utils.onNodeWithTextById
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class ErrorScreenUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun uikit_errorScreen_textIsDisplayed() {
        setContent()
        composeTestRule.onNodeWithTextById(R.string.error_text)
            .assertExists("No text on errorScreen")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun uikit_errorScreen_buttonIsDisplayed() {
        setContent()
        composeTestRule.onNodeWithTextById(R.string.error_button_text)
            .assertExists("No button on errorScreen")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun uikit_errorScreen_buttonSizeValid() {
        setContent()
        composeTestRule.onNodeWithTextById(R.string.error_button_text)
            .assertHeightIsAtLeast(48.dp)
    }

    private fun setContent() {
        composeTestRule.setContent {
            FairyTalesTheme {
                ErrorScreen(
                    onButtonClick = {},
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
