package com.viktoriagavrosh.fairytales.ui.detailscreen

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.viktoriagavrosh.fairytales.model.TaleUi
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class VerticalDetailScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun verticalDetailScreen_verifyContent() {
        val fakeTale = TaleUi(
            genre = "story",
            title = "title",
            text = "text",
        )
        setVerticalDetailScreen(fakeTale)
        composeTestRule.onNodeWithContentDescription(fakeTale.title)
            .assertExists("No image")
            .assertIsDisplayed()
            .assertHasNoClickAction()
        composeTestRule.onNodeWithText(fakeTale.text)
            .assertExists("No text")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun verticalDetailScreen_puzzleType_verifyContent() {
        val fakeTale = TaleUi(
            genre = "puzzle",
            title = "puzzleTitle",
            text = "puzzleText",
        )
        setVerticalDetailScreen(fakeTale)
        composeTestRule.onNodeWithText(fakeTale.text)
            .assertExists("No text")
            .assertIsDisplayed()
            .assertHasNoClickAction()
        composeTestRule.onNodeWithText("Адгадка")
            .assertExists("No button")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    @Throws(Exception::class)
    fun verticalDetailScreen_puzzleType_AnswerNotDisplayed() {
        val fakeTale = TaleUi(
            genre = "puzzle",
            title = "puzzleTitle",
            text = "puzzleText",
            answer = "puzzleAnswer",
        )
        setVerticalDetailScreen(fakeTale)
        composeTestRule.onNodeWithText(fakeTale.answer!!)
            .assertIsNotDisplayed()
        composeTestRule.onNodeWithContentDescription(fakeTale.answer!!)
            .assertIsNotDisplayed()
    }

    @Test
    @Throws(Exception::class)
    fun verticalDetailScreen_puzzleType_showAnswer() {
        val fakeTale = TaleUi(
            genre = "puzzle",
            title = "puzzleTitle",
            text = "puzzleText",
            answer = "puzzleAnswer",
        )
        setVerticalDetailScreen(fakeTale)
        composeTestRule.onNodeWithText("Адгадка")
            .performClick()
        composeTestRule.onNodeWithText(fakeTale.answer!!)
            .assertExists("No text answer")
            .assertIsDisplayed()
            .assertHasNoClickAction()
        composeTestRule.onNodeWithContentDescription(fakeTale.answer!!)
            .assertExists("No image answer")
            .assertIsDisplayed()
            .assertHasNoClickAction()

    }

    private fun setVerticalDetailScreen(fakeTale: TaleUi) {
        composeTestRule.setContent {
            FairyTalesTheme {
                VerticalDetailScreen(
                    tale = fakeTale,
                    fontSize = 24.0F,
                )
            }
        }
    }
}
