package com.viktoriagavrosh.fairytales.compose.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.compose.utils.onNodeWithContentDescriptionForStringId
import com.viktoriagavrosh.fairytales.data.TaleType
import com.viktoriagavrosh.fairytales.ui.TalesUiState
import com.viktoriagavrosh.fairytales.ui.screens.HomeScreen
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme
import com.viktoriagavrosh.fairytales.ui.utils.UILogic
import org.junit.Rule
import org.junit.Test

class HomeScreenUiTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun homeScreen_storyTypeVerticalScreen_verifyContent() {
        val fakeUiState = TalesUiState().copy(taleType = TaleType.Story)
        composeTestRule.setContent {
            FairyTalesTheme {
                HomeScreen(logic = UILogic(), uiState = fakeUiState, isExpandedScreen = false)
            }
        }
        composeTestRule.onNodeWithText("Казк\u0456").assertExists("No story title")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.all_tales)
            .assertExists("No heart on top bar")
        composeTestRule.onNodeWithText("Story").assertExists("No card")
    }

    @Test
    fun homeScreen_puzzleTypeVerticalScreen_verifyContent() {
        val fakeUiState = TalesUiState().copy(taleType = TaleType.Puzzle)
        composeTestRule.setContent {
            FairyTalesTheme {
                HomeScreen(logic = UILogic(), uiState = fakeUiState, isExpandedScreen = false)
            }
        }
        composeTestRule.onNodeWithText("Загадк\u0456").assertExists("No puzzle title")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.all_tales)
            .assertExists("No heart on top bar")
        composeTestRule.onNodeWithText("Story").assertExists("No card")
    }

    @Test
    fun homeScreen_gameTypeVerticalScreen_verifyContent() {
        val fakeUiState = TalesUiState().copy(taleType = TaleType.Game)
        composeTestRule.setContent {
            FairyTalesTheme {
                HomeScreen(logic = UILogic(), uiState = fakeUiState, isExpandedScreen = false)
            }
        }
        composeTestRule.onNodeWithText("Заба\u045Eлянк\u0456").assertExists("No game title")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.all_tales)
            .assertExists("No heart on top bar")
        composeTestRule.onNodeWithText("Story").assertExists("No card")
    }

    @Test
    fun homeScreen_poemTypeVerticalScreen_verifyContent() {
        val fakeUiState = TalesUiState().copy(taleType = TaleType.Poem)
        composeTestRule.setContent {
            FairyTalesTheme {
                HomeScreen(logic = UILogic(), uiState = fakeUiState, isExpandedScreen = false)
            }
        }
        composeTestRule.onNodeWithText("Л\u0456чылк\u0456").assertExists("No poem title")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.all_tales)
            .assertExists("No heart on top bar")
        composeTestRule.onNodeWithText("Story").assertExists("No card")
    }

    @Test
    fun homeScreen_lullabyTypeVerticalScreen_verifyContent() {
        val fakeUiState = TalesUiState().copy(taleType = TaleType.Lullaby)
        composeTestRule.setContent {
            FairyTalesTheme {
                HomeScreen(logic = UILogic(), uiState = fakeUiState, isExpandedScreen = false)
            }
        }
        composeTestRule.onNodeWithText("Калыханк\u0456").assertExists("No lullaby title")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.all_tales)
            .assertExists("No heart on top bar")
        composeTestRule.onNodeWithText("Story").assertExists("No card")
    }

    @Test
    fun homeScreen_storyTypeHorizontalScreen_verifyContent() {
        val fakeUiState = TalesUiState().copy(taleType = TaleType.Story)
        composeTestRule.setContent {
            FairyTalesTheme {
                HomeScreen(logic = UILogic(), uiState = fakeUiState, isExpandedScreen = true)
            }
        }
        composeTestRule.onNodeWithText("Казк\u0456").assertExists("No story title")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.all_tales)
            .assertExists("No heart on top bar")
        composeTestRule.onNodeWithText("Story").assertExists("No card")
    }

    @Test
    fun homeScreen_puzzleTypeHorizontalScreen_verifyContent() {
        val fakeUiState = TalesUiState().copy(taleType = TaleType.Puzzle)
        composeTestRule.setContent {
            FairyTalesTheme {
                HomeScreen(logic = UILogic(), uiState = fakeUiState, isExpandedScreen = true)
            }
        }
        composeTestRule.onNodeWithText("Загадк\u0456").assertExists("No puzzle title")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.all_tales)
            .assertExists("No heart on top bar")
        composeTestRule.onNodeWithText("Story").assertExists("No card")
    }

    @Test
    fun homeScreen_gameTypeHorizontalScreen_verifyContent() {
        val fakeUiState = TalesUiState().copy(taleType = TaleType.Game)
        composeTestRule.setContent {
            FairyTalesTheme {
                HomeScreen(logic = UILogic(), uiState = fakeUiState, isExpandedScreen = true)
            }
        }
        composeTestRule.onNodeWithText("Заба\u045Eлянк\u0456").assertExists("No game title")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.all_tales)
            .assertExists("No heart on top bar")
        composeTestRule.onNodeWithText("Story").assertExists("No card")
    }

    @Test
    fun homeScreen_poemTypeHorizontalScreen_verifyContent() {
        val fakeUiState = TalesUiState().copy(taleType = TaleType.Poem)
        composeTestRule.setContent {
            FairyTalesTheme {
                HomeScreen(logic = UILogic(), uiState = fakeUiState, isExpandedScreen = true)
            }
        }
        composeTestRule.onNodeWithText("Л\u0456чылк\u0456").assertExists("No poem title")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.all_tales)
            .assertExists("No heart on top bar")
        composeTestRule.onNodeWithText("Story").assertExists("No card")
    }

    @Test
    fun homeScreen_lullabyTypeHorizontalScreen_verifyContent() {
        val fakeUiState = TalesUiState().copy(taleType = TaleType.Lullaby)
        composeTestRule.setContent {
            FairyTalesTheme {
                HomeScreen(logic = UILogic(), uiState = fakeUiState, isExpandedScreen = true)
            }
        }
        composeTestRule.onNodeWithText("Калыханк\u0456").assertExists("No lullaby title")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.all_tales)
            .assertExists("No heart on top bar")
        composeTestRule.onNodeWithText("Story").assertExists("No card")
    }

    @Test
    fun homeScreen_verticalScreen_verifyDarkHeart() {
        val fakeUiState = TalesUiState()
            .copy(taleType = TaleType.Story, isFavoriteTales = true)
        composeTestRule.setContent {
            FairyTalesTheme {
                HomeScreen(logic = UILogic(), uiState = fakeUiState, isExpandedScreen = false)
            }
        }
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.favorite_tales)
            .assertExists("No dark heart on top bar")
    }
}
