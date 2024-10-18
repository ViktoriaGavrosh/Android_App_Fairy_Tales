package com.viktoriagavrosh.uikit

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class SelectionDropdownMenuUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val options = listOf("first", "second", "third")

    @Test
    fun uikit_selectedDropdownMenu_selectOptionIsDisplayed() {
        val option = options[0]
        setContent(option)
        composeTestRule.onNodeWithText(option)
            .assertExists("No select option")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun uikit_selectedDropdownMenu_secondOptionIsDisplayed() {
        val option = options[0]
        setContent(option)
        composeTestRule.onNodeWithText(option)
            .performClick()
        composeTestRule.onNodeWithText(options[1])
            .assertExists("No second option")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun uikit_selectedDropdownMenu_thirdOptionIsDisplayed() {
        val option = options[0]
        setContent(option)
        composeTestRule.onNodeWithText(option)
            .performClick()
        composeTestRule.onNodeWithText(options[2])
            .assertExists("No third option")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun uikit_selectedDropdownMenu_menuClose() {
        val option = options[0]
        setContent(option)
        composeTestRule.onNodeWithText(option)
            .performClick()
        composeTestRule.onNodeWithText(options[2])
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(options[1])
            .performClick()
        composeTestRule.onNodeWithText(options[2])
            .assertIsNotDisplayed()
    }

    private fun setContent(selectedOptions: String) {
        composeTestRule.setContent {
            FairyTalesTheme {
                SelectionDropdownMenu(
                    options = options,
                    selectedOption = selectedOptions,
                    onValueChange = {},
                )
            }
        }
    }
}
