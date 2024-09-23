package com.viktoriagavrosh.ui.ui
/*
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.viktoriagavrosh.details.HorizontalDetailScreen
import com.viktoriagavrosh.details.model.TaleUiDetail
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class HorizontalDetailScreenUiTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun detailScreen_horizontalScreen_verifyContent() {
        val fakeTale = TaleUiDetail(
            genre = "story",
            title = "title",
            text = "text",
        )
        setHorizontalDetailScreen(fakeTale)
        composeTestRule.onNodeWithContentDescription(fakeTale.title)
            .assertExists("No image")
        composeTestRule.onNodeWithText(fakeTale.text)
            .assertExists("No text")
    }

    @Test
    fun detailScreen_puzzleTypeHorizontalScreen_verifyContent() {
        val fakeTale = TaleUiDetail(
            genre = "puzzle",
            title = "puzzleTitle",
            text = "puzzleText",
        )
        setHorizontalDetailScreen(fakeTale)
        composeTestRule.onNodeWithText(fakeTale.text)
            .assertExists("No text")
        composeTestRule.onNodeWithText("Адгадка")
            .assertExists("No button")
    }

    @Test
    @Throws(Exception::class)
    fun detailScreen_puzzleType_showAnswer() {
        val fakeTale = TaleUiDetail(
            genre = "puzzle",
            title = "puzzleTitle",
            text = "puzzleText",
            answer = "puzzleAnswer",
        )
        setHorizontalDetailScreen(fakeTale)
        composeTestRule.onNodeWithText("Адгадка")
            .assertExists("No button")
            .performClick()
        composeTestRule.onNodeWithText(fakeTale.answer!!)
            .assertExists("No text answer")
        composeTestRule.onNodeWithContentDescription(fakeTale.answer!!)
            .assertExists("No image answer")
    }

    private fun setHorizontalDetailScreen(fakeTale: TaleUiDetail) {
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


 */
