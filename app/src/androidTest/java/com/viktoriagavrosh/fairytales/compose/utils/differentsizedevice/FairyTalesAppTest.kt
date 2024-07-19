package com.viktoriagavrosh.fairytales.compose.utils.differentsizedevice

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.compose.utils.utils.onNodeWithTagForStringId
import com.viktoriagavrosh.navigation.FairyTalesApp
import org.junit.Rule
import org.junit.Test
/*
// TODO Эти тесты пока не работают (не настраивала)
class FairyTalesAppTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    @TestCompactWidth
    fun compactDevice_verifyUsingBottomNavigation() {
        composeTestRule.setContent {
            FairyTalesApp(windowSize = WindowWidthSizeClass.Compact)
        }
        composeTestRule.onNodeWithTag("compact screen")
            .assertExists()
    }

    @Test
    @TestMediumWidth
    fun mediumDevice_verifyUsingNavigationRail() {
        composeTestRule.setContent {
            FairyTalesApp(windowSize = WindowWidthSizeClass.Medium)
        }
        composeTestRule.onNodeWithTagForStringId(R.string.medium_screen_test_tag)
            .assertExists()
    }

    @Test
    @TestExpandedWidth
    fun expandedDevice_verifyUsingNavigationDrawer() {
        composeTestRule.setContent {
            FairyTalesApp(windowSize = WindowWidthSizeClass.Expanded)
        }
        composeTestRule.onNodeWithTagForStringId(R.string.expanded_screen_test_tag)
    }


}
*/
