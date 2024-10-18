package com.viktoriagavrosh.uikit

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.viktoriagavrosh.librarymenu.utils.onNodeWithTagById
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class CoverUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun uikit_cover_textIsDisplayed() {
        val text = "Text"
        setContent(text)
        composeTestRule.onNodeWithText(text)
            .assertExists("No text on cover")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun uikit_cover_topFlowersRowIsDisplayed() {
        setContent()
        composeTestRule.onNodeWithTagById(R.string.top_flowersrow_test_tag)
            .assertExists("No top flowersRow on cover")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun uikit_cover_bottomFlowersRowIsDisplayed() {
        setContent()
        composeTestRule.onNodeWithTagById(R.string.bottom_flowersrow_test_tag)
            .assertExists("No bottom flowersRow on cover")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    private fun setContent(text: String = "") {
        composeTestRule.setContent {
            FairyTalesTheme {
                Cover(
                    text = text
                )
            }
        }
    }
}
