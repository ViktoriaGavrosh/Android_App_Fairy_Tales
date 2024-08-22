package com.viktoriagavrosh.fairytales.ui.homescreen

import androidx.activity.ComponentActivity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.model.TaleUi
import com.viktoriagavrosh.fairytales.ui.ScreenState
import com.viktoriagavrosh.fairytales.ui.elements.Genre
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme
import com.viktoriagavrosh.fairytales.ui.utils.onNodeWithContentDescriptionById
import com.viktoriagavrosh.fairytales.ui.utils.onNodeWithTextById
import org.junit.Rule
import org.junit.Test

class ContentHomeScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val fakeListTales = listOf(TaleUi(genre = Genre.Story, title = "Story"))

    @Test
    fun contentHomeScreen_verticalScreen_settingsButtonIsDisplayed() {
        val fakeUiState = TalesUiState()
        setContentHomeScreen(fakeUiState, isExpandedScreen = false)
        composeTestRule.onNodeWithContentDescriptionById(R.string.settings)
            .assertExists("No settings icon on top bar")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun contentHomeScreen_horizontalScreen_settingsButtonIsDisplayed() {
        val fakeUiState = TalesUiState()
        setContentHomeScreen(fakeUiState, isExpandedScreen = true)
        composeTestRule.onNodeWithContentDescriptionById(R.string.settings)
            .assertExists("No settings icon on top bar")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun contentHomeScreen_storyTypeVerticalScreen_titleIsDisplayed() {
        val fakeUiState = TalesUiState()
        setContentHomeScreen(fakeUiState, isExpandedScreen = false)
        composeTestRule.onNodeWithTextById(R.string.title_fairy_tales)
            .assertExists("No story title")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun contentHomeScreen_puzzleTypeVerticalScreen_titleIsDisplayed() {
        val fakeUiState = TalesUiState(genre = Genre.Puzzle)
        setContentHomeScreen(fakeUiState, isExpandedScreen = false)
        composeTestRule.onNodeWithTextById(R.string.title_puzzle)
            .assertExists("No puzzle title")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun contentHomeScreen_gameTypeVerticalScreen_titleIsDisplayed() {
        val fakeUiState = TalesUiState(genre = Genre.Game)
        setContentHomeScreen(fakeUiState, isExpandedScreen = false)
        composeTestRule.onNodeWithTextById(R.string.title_game)
            .assertExists("No game title")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun contentHomeScreen_poemTypeVerticalScreen_titleIsDisplayed() {
        val fakeUiState = TalesUiState(genre = Genre.Poem)
        setContentHomeScreen(fakeUiState, isExpandedScreen = false)
        composeTestRule.onNodeWithTextById(R.string.title_poem)
            .assertExists("No poem title")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun contentHomeScreen_lullabyTypeVerticalScreen_titleIsDisplayed() {
        val fakeUiState = TalesUiState(genre = Genre.Lullaby)
        setContentHomeScreen(fakeUiState, isExpandedScreen = false)
        composeTestRule.onNodeWithTextById(R.string.lullaby)
            .assertExists("No lullaby title")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun contentHomeScreen_storyTypeHorizontalScreen_titleIsDisplayed() {
        val fakeUiState = TalesUiState()
        setContentHomeScreen(fakeUiState, isExpandedScreen = true)
        composeTestRule.onNodeWithTextById(R.string.title_fairy_tales)
            .assertExists("No story title")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun contentHomeScreen_puzzleTypeHorizontalScreen_titleIsDisplayed() {
        val fakeUiState = TalesUiState(genre = Genre.Puzzle)
        setContentHomeScreen(fakeUiState, isExpandedScreen = true)
        composeTestRule.onNodeWithTextById(R.string.title_puzzle)
            .assertExists("No puzzle title")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun contentHomeScreen_gameTypeHorizontalScreen_titleIsDisplayed() {
        val fakeUiState = TalesUiState(genre = Genre.Game)
        setContentHomeScreen(fakeUiState, isExpandedScreen = true)
        composeTestRule.onNodeWithTextById(R.string.title_game)
            .assertExists("No game title")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun contentHomeScreen_poemTypeHorizontalScreen_titleIsDisplayed() {
        val fakeUiState = TalesUiState(genre = Genre.Poem)
        setContentHomeScreen(fakeUiState, isExpandedScreen = true)
        composeTestRule.onNodeWithTextById(R.string.title_poem)
            .assertExists("No poem title")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun contentHomeScreen_lullabyTypeHorizontalScreen_titleIsDisplayed() {
        val fakeUiState = TalesUiState(genre = Genre.Lullaby)
        setContentHomeScreen(fakeUiState, isExpandedScreen = true)
        composeTestRule.onNodeWithTextById(R.string.lullaby)
            .assertExists("No lullaby title")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun contentHomeScreen_verticalScreen_cardIsDisplayed() {
        val fakeUiState = TalesUiState()
        setContentHomeScreen(fakeUiState, isExpandedScreen = false)
        composeTestRule.onNodeWithText("Story")
            .assertExists("No card")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun contentHomeScreen_horizontalScreen_cardIsDisplayed() {
        val fakeUiState = TalesUiState()
        setContentHomeScreen(fakeUiState, isExpandedScreen = true)
        composeTestRule.onNodeWithText("Story")
            .assertExists("No card")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun contentHomeScreen_verticalScreen_lightHeartIsDisplayedOnTopBar() {
        val fakeUiState = TalesUiState()
        setContentHomeScreen(fakeUiState, isExpandedScreen = false)
        composeTestRule.onNodeWithContentDescriptionById(R.string.all_tales)
            .assertExists("No dark heart on top bar")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun contentHomeScreen_horizontalScreen_lightHeartIsDisplayedOnTopBar() {
        val fakeUiState = TalesUiState()
        setContentHomeScreen(fakeUiState, isExpandedScreen = true)
        composeTestRule.onNodeWithContentDescriptionById(R.string.all_tales)
            .assertExists("No dark heart on top bar")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun contentHomeScreen_verticalScreen_darkHeartIsDisplayedOnTopBar() {
        val fakeUiState = TalesUiState(isFavoriteTalesShown = true)
        setContentHomeScreen(fakeUiState, isExpandedScreen = false)
        composeTestRule.onNodeWithContentDescriptionById(R.string.favorite_tales)
            .assertExists("No dark heart on top bar")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun contentHomeScreen_horizontalScreen_darkHeartIsDisplayedOnTopBar() {
        val fakeUiState = TalesUiState(isFavoriteTalesShown = true)
        setContentHomeScreen(fakeUiState, isExpandedScreen = true)
        composeTestRule.onNodeWithContentDescriptionById(R.string.favorite_tales)
            .assertExists("No dark heart on top bar")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    private fun setContentHomeScreen(fakeUiState: TalesUiState, isExpandedScreen: Boolean) {
        composeTestRule.setContent {
            FairyTalesTheme {
                ContentHomeScreen(
                    screenState = ScreenState.Success(fakeListTales),
                    topBarText = stringResource(id = fakeUiState.genre.textId),
                    isFavoriteTalesShown = fakeUiState.isFavoriteTalesShown,
                    isCompactScreen = !isExpandedScreen,
                    onHeartClick = {},
                    onTopBarHeartClick = {},
                    onCardClick = {},
                    onTabClick = {},
                    onSettingsClick = {}
                )
            }
        }
    }
}
