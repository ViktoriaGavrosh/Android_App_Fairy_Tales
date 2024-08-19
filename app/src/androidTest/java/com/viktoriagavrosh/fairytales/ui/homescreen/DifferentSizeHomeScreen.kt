package com.viktoriagavrosh.fairytales.ui.homescreen

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.ui.utils.onNodeWithTagById
import org.junit.Rule
import org.junit.Test

class DifferentSizeHomeScreen {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun homeScreen_compactScreen_bottomNavigationBarIsDisplayed() {
        val fakeUiState = TalesUiState()
        setHomeScreen(fakeUiState, WindowWidthSizeClass.Compact)
        composeTestRule.onNodeWithTagById(R.string.compact_screen_test_tag)
            .assertExists("No bottom navigation bar")
            .assertIsDisplayed()
    }

    @Test
    fun homeScreen_mediumScreen_navigationRailIsDisplayed() {
        val fakeUiState = TalesUiState()
        setHomeScreen(fakeUiState, WindowWidthSizeClass.Medium)
        composeTestRule.onNodeWithTagById(R.string.expanded_screen_test_tag)
            .assertExists("No navigation rail")
            .assertIsDisplayed()
    }

    @Test
    fun homeScreen_expandedScreen_navigationRailIsDisplayed() {
        val fakeUiState = TalesUiState()
        setHomeScreen(fakeUiState, WindowWidthSizeClass.Expanded)
        composeTestRule.onNodeWithTagById(R.string.expanded_screen_test_tag)
            .assertExists("No navigation rail")
            .assertIsDisplayed()
    }

    private fun setHomeScreen(fakeUiState: TalesUiState, windowSize: WindowWidthSizeClass) {
        composeTestRule.setContent {
            HomeScreen(
                uiState = fakeUiState,
                screenState = HomeScreenState.Error(),
                windowSize = windowSize,
                onCardClick = {},
                onTabClick = {},
                onTopBarHeartClick = {},
                onHeartClick = {},
                onSettingsClick = {}
            )
        }
    }
}
