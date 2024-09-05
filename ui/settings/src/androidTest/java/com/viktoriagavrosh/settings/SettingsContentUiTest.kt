package com.viktoriagavrosh.settings

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
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class SettingsContentUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun settingsScreen_verticalScreen_verifyTextSizeTitle() {
        setContentScreen()

        composeTestRule.onNodeWithTextById(R.string.textsize_title)
            .assertExists("No text size title")
            .assertIsDisplayed()
            .assertHasNoClickAction()

    }

    @Test
    fun settingsScreen_verticalScreen_verifyLetters() {
        setContentScreen()

        composeTestRule.onNodeWithTextById(R.string.letters)
            .assertExists("No letters")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun settingsScreen_verticalScreen_verifyTextSizeSlider() {
        setContentScreen()

        composeTestRule.onNodeWithTagForStringId(R.string.textsize_slider)
            .assertExists("No textSize slider")
            .assertIsDisplayed()
            .assertIsEnabled()
    }

    @Test
    fun settingsScreen_moveRightTextSizeSlider_increasedLetters() {
        setContentScreen()

        composeTestRule.onNodeWithTagForStringId(R.string.textsize_slider)
            .performTouchInput { swipeRight() }
        composeTestRule.onNodeWithTextById(R.string.letters)
            .assertHeightIsAtLeast(50.dp)
    }

    @Test
    fun settingsScreen_moveLeftTextSizeSlider_decreasedLetters() {
        setContentScreen(textSize = 90.0F)

        composeTestRule.onNodeWithTagForStringId(R.string.textsize_slider)
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
    fun settingsScreen_largeTextSize_verifyLargeLetters() {
        setContentScreen(textSize = 100.0F)

        composeTestRule.onNodeWithTextById(R.string.letters)
            .assertHeightIsAtLeast(50.dp)
    }

    @Test
    fun settingsScreen_smallTextSize_verifySmallLetters() {
        setContentScreen(textSize = 10.0F)

        try {
            composeTestRule.onNodeWithTextById(R.string.letters)
                .assertHeightIsAtLeast(50.dp)
        } catch (e: AssertionError) {
            return
        }
        throw AssertionError("letters not small when enter small textSize")
    }

    private fun setContentScreen(textSize: Float = 24.0F) {
        composeTestRule.setContent {
            FairyTalesTheme {
                SettingsContent(
                    textSize = textSize,
                    onTextSizeUpdate = {}
                )
            }
        }
    }
}
