package com.viktoriagavrosh.home

import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.viktoriagavrosh.shelf.elements.Genre
import com.viktoriagavrosh.shelf.model.Book
import com.viktoriagavrosh.shelf.ContentScreen
import com.viktoriagavrosh.shelf.HomeScreenState
import com.viktoriagavrosh.shelf.TalesListUiState
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class ContentScreenUiTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val fakeList: List<Book> = listOf(Book(title = "Story"))

    @Test
    fun contentScreen_storyTypeVerticalScreen_verifyContent() {
        val fakeUiState = TalesListUiState().copy(genre = Genre.Story)
        setContentScreen(
            fakeUiState = fakeUiState,
            isExpandedScreen = false
        )
        composeTestRule.onNodeWithText("Казк\u0456").assertExists("No story title")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.all_tales)
            .assertExists("No heart on top bar")
        composeTestRule.onNodeWithText("Story").assertExists("No card")
    }

    @Test
    fun contentScreen_puzzleTypeVerticalScreen_verifyContent() {
        val fakeUiState = TalesListUiState().copy(genre = Genre.Puzzle)
        setContentScreen(
            fakeUiState = fakeUiState,
            isExpandedScreen = false
        )
        composeTestRule.onNodeWithText("Загадк\u0456").assertExists("No puzzle title")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.all_tales)
            .assertExists("No heart on top bar")
        composeTestRule.onNodeWithText("Story").assertExists("No card")
    }


    @Test
    fun contentScreen_gameTypeVerticalScreen_verifyContent() {
        val fakeUiState = TalesListUiState().copy(genre = Genre.Game)
        setContentScreen(
            fakeUiState = fakeUiState,
            isExpandedScreen = false
        )
        composeTestRule.onNodeWithText("Заба\u045Eлянк\u0456").assertExists("No game title")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.all_tales)
            .assertExists("No heart on top bar")
        composeTestRule.onNodeWithText("Story").assertExists("No card")
    }

    @Test
    fun contentScreen_poemTypeVerticalScreen_verifyContent() {
        val fakeUiState = TalesListUiState().copy(genre = Genre.Poem)
        setContentScreen(
            fakeUiState = fakeUiState,
            isExpandedScreen = false
        )
        composeTestRule.onNodeWithText("Л\u0456чылк\u0456").assertExists("No poem title")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.all_tales)
            .assertExists("No heart on top bar")
        composeTestRule.onNodeWithText("Story").assertExists("No card")
    }

    @Test
    fun contentScreen_lullabyTypeVerticalScreen_verifyContent() {
        val fakeUiState = TalesListUiState().copy(genre = Genre.Lullaby)
        setContentScreen(
            fakeUiState = fakeUiState,
            isExpandedScreen = false
        )
        composeTestRule.onNodeWithText("Калыханк\u0456").assertExists("No lullaby title")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.all_tales)
            .assertExists("No heart on top bar")
        composeTestRule.onNodeWithText("Story").assertExists("No card")
    }

    @Test
    fun contentScreen_storyTypeHorizontalScreen_verifyContent() {
        val fakeUiState = TalesListUiState().copy(genre = Genre.Story)
        setContentScreen(
            fakeUiState = fakeUiState,
            isExpandedScreen = true
        )
        composeTestRule.onNodeWithText("Казк\u0456").assertExists("No story title")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.all_tales)
            .assertExists("No heart on top bar")
        composeTestRule.onNodeWithText("Story").assertExists("No card")
    }

    @Test
    fun contentScreen_puzzleTypeHorizontalScreen_verifyContent() {
        val fakeUiState = TalesListUiState().copy(genre = Genre.Puzzle)
        setContentScreen(
            fakeUiState = fakeUiState,
            isExpandedScreen = true
        )
        composeTestRule.onNodeWithText("Загадк\u0456").assertExists("No puzzle title")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.all_tales)
            .assertExists("No heart on top bar")
        composeTestRule.onNodeWithText("Story").assertExists("No card")
    }

    @Test
    fun contentScreen_gameTypeHorizontalScreen_verifyContent() {
        val fakeUiState = TalesListUiState().copy(genre = Genre.Game)
        setContentScreen(
            fakeUiState = fakeUiState,
            isExpandedScreen = true
        )
        composeTestRule.onNodeWithText("Заба\u045Eлянк\u0456").assertExists("No game title")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.all_tales)
            .assertExists("No heart on top bar")
        composeTestRule.onNodeWithText("Story").assertExists("No card")
    }

    @Test
    fun contentScreen_poemTypeHorizontalScreen_verifyContent() {
        val fakeUiState = TalesListUiState().copy(genre = Genre.Poem)
        setContentScreen(
            fakeUiState = fakeUiState,
            isExpandedScreen = true
        )
        composeTestRule.onNodeWithText("Л\u0456чылк\u0456").assertExists("No poem title")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.all_tales)
            .assertExists("No heart on top bar")
        composeTestRule.onNodeWithText("Story").assertExists("No card")
    }

    @Test
    fun contentScreen_lullabyTypeHorizontalScreen_verifyContent() {
        val fakeUiState = TalesListUiState().copy(genre = Genre.Lullaby)
        setContentScreen(
            fakeUiState = fakeUiState,
            isExpandedScreen = true
        )
        composeTestRule.onNodeWithText("Калыханк\u0456").assertExists("No lullaby title")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.all_tales)
            .assertExists("No heart on top bar")
        composeTestRule.onNodeWithText("Story").assertExists("No card")
    }

    @Test
    fun contentScreen_verticalScreen_verifyDarkHeart() {
        val fakeUiState = TalesListUiState(genre = Genre.Story, isFavoriteTalesShown = true)
        setContentScreen(
            fakeUiState = fakeUiState,
            isExpandedScreen = false
        )
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.favorite_tales)
            .assertExists("No dark heart on top bar")
    }

    @Test
    fun contentScreen_verticalErrorScreen_verifyContent() {
        val fakeUiState = TalesListUiState()
        composeTestRule.setContent {
            FairyTalesTheme {
                ContentScreen(
                    screenState = HomeScreenState.Error(),
                    topBarTextId = fakeUiState.genre.textId,
                    isFavoriteTalesList = fakeUiState.isFavoriteTalesShown,
                    isCompactScreen = true,
                    onHeartClick = {},
                    onTopBarHeartClick = {},
                    onCardClick = {},
                    onTabClick = {},
                    onSettingsClick = {},
                )
            }
        }
        composeTestRule.onNodeWithTextById(R.string.error_text)
            .assertExists("No error text")
        composeTestRule.onNodeWithTextById(R.string.error_button_text)
            .assertExists("No error button")
    }

    @Test
    fun contentScreen_horizontalErrorScreen_verifyContent() {
        val fakeUiState = TalesListUiState()
        composeTestRule.setContent {
            FairyTalesTheme {
                ContentScreen(
                    screenState = HomeScreenState.Error(),
                    topBarTextId = fakeUiState.genre.textId,
                    isFavoriteTalesList = fakeUiState.isFavoriteTalesShown,
                    isCompactScreen = false,
                    onHeartClick = {},
                    onTopBarHeartClick = {},
                    onCardClick = {},
                    onTabClick = {},
                    onSettingsClick = {},
                )
            }
        }
        composeTestRule.onNodeWithTextById(R.string.error_text)
            .assertExists("No error text")
        composeTestRule.onNodeWithTextById(R.string.error_button_text)
            .assertExists("No error button")
    }

    private fun setContentScreen(fakeUiState: TalesListUiState, isExpandedScreen: Boolean) {
        composeTestRule.setContent {
            FairyTalesTheme {
                ContentScreen(
                    screenState = HomeScreenState.Success(fakeList),
                    topBarTextId = fakeUiState.genre.textId,
                    isFavoriteTalesList = fakeUiState.isFavoriteTalesShown,
                    isCompactScreen = !isExpandedScreen,
                    onHeartClick = {},
                    onTopBarHeartClick = {},
                    onTabClick = {},
                    onCardClick = {},
                    onSettingsClick = {},
                )
            }
        }
    }
}

internal fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.onNodeWithContentDescriptionForStringId(
    @StringRes id: Int
): SemanticsNodeInteraction =
    onNodeWithContentDescription(activity.getString(id))

internal fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.onNodeWithTextById(
    @StringRes id: Int
): SemanticsNodeInteraction =
    onNodeWithText(activity.getString(id))
