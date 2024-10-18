package com.viktoriagavrosh.riddle

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import com.viktoriagavrosh.riddle.elements.VerticalRiddleContent
import com.viktoriagavrosh.riddle.model.ReadRiddle
import com.viktoriagavrosh.riddle.utils.onNodeWithTextById
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class VerticalRiddleContentUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun riddleContent_verticalScreen_textIsDisplayed() {
        val riddle = ReadRiddle(text = "Riddle text")
        setVerticalContent(riddle = riddle)
        composeTestRule.onNodeWithText(riddle.text)
            .assertExists("No text")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun riddleContent_verticalScreen_answerButtonIsDisplayed() {
        setVerticalContent(riddle = ReadRiddle())
        composeTestRule.onNodeWithTextById(R.string.answer_button)
            .assertExists("No answer button")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun riddleContent_verticalScreen_answerButtonSizeIsRelevant() {
        setVerticalContent(riddle = ReadRiddle())
        composeTestRule.onNodeWithTextById(R.string.answer_button)
            .assertHeightIsAtLeast(48.dp)
    }

    @Test
    fun riddleContent_verticalScreen_imageIsDisplayed() {
        val riddle = ReadRiddle(title = "Riddle title")
        setVerticalContent(riddle = riddle)
        composeTestRule.onNodeWithTextById(R.string.answer_button)
            .performClick()
        composeTestRule.onNodeWithContentDescription(riddle.answer)
            .assertExists("No image")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun riddleContent_verticalScreen_answerIsDisplayed() {
        val riddle = ReadRiddle(answer = "Riddle answer")
        setVerticalContent(riddle = riddle)
        composeTestRule.onNodeWithTextById(R.string.answer_button)
            .performClick()
        composeTestRule.onNodeWithText(riddle.answer)
            .assertExists("No answer")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    private fun setVerticalContent(riddle: ReadRiddle) {
        composeTestRule.setContent {
            FairyTalesTheme {
                VerticalRiddleContent(
                    riddleProvider = { riddle },
                    textSizeProvider = { 24.0F },
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
