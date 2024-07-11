package com.viktoriagavrosh.ui.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.viktoriagavrosh.fairytales.ui.screens.HomeScreen
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme
import com.viktoriagavrosh.fairytales.ui.utils.UILogic
import com.viktoriagavrosh.ui.R
import com.viktoriagavrosh.ui.uiscreens.FairyTalesUiState
import com.viktoriagavrosh.ui.uiscreens.FolkWorkType
import com.viktoriagavrosh.ui.utils.onNodeWithContentDescriptionForStringId
import org.junit.Rule
import org.junit.Test

class HomeScreenUiTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun homeScreen_storyTypeVerticalScreen_verifyContent() {
        val fakeUiState = FairyTalesUiState().copy(folkWorkType = FolkWorkType.Story)
        composeTestRule.setContent {
            FairyTalesTheme {
                HomeScreen(logic = UILogic(), uiState = fakeUiState, isExpandedScreen = false)
            }
        }
        composeTestRule.onNodeWithText("Казк\u0456").assertExists("No story title")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.all_folk_works)
            .assertExists("No heart on top bar")
        composeTestRule.onNodeWithText("Story").assertExists("No card")
    }

    @Test
    fun homeScreen_puzzleTypeVerticalScreen_verifyContent() {
        val fakeUiState = FairyTalesUiState().copy(folkWorkType = FolkWorkType.Puzzle)
        composeTestRule.setContent {
            FairyTalesTheme {
                HomeScreen(logic = UILogic(), uiState = fakeUiState, isExpandedScreen = false)
            }
        }
        composeTestRule.onNodeWithText("Загадк\u0456").assertExists("No puzzle title")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.all_folk_works)
            .assertExists("No heart on top bar")
        composeTestRule.onNodeWithText("Story").assertExists("No card")
    }

    @Test
    fun homeScreen_gameTypeVerticalScreen_verifyContent() {
        val fakeUiState = FairyTalesUiState().copy(folkWorkType = FolkWorkType.Game)
        composeTestRule.setContent {
            FairyTalesTheme {
                HomeScreen(logic = UILogic(), uiState = fakeUiState, isExpandedScreen = false)
            }
        }
        composeTestRule.onNodeWithText("Заба\u045Eлянк\u0456").assertExists("No game title")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.all_folk_works)
            .assertExists("No heart on top bar")
        composeTestRule.onNodeWithText("Story").assertExists("No card")
    }

    @Test
    fun homeScreen_poemTypeVerticalScreen_verifyContent() {
        val fakeUiState = FairyTalesUiState().copy(folkWorkType = FolkWorkType.Poem)
        composeTestRule.setContent {
            FairyTalesTheme {
                HomeScreen(logic = UILogic(), uiState = fakeUiState, isExpandedScreen = false)
            }
        }
        composeTestRule.onNodeWithText("Л\u0456чылк\u0456").assertExists("No poem title")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.all_folk_works)
            .assertExists("No heart on top bar")
        composeTestRule.onNodeWithText("Story").assertExists("No card")
    }

    @Test
    fun homeScreen_lullabyTypeVerticalScreen_verifyContent() {
        val fakeUiState = FairyTalesUiState().copy(folkWorkType = FolkWorkType.Lullaby)
        composeTestRule.setContent {
            FairyTalesTheme {
                HomeScreen(logic = UILogic(), uiState = fakeUiState, isExpandedScreen = false)
            }
        }
        composeTestRule.onNodeWithText("Калыханк\u0456").assertExists("No lullaby title")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.all_folk_works)
            .assertExists("No heart on top bar")
        composeTestRule.onNodeWithText("Story").assertExists("No card")
    }

    @Test
    fun homeScreen_storyTypeHorizontalScreen_verifyContent() {
        val fakeUiState = FairyTalesUiState().copy(folkWorkType = FolkWorkType.Story)
        composeTestRule.setContent {
            FairyTalesTheme {
                HomeScreen(logic = UILogic(), uiState = fakeUiState, isExpandedScreen = true)
            }
        }
        composeTestRule.onNodeWithText("Казк\u0456").assertExists("No story title")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.all_folk_works)
            .assertExists("No heart on top bar")
        composeTestRule.onNodeWithText("Story").assertExists("No card")
    }

    @Test
    fun homeScreen_puzzleTypeHorizontalScreen_verifyContent() {
        val fakeUiState = FairyTalesUiState().copy(folkWorkType = FolkWorkType.Puzzle)
        composeTestRule.setContent {
            FairyTalesTheme {
                HomeScreen(logic = UILogic(), uiState = fakeUiState, isExpandedScreen = true)
            }
        }
        composeTestRule.onNodeWithText("Загадк\u0456").assertExists("No puzzle title")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.all_folk_works)
            .assertExists("No heart on top bar")
        composeTestRule.onNodeWithText("Story").assertExists("No card")
    }

    @Test
    fun homeScreen_gameTypeHorizontalScreen_verifyContent() {
        val fakeUiState = FairyTalesUiState().copy(folkWorkType = FolkWorkType.Game)
        composeTestRule.setContent {
            FairyTalesTheme {
                HomeScreen(logic = UILogic(), uiState = fakeUiState, isExpandedScreen = true)
            }
        }
        composeTestRule.onNodeWithText("Заба\u045Eлянк\u0456").assertExists("No game title")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.all_folk_works)
            .assertExists("No heart on top bar")
        composeTestRule.onNodeWithText("Story").assertExists("No card")
    }

    @Test
    fun homeScreen_poemTypeHorizontalScreen_verifyContent() {
        val fakeUiState = FairyTalesUiState().copy(folkWorkType = FolkWorkType.Poem)
        composeTestRule.setContent {
            FairyTalesTheme {
                HomeScreen(logic = UILogic(), uiState = fakeUiState, isExpandedScreen = true)
            }
        }
        composeTestRule.onNodeWithText("Л\u0456чылк\u0456").assertExists("No poem title")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.all_folk_works)
            .assertExists("No heart on top bar")
        composeTestRule.onNodeWithText("Story").assertExists("No card")
    }

    @Test
    fun homeScreen_lullabyTypeHorizontalScreen_verifyContent() {
        val fakeUiState = FairyTalesUiState().copy(folkWorkType = FolkWorkType.Lullaby)
        composeTestRule.setContent {
            FairyTalesTheme {
                HomeScreen(logic = UILogic(), uiState = fakeUiState, isExpandedScreen = true)
            }
        }
        composeTestRule.onNodeWithText("Калыханк\u0456").assertExists("No lullaby title")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.all_folk_works)
            .assertExists("No heart on top bar")
        composeTestRule.onNodeWithText("Story").assertExists("No card")
    }

    @Test
    fun homeScreen_verticalScreen_verifyDarkHeart() {
        val fakeUiState = FairyTalesUiState()
            .copy(folkWorkType = FolkWorkType.Story, isFavoriteWorks = true)
        composeTestRule.setContent {
            FairyTalesTheme {
                HomeScreen(logic = UILogic(), uiState = fakeUiState, isExpandedScreen = false)
            }
        }
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.favorite_folk_works)
            .assertExists("No dark heart on top bar")
    }
}
