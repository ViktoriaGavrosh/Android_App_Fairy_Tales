package com.viktoriagavrosh.ui.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.viktoriagavrosh.details.HorizontalDetailScreen
import com.viktoriagavrosh.details.model.FolkWorkUiDetails
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class HorizontalDetailScreenUiTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun detailScreen_horizontalScreen_verifyContent() {
        val fakeFolkWorkUiDetail = FolkWorkUiDetails()
            .copy(
                genre = "story",
                title = "title",
                text = "text",
            )
        composeTestRule.setContent {
            FairyTalesTheme {
                HorizontalDetailScreen(
                    folkWork = fakeFolkWorkUiDetail,
                )
            }
        }
        composeTestRule.onNodeWithContentDescription(fakeFolkWorkUiDetail.title)
            .assertExists("No image")
        composeTestRule.onNodeWithText(fakeFolkWorkUiDetail.text)
            .assertExists("No text")
    }

    @Test
    fun detailScreen_puzzleTypeHorizontalScreen_verifyContent() {
        val fakeFolkWorkUiDetail = FolkWorkUiDetails()
            .copy(
                genre = "puzzle",
                title = "puzzleTitle",
                text = "puzzleText",
            )
        composeTestRule.setContent {
            FairyTalesTheme {
                HorizontalDetailScreen(
                    folkWork = fakeFolkWorkUiDetail,
                )
            }
        }
        composeTestRule.onNodeWithText(fakeFolkWorkUiDetail.text)
            .assertExists("No text")
        composeTestRule.onNodeWithText("Адгадка")
            .assertExists("No button")
    }

    @Test
    @Throws(Exception::class)
    fun detailScreen_puzzleType_showAnswer() {
        val fakeFolkWorkUiDetail = FolkWorkUiDetails()
            .copy(
                genre = "puzzle",
                title = "puzzleTitle",
                text = "puzzleText",
                answer = "puzzleAnswer",
            )
        composeTestRule.setContent {
            FairyTalesTheme {
                HorizontalDetailScreen(
                    folkWork = fakeFolkWorkUiDetail,
                )
            }
        }
        composeTestRule.onNodeWithText("Адгадка")
            .assertExists("No button")
            .performClick()
        composeTestRule.onNodeWithText(fakeFolkWorkUiDetail.answer!!)
            .assertExists("No text answer")
        composeTestRule.onNodeWithContentDescription(fakeFolkWorkUiDetail.answer!!)
            .assertExists("No image answer")
    }
}
