package com.viktoriagavrosh.settings

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeLeft
import androidx.compose.ui.test.swipeRight
import androidx.compose.ui.unit.dp
import com.viktoriagavrosh.settings.elements.VerticalSettingsContent
import com.viktoriagavrosh.settings.utils.onNodeWithTagById
import com.viktoriagavrosh.settings.utils.onNodeWithTextById
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class VerticalSettingsContentUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun settingsScreen_verticalScreen_textSizeTitleIsDisplayed() {
        setVerticalContent(textSize = 35.0F)
        composeTestRule.onNodeWithTextById(R.string.textsize_title)
            .assertExists("No text size title")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun settingsScreen_verticalScreen_lettersIsDisplayed() {
        setVerticalContent(textSize = 35.0F)
        composeTestRule.onNodeWithTextById(R.string.letters)
            .assertExists("No letters")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun settingsScreen_verticalScreen_textSizeSliderIsDisplayed() {
        setVerticalContent(textSize = 35.0F)
        composeTestRule.onNodeWithTagById(R.string.textsize_slider)
            .assertExists("No textSize slider")
            .assertIsDisplayed()
            .assertIsEnabled()
    }

    @Test
    fun settingsScreen_moveRightTextSizeSlider_increasedLetters() {
        setVerticalContent(textSize = 35.0F)
        composeTestRule.onNodeWithTagById(R.string.textsize_slider)
            .performTouchInput { this.swipeRight() }
        composeTestRule.onNodeWithTextById(R.string.letters)
            .assertHeightIsAtLeast(50.dp)
    }

    @Test
    fun settingsScreen_moveLeftTextSizeSlider_decreasedLetters() {
        setVerticalContent(textSize = 50.0F)
        composeTestRule.onNodeWithTagById(R.string.textsize_slider)
            .performTouchInput { this.swipeLeft() }

        // needs to get the opposite of assertHeightIsAtLeast
        try {
            composeTestRule.onNodeWithTextById(R.string.letters)
                .assertHeightIsAtLeast(50.dp)
        } catch (e: AssertionError) {
            return
        }
        throw AssertionError("swipe left on textSize slider does not work")
    }

    @Test
    fun settingsScreen_largeTextSize_verifyLargeLetters() {
        setVerticalContent(textSize = 60.0F)
        composeTestRule.onNodeWithTextById(R.string.letters)
            .assertHeightIsAtLeast(30.dp)
    }

    @Test
    fun settingsScreen_smallTextSize_verifySmallLetters() {
        setVerticalContent(textSize = 10.0F)
        try {
            composeTestRule.onNodeWithTextById(R.string.letters)
                .assertHeightIsAtLeast(30.dp)
        } catch (e: AssertionError) {
            return
        }
        throw AssertionError("letters not small when enter small textSize")
    }

    private fun setVerticalContent(textSize: Float) {
        composeTestRule.setContent {
            FairyTalesTheme {
                VerticalSettingsContent(
                    textSizeProvider = { textSize },
                    onTextSizeUpdate = {},
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
