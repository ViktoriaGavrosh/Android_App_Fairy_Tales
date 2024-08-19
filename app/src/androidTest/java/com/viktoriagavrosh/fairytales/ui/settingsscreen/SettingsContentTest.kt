package com.viktoriagavrosh.fairytales.ui.settingsscreen

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeLeft
import androidx.compose.ui.test.swipeRight
import androidx.compose.ui.unit.dp
import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme
import com.viktoriagavrosh.fairytales.ui.utils.onNodeWithTagById
import com.viktoriagavrosh.fairytales.ui.utils.onNodeWithTextById
import org.junit.Rule
import org.junit.Test

class SettingsContentTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun settingsContentScreen_verticalScreen_textSizeTitleIsDisplayed() {
        setSettingsContentScreen()
        composeTestRule.onNodeWithTextById(R.string.textsize_title)
            .assertExists("No text size title")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun settingsContentScreen_verticalScreen_lettersIsDisplayed() {
        setSettingsContentScreen()
        composeTestRule.onNodeWithTextById(R.string.letters)
            .assertExists("No letters")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun settingsContentScreen_verticalScreen_textSizeSliderIsDisplayed() {
        setSettingsContentScreen()
        composeTestRule.onNodeWithTagById(R.string.textsize_slider)
            .assertExists("No textSize slider")
            .assertIsDisplayed()
            .assertIsEnabled()
    }

    @Test
    fun settingsContentScreen_moveRightTextSizeSlider_increasedLetters() {
        setSettingsContentScreen()
        composeTestRule.onNodeWithTagById(R.string.textsize_slider)
            .performTouchInput { swipeRight() }
        composeTestRule.onNodeWithTextById(R.string.letters)
            .assertHeightIsAtLeast(50.dp)
    }

    @Test
    fun settingsContentScreen_moveLeftTextSizeSlider_decreasedLetters() {
        setSettingsContentScreen(textSize = 100.0F)
        composeTestRule.onNodeWithTagById(R.string.textsize_slider)
            .performTouchInput { swipeLeft() }
        // needs to get the opposite of assertHeightIsAtLeast
        try {
            composeTestRule.onNodeWithTextById(R.string.letters)
                .assertHeightIsAtLeast(90.dp)
        } catch (e: AssertionError) {
            return
        }
        throw AssertionError("swipe left on textSize slider does not work")
    }

    @Test
    fun settingsContentScreen_largeTextSize_lettersLarge() {
        setSettingsContentScreen(textSize = 100.0F)
        composeTestRule.onNodeWithTextById(R.string.letters)
            .assertHeightIsAtLeast(50.dp)
    }

    @Test
    fun settingsContentScreen_smallTextSize_lettersSmall() {
        setSettingsContentScreen(textSize = 10.0F)
        // needs to get the opposite of assertHeightIsAtLeast
        try {
            composeTestRule.onNodeWithTextById(R.string.letters)
                .assertHeightIsAtLeast(50.dp)
        } catch (e: AssertionError) {
            return
        }
        throw AssertionError("letters are not small when enter smal textSize")
    }

    private fun setSettingsContentScreen(textSize: Float = 24.0F) {
        composeTestRule.setContent {
            FairyTalesTheme {
                SettingsContent(
                    textSize = textSize,
                    onTextSizeUpdate = {},
                )
            }
        }
    }
}
