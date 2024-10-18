package com.viktoriagavrosh.uikit

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.viktoriagavrosh.librarymenu.utils.onNodeWithTagById
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class SwitchRowUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun uikit_switchRow_textIsDisplayed() {
        val text = "Switch text"
        setContent(text = text)
        composeTestRule.onNodeWithText(text)
            .assertExists("No text")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun uikit_switchRow_switchIsDisplayed() {
        setContent()
        composeTestRule.onNodeWithTagById(R.string.switch_test_tag)
            .assertExists("No switch")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun uikit_switchRow_switchIsOff() {
        setContent(isChecked = false)
        composeTestRule.onNodeWithTagById(R.string.switch_test_tag)
            .assertIsOff()
    }

    @Test
    fun uikit_switchRow_switchIsOn() {
        setContent(isChecked = true)
        composeTestRule.onNodeWithTagById(R.string.switch_test_tag)
            .assertIsOn()
    }


    private fun setContent(text: String = "", isChecked: Boolean = false) {
        composeTestRule.setContent {
            FairyTalesTheme {
                SwitchRow(
                    text = text,
                    isChecked = isChecked,
                    onValueChange = {},
                )
            }
        }
    }
}
