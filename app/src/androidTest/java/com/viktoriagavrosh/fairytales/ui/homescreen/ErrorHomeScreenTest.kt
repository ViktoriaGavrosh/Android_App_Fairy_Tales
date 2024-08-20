package com.viktoriagavrosh.fairytales.ui.homescreen

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme
import com.viktoriagavrosh.fairytales.ui.utils.onNodeWithTextById
import org.junit.Rule
import org.junit.Test

class ErrorHomeScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun contentHomeScreen_verticalErrorScreen_errorTextIsDisplayed() {
        val fakeUiState = TalesUiState()
        setContentHomeScreen(fakeUiState, isExpandedScreen = false)
        composeTestRule.onNodeWithTextById(R.string.error_text)
            .assertExists("No error text")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun contentHomeScreen_horizontalErrorScreen_errorTextIsDisplayed() {
        val fakeUiState = TalesUiState()
        setContentHomeScreen(fakeUiState, isExpandedScreen = true)
        composeTestRule.onNodeWithTextById(R.string.error_text)
            .assertExists("No error text")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun contentHomeScreen_verticalErrorScreen_errorButtonIsDisplayed() {
        val fakeUiState = TalesUiState()
        setContentHomeScreen(fakeUiState, isExpandedScreen = false)
        composeTestRule.onNodeWithTextById(R.string.error_button_text)
            .assertExists("No error text")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun contentHomeScreen_horizontalErrorScreen_errorButtonIsDisplayed() {
        val fakeUiState = TalesUiState()
        setContentHomeScreen(fakeUiState, isExpandedScreen = true)
        composeTestRule.onNodeWithTextById(R.string.error_button_text)
            .assertExists("No error text")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    private fun setContentHomeScreen(fakeUiState: TalesUiState, isExpandedScreen: Boolean) {
        composeTestRule.setContent {
            FairyTalesTheme {
                ContentHomeScreen(
                    screenState = HomeScreenState.Error(),
                    topBarTextId = fakeUiState.genre.textId,
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
