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
import com.viktoriagavrosh.fairytales.ui.elements.Genre
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class HorizontalDetailScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun horizontalDetailScreen_verifyContent() {
        val fakeTale = TaleUi(
            genre = Genre.Story,
            title = "title",
            text = "text",
        )
        setHorizontalDetailScreen(fakeTale)
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
    fun horizontalDetailScreen_puzzleType_verifyContent() {
        val fakeTale = TaleUi(
            genre = Genre.Puzzle,
            title = "puzzleTitle",
            text = "puzzleText",
        )
        setHorizontalDetailScreen(fakeTale)
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
    fun horizontalDetailScreen_puzzleType_AnswerNotDisplayed() {
        val fakeTale = TaleUi(
            genre = Genre.Puzzle,
            title = "puzzleTitle",
            text = "puzzleText",
            answer = "puzzleAnswer",
        )
        setHorizontalDetailScreen(fakeTale)
        composeTestRule.onNodeWithText(fakeTale.answer!!)
            .assertIsNotDisplayed()
        composeTestRule.onNodeWithContentDescription(fakeTale.answer!!)
            .assertIsNotDisplayed()
    }

    @Test
    @Throws(Exception::class)
    fun horizontalDetailScreen_puzzleType_showAnswer() {
        val fakeTale = TaleUi(
            genre = Genre.Puzzle,
            title = "puzzleTitle",
            text = "puzzleText",
            answer = "puzzleAnswer",
        )
        setHorizontalDetailScreen(fakeTale)
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

    private fun setHorizontalDetailScreen(fakeTale: TaleUi) {
        composeTestRule.setContent {
            FairyTalesTheme {
                HorizontalDetailScreen(
                    tale = fakeTale,
                    fontSize = 24.0F,
                )
            }
        }
    }
}
