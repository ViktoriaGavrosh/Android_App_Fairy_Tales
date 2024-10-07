package com.viktoriagavrosh.uikit.button

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.viktoriagavrosh.uikit.buttons.FlowerMenuButton
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class FlowerMenuButtonUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun uikit_flowerMenuButton_textIsDisplayed() {
        val text = "Text"
        setContent(text)
        composeTestRule.onNodeWithText(text)
            .assertExists("No text")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    private fun setContent(text: String = "") {
        composeTestRule.setContent {
            FairyTalesTheme {
                FlowerMenuButton(
                    text = text,
                    onClick = {}
                )
            }
        }
    }
}
