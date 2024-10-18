package com.viktoriagavrosh.uikit

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeLeft
import androidx.compose.ui.test.swipeRight
import androidx.compose.ui.unit.dp
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class SettingsSliderUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun uikit_settingsSlider_textIsDisplayed() {
        val text = "Slider text"
        setContent(text = text)
        composeTestRule.onNodeWithText(text)
            .assertExists("No text")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun uikit_settingsSlider_sliderIsDisplayed() {
        setContent()
        composeTestRule.onNodeWithTag("Slider")
            .assertExists("No slider")
            .assertIsDisplayed()
    }

    @Test
    fun uikit_settingsSlider_verifyLargeText() {
        val text = "Text"
        setContent(settingsSize = 60.0F, text = text)
        composeTestRule.onNodeWithText(text)
            .assertHeightIsAtLeast(30.dp)
    }

    @Test
    fun uikit_settingsSlider_verifySmallText() {
        val text = "Text"
        setContent(settingsSize = 10.0F, text = text)
        try {
            composeTestRule.onNodeWithText(text)
                .assertHeightIsAtLeast(30.dp)
        } catch (e: AssertionError) {
            return
        }
        throw AssertionError("text not small when enter small textSize")
    }

    @Test
    fun uikit_settingsSlider_increasedLetters() {
        val text = "Text"
        setContent(settingsSize = 35.0F, text = text)
        composeTestRule.onNodeWithTag("Slider")
            .performTouchInput { this.swipeRight() }
        composeTestRule.onNodeWithText(text)
            .assertHeightIsAtLeast(50.dp)
    }

    @Test
    fun uikit_settingsSlider_decreasedLetters() {
        val text = "Text"
        setContent(settingsSize = 50.0F, text = text)
        composeTestRule.onNodeWithTag("Slider")
            .performTouchInput { this.swipeLeft() }

        // needs to get the opposite of assertHeightIsAtLeast
        try {
            composeTestRule.onNodeWithText(text)
                .assertHeightIsAtLeast(50.dp)
        } catch (e: AssertionError) {
            return
        }
        throw AssertionError("swipe left on textSize slider does not work")
    }

    private fun setContent(settingsSize: Float = 24.0F, text: String = "") {
        composeTestRule.setContent {
            FairyTalesTheme {
                SettingSlider(
                    text = text,
                    settingSizeProvider = { settingsSize },
                    onSettingSizeUpdate = {},
                    testTag = "Slider"
                )
            }
        }
    }
}
