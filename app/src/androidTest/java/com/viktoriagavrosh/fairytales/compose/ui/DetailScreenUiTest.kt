package com.viktoriagavrosh.fairytales.compose.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.compose.utils.onNodeWithContentDescriptionForStringId
import com.viktoriagavrosh.fairytales.data.FolkWorkType
import com.viktoriagavrosh.ui.uiscreens.FairyTalesUiState
import com.viktoriagavrosh.fairytales.ui.screens.detailscreens.DetailScreen
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme
import com.viktoriagavrosh.fairytales.ui.utils.UILogic
import org.junit.Rule
import org.junit.Test

class DetailScreenUiTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun detailScreen_verticalScreen_verifyContent() {
        val fakeUiState = FairyTalesUiState()
            .copy(folkWorkType = FolkWorkType.Story, isShowHomeScreen = false)
        composeTestRule.setContent {
            FairyTalesTheme {
                DetailScreen(
                    folkWork = fakeUiState.selectedWork,
                    logic = UILogic(),
                    isPuzzleType = false,
                    isStoryType = true,
                    isExpandedScreen = false
                )
            }
        }
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.back)
            .assertExists("No back button on topBar")
        composeTestRule.onNodeWithText(fakeUiState.selectedWork.title)
            .assertExists("No title on topBar")
        composeTestRule.onNodeWithContentDescription(fakeUiState.selectedWork.title)
            .assertExists("No image")
        composeTestRule.onNodeWithText(fakeUiState.selectedWork.text)
            .assertExists("No text")
    }

    @Test
    fun detailScreen_horizontalScreen_verifyContent() {
        val fakeUiState = FairyTalesUiState()
            .copy(folkWorkType = FolkWorkType.Story, isShowHomeScreen = false)
        composeTestRule.setContent {
            FairyTalesTheme {
                DetailScreen(
                    folkWork = fakeUiState.selectedWork,
                    logic = UILogic(),
                    isPuzzleType = false,
                    isStoryType = true,
                    isExpandedScreen = true
                )
            }
        }
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.back)
            .assertExists("No back button on topBar")
        composeTestRule.onNodeWithText(fakeUiState.selectedWork.title)
            .assertExists("No title on topBar")
        composeTestRule.onNodeWithContentDescription(fakeUiState.selectedWork.title)
            .assertExists("No image")
        composeTestRule.onNodeWithText(fakeUiState.selectedWork.text)
            .assertExists("No text")
    }

    @Test
    fun detailScreen_puzzleTypeVerticalScreen_verifyContent() {
        val fakeUiState = FairyTalesUiState()
            .copy(folkWorkType = FolkWorkType.Puzzle, isShowHomeScreen = false)
        composeTestRule.setContent {
            FairyTalesTheme {
                DetailScreen(
                    folkWork = fakeUiState.selectedWork,
                    logic = UILogic(),
                    isPuzzleType = true,
                    isStoryType = false,
                    isExpandedScreen = false
                )
            }
        }
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.back)
            .assertExists("No back button on topBar")
        composeTestRule.onNodeWithText(fakeUiState.selectedWork.title)
            .assertExists("No title on topBar")
        composeTestRule.onNodeWithText(fakeUiState.selectedWork.text)
            .assertExists("No text")
        composeTestRule.onNodeWithText("Адгадка")
            .assertExists("No button")
    }

    @Test
    fun detailScreen_puzzleTypeHorizontalScreen_verifyContent() {
        val fakeUiState = FairyTalesUiState()
            .copy(folkWorkType = FolkWorkType.Puzzle, isShowHomeScreen = false)
        composeTestRule.setContent {
            FairyTalesTheme {
                DetailScreen(
                    folkWork = fakeUiState.selectedWork,
                    logic = UILogic(),
                    isPuzzleType = true,
                    isStoryType = false,
                    isExpandedScreen = true
                )
            }
        }
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.back)
            .assertExists("No back button on topBar")
        composeTestRule.onNodeWithText(fakeUiState.selectedWork.title)
            .assertExists("No title on topBar")
        composeTestRule.onNodeWithText(fakeUiState.selectedWork.text)
            .assertExists("No text")
        composeTestRule.onNodeWithText("Адгадка")
            .assertExists("No button")
    }

    @Test
    @Throws(Exception::class)
    fun detailScreen_puzzleType_showAnswer() {
        val fakeUiState = FairyTalesUiState()
            .copy(folkWorkType = FolkWorkType.Puzzle, isShowHomeScreen = false)
        composeTestRule.setContent {
            FairyTalesTheme {
                DetailScreen(
                    folkWork = fakeUiState.selectedWork,
                    logic = UILogic(),
                    isPuzzleType = true,
                    isStoryType = false,
                    isExpandedScreen = false
                )
            }
        }
        composeTestRule.onNodeWithText("Адгадка")
            .assertExists("No button")
            .performClick()
        composeTestRule.onNodeWithText(fakeUiState.selectedWork.answer!!)
            .assertExists("No text answer")
        composeTestRule.onNodeWithContentDescription(fakeUiState.selectedWork.answer!!)
            .assertExists("No image answer")
    }

}
