package com.viktoriagavrosh.fairytales.compose.navigation

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.compose.differentsizedevice.TestCompactWidth
import com.viktoriagavrosh.fairytales.compose.utils.onNodeWithContentDescriptionForStringId
import com.viktoriagavrosh.fairytales.compose.utils.onNodeWithTagForStringId
import com.viktoriagavrosh.fairytales.ui.FairyTalesApp
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
        composeTestRule.onNodeWithTagForStringId(R.string.vertical_detail_screen)
            .assertExists("No navigation from HomeScreen to DetailScreen")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.back).performClick()
        composeTestRule.onNodeWithText("Казк\u0456")
            .assertExists("No navigation from DetailScreen to HomeScreen")
    }

    @Test
    @TestCompactWidth
    fun navigation_clickHeartOnTopBar_navigateToFavoriteList() {
        val selectedFolkWorkId = "4"
        composeTestRule.setContent {
            FairyTalesTheme {
                FairyTalesApp(windowSize = WindowWidthSizeClass.Compact)
            }
        }
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.all_folk_works)
            .performClick()
        composeTestRule.onNodeWithTag(selectedFolkWorkId).assertIsNotDisplayed()
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.favorite_folk_works)
            .performClick()
        composeTestRule.onNodeWithTag(selectedFolkWorkId).assertExists("No card")
    }
}