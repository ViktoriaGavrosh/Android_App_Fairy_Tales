package com.viktoriagavrosh.uikit

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.viktoriagavrosh.librarymenu.utils.onNodeWithContentDescriptionForStringId
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class HeartIconUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun uikit_heartIcon_lightHeartIsDisplayed() {
        setContent(false)
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.not_favorite)
            .assertExists("No light heart icon")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun uikit_heartIcon_darkHeartIsDisplayed() {
        setContent(true)
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.favorite)
            .assertExists("No dark heart icon")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    private fun setContent(isFavorite: Boolean) {
        composeTestRule.setContent {
            FairyTalesTheme {
                HeartIcon(
                    isFavorite = isFavorite
                )
            }
        }
    }
}
