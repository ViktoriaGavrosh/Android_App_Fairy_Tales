package com.viktoriagavrosh.home

import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme
import com.viktoriagavrosh.home.elements.TaleType
import org.junit.Rule
import org.junit.Test

class HomeScreenUiTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun homeScreen_storyTypeVerticalScreen_verifyContent() {
        val fakeUiState = TalesListUiState().copy(taleType = TaleType.Story)
        setContentScreen(
            fakeUiState = fakeUiState,
            isExpandedScreen = false
        )
        composeTestRule.onNodeWithText("Казк\u0456").assertExists("No story title")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.all_folk_works)
            .assertExists("No heart on top bar")
        composeTestRule.onNodeWithText("Story").assertExists("No card")
    }

    @Test
    fun homeScreen_puzzleTypeVerticalScreen_verifyContent() {
        val fakeUiState = TalesListUiState().copy(taleType = TaleType.Puzzle)
        setContentScreen(
            fakeUiState = fakeUiState,
            isExpandedScreen = false
        )
        composeTestRule.onNodeWithText("Загадк\u0456").assertExists("No puzzle title")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.all_folk_works)
            .assertExists("No heart on top bar")
        composeTestRule.onNodeWithText("Story").assertExists("No card")
    }


    @Test
    fun homeScreen_gameTypeVerticalScreen_verifyContent() {
        val fakeUiState = TalesListUiState().copy(taleType = TaleType.Game)
        setContentScreen(
            fakeUiState = fakeUiState,
            isExpandedScreen = false
        )
        composeTestRule.onNodeWithText("Заба\u045Eлянк\u0456").assertExists("No game title")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.all_folk_works)
            .assertExists("No heart on top bar")
        composeTestRule.onNodeWithText("Story").assertExists("No card")
    }

    @Test
    fun homeScreen_poemTypeVerticalScreen_verifyContent() {
        val fakeUiState = TalesListUiState().copy(taleType = TaleType.Poem)
        setContentScreen(
            fakeUiState = fakeUiState,
            isExpandedScreen = false
        )
        composeTestRule.onNodeWithText("Л\u0456чылк\u0456").assertExists("No poem title")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.all_folk_works)
            .assertExists("No heart on top bar")
        composeTestRule.onNodeWithText("Story").assertExists("No card")
    }

    @Test
    fun homeScreen_lullabyTypeVerticalScreen_verifyContent() {
        val fakeUiState = TalesListUiState().copy(taleType = TaleType.Lullaby)
        setContentScreen(
            fakeUiState = fakeUiState,
            isExpandedScreen = false
        )
        composeTestRule.onNodeWithText("Калыханк\u0456").assertExists("No lullaby title")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.all_folk_works)
            .assertExists("No heart on top bar")
        composeTestRule.onNodeWithText("Story").assertExists("No card")
    }

    @Test
    fun homeScreen_storyTypeHorizontalScreen_verifyContent() {
        val fakeUiState = TalesListUiState().copy(taleType = TaleType.Story)
        setContentScreen(
            fakeUiState = fakeUiState,
            isExpandedScreen = true
        )
        composeTestRule.onNodeWithText("Казк\u0456").assertExists("No story title")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.all_folk_works)
            .assertExists("No heart on top bar")
        composeTestRule.onNodeWithText("Story").assertExists("No card")
    }

    @Test
    fun homeScreen_puzzleTypeHorizontalScreen_verifyContent() {
        val fakeUiState = TalesListUiState().copy(taleType = TaleType.Puzzle)
        setContentScreen(
            fakeUiState = fakeUiState,
            isExpandedScreen = true
        )
        composeTestRule.onNodeWithText("Загадк\u0456").assertExists("No puzzle title")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.all_folk_works)
            .assertExists("No heart on top bar")
        composeTestRule.onNodeWithText("Story").assertExists("No card")
    }

    @Test
    fun homeScreen_gameTypeHorizontalScreen_verifyContent() {
        val fakeUiState = TalesListUiState().copy(taleType = TaleType.Game)
        setContentScreen(
            fakeUiState = fakeUiState,
            isExpandedScreen = true
        )
        composeTestRule.onNodeWithText("Заба\u045Eлянк\u0456").assertExists("No game title")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.all_folk_works)
            .assertExists("No heart on top bar")
        composeTestRule.onNodeWithText("Story").assertExists("No card")
    }

    @Test
    fun homeScreen_poemTypeHorizontalScreen_verifyContent() {
        val fakeUiState = TalesListUiState().copy(taleType = TaleType.Poem)
        setContentScreen(
            fakeUiState = fakeUiState,
            isExpandedScreen = true
        )
        composeTestRule.onNodeWithText("Л\u0456чылк\u0456").assertExists("No poem title")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.all_folk_works)
            .assertExists("No heart on top bar")
        composeTestRule.onNodeWithText("Story").assertExists("No card")
    }

    @Test
    fun homeScreen_lullabyTypeHorizontalScreen_verifyContent() {
        val fakeUiState = TalesListUiState().copy(taleType = TaleType.Lullaby)
        setContentScreen(
            fakeUiState = fakeUiState,
            isExpandedScreen = true
        )
        composeTestRule.onNodeWithText("Калыханк\u0456").assertExists("No lullaby title")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.all_folk_works)
            .assertExists("No heart on top bar")
        composeTestRule.onNodeWithText("Story").assertExists("No card")
    }

    @Test
    fun homeScreen_verticalScreen_verifyDarkHeart() {
        val fakeUiState = TalesListUiState()
            .copy(taleType = TaleType.Story, isFavoriteTalesList = true)
        setContentScreen(
            fakeUiState = fakeUiState,
            isExpandedScreen = false
        )
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.favorite_folk_works)
            .assertExists("No dark heart on top bar")
    }

    private fun setContentScreen(fakeUiState: TalesListUiState, isExpandedScreen: Boolean) {
        composeTestRule.setContent {
            FairyTalesTheme {
                ContentScreen(
                    tales = fakeUiState.tales,
                    topBarTextId = fakeUiState.taleType.textId,
                    isFavoriteTalesList = fakeUiState.isFavoriteTalesList,
                    isExpandedScreen = isExpandedScreen,
                    onHeartClick = {},
                    onTopBarHeartClick = {},
                    onCardClick = {}
                )
            }
        }
    }
}

fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.onNodeWithContentDescriptionForStringId(
    @StringRes id: Int
): SemanticsNodeInteraction =
    onNodeWithContentDescription(activity.getString(id))
