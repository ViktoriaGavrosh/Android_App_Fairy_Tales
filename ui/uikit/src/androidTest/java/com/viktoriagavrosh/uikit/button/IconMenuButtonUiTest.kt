package com.viktoriagavrosh.uikit.button

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uikit.buttons.IconMenuButton
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class IconMenuButtonUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun uikit_iconMenuButton_textIsDisplayed() {
        val text = "Text"
        setContent(text)
        composeTestRule.onNodeWithText(text)
            .assertExists("No text")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun uikit_iconMenuButton_iconIsDisplayed() {
        val text = "Text"
        setContent(text)
        composeTestRule.onNodeWithContentDescription(text)
            .assertExists("No icon")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    private fun setContent(text: String = "") {
        composeTestRule.setContent {
            FairyTalesTheme {
                IconMenuButton(
                    iconId = R.drawable.ic_favorite_false,
                    text = text,
                    onClick = {}
                )
            }
        }
    }
}
