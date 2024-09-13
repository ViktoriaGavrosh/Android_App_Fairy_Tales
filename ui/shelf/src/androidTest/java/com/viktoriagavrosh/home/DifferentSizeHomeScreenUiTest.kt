package com.viktoriagavrosh.home

import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.viktoriagavrosh.shelf.HomeScreen
import com.viktoriagavrosh.shelf.HomeScreenState
import com.viktoriagavrosh.shelf.TalesListUiState
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class DifferentSizeHomeScreenUiTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun homeScreen_compactScreen_verifyContent() {
        val fakeUiState = TalesListUiState()
        setHomeScreen(
            fakeUiState = fakeUiState,
            windowSize = WindowWidthSizeClass.Compact
        )
        composeTestRule.onNodeWithTagForStringId(R.string.compact_screen_test_tag)
            .assertExists("No bottom navigation bar")
    }

    @Test
    fun homeScreen_mediumScreen_verifyContent() {
        val fakeUiState = TalesListUiState()
        setHomeScreen(
            fakeUiState = fakeUiState,
            windowSize = WindowWidthSizeClass.Medium
        )
        composeTestRule.onNodeWithTagForStringId(R.string.expanded_screen_test_tag)
            .assertExists("No navigation rail")
    }

    @Test
    fun homeScreen_expandedScreen_verifyContent() {
        val fakeUiState = TalesListUiState()
        setHomeScreen(
            fakeUiState = fakeUiState,
            windowSize = WindowWidthSizeClass.Expanded
        )
        composeTestRule.onNodeWithTagForStringId(R.string.expanded_screen_test_tag)
            .assertExists("No navigation rail")
    }

    private fun setHomeScreen(fakeUiState: TalesListUiState, windowSize: WindowWidthSizeClass) {
        composeTestRule.setContent {
            FairyTalesTheme {
                HomeScreen(
                    uiState = fakeUiState,
                    screenState = HomeScreenState.Error(),
                    windowSize = windowSize,
                    onCardClick = {},
                    onTabClick = {},
                    onTopBarHeartClick = {},
                    onHeartClick = {},
                    onSettingsClick = {},
                )
            }
        }
    }
}

internal fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.onNodeWithTagForStringId(
    @StringRes id: Int
): SemanticsNodeInteraction =
    onNodeWithTag(activity.getString(id))
