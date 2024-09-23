package com.viktoriagavrosh.ui.ui
/*
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.viktoriagavrosh.details.VerticalDetailScreen
import com.viktoriagavrosh.details.model.TaleUiDetail
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class VerticalDetailScreenUiTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun verticalDetailScreen_verticalScreen_verifyContent() {
        val fakeTale = TaleUiDetail(
            genre = "story",
            title = "title",
            text = "text",
        )
        setVerticalDetailScreen(fakeTale)
        composeTestRule.onNodeWithContentDescription(fakeTale.title)
            .assertExists("No image")
        composeTestRule.onNodeWithText(fakeTale.text)
            .assertExists("No text")
    }

    @Test
    fun verticalDetailScreen_puzzleTypeVerticalScreen_verifyContent() {
        val fakeTale = TaleUiDetail(
            genre = "puzzle",
            title = "puzzleTitle",
            text = "puzzleText",
        )
        setVerticalDetailScreen(fakeTale)
        composeTestRule.onNodeWithText(fakeTale.text)
            .assertExists("No text")
        composeTestRule.onNodeWithText("Адгадка")
            .assertExists("No button")
    }

    @Test
    @Throws(Exception::class)
    fun verticalDetailScreen_puzzleType_showAnswer() {
        val fakeTale = TaleUiDetail(
            genre = "puzzle",
            title = "puzzleTitle",
            text = "puzzleText",
            answer = "puzzleAnswer",
        )
        setVerticalDetailScreen(fakeTale)
        composeTestRule.onNodeWithText("Адгадка")
            .assertExists("No button")
            .performClick()
        composeTestRule.onNodeWithText(fakeTale.answer!!)
            .assertExists("No text answer")
        composeTestRule.onNodeWithContentDescription(fakeTale.answer!!)
            .assertExists("No image answer")
    }

    private fun setVerticalDetailScreen(fakeTale: TaleUiDetail) {
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

 */
