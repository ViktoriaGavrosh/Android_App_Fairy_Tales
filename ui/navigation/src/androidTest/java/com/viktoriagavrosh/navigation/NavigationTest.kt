package com.viktoriagavrosh.navigation

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test


class NavigationTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun navigation_clickCard_navigateHomeScreenToDetailScreen() {
        val selectedFolkWorkId = "4"
        composeTestRule.setContent {
            FairyTalesTheme {
                FairyTalesApp(windowSize = WindowWidthSizeClass.Compact)
            }
        }
        composeTestRule.onNodeWithTag(selectedFolkWorkId).performClick()
        composeTestRule.onNodeWithText("Vertical detail screen")
            .assertExists("No navigation from HomeScreen to DetailScreen")
    }

    @Test
    fun navigation_clickCard_navigateDetailScreenToHomeScreen() {
        val selectedFolkWorkId = "4"
        composeTestRule.setContent {
            FairyTalesTheme {
                FairyTalesApp(windowSize = WindowWidthSizeClass.Compact)
            }
        }
        composeTestRule.onNodeWithTag(selectedFolkWorkId).performClick()
        composeTestRule.onNodeWithContentDescription("back").performClick()
        composeTestRule.onNodeWithText("Казк\u0456")
            .assertExists("No navigation from DetailScreen to HomeScreen")
    }
}
