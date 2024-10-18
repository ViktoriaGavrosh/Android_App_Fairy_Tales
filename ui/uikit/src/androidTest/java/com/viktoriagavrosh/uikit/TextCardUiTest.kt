package com.viktoriagavrosh.uikit

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.viktoriagavrosh.librarymenu.utils.onNodeWithTagById
import com.viktoriagavrosh.uikit.text.TextCard
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class TextCardUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun uikit_textCard_textIsDisplayed() {
        val text = "Text"
        setContent(text)
        composeTestRule.onNodeWithText(text)
            .assertExists("No text")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun uikit_textCard_titleIsDisplayed() {
        val title = "Title"
        setContent(isTitleShow = true, title = title)
        composeTestRule.onNodeWithText(title)
            .assertExists("No title")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun uikit_textCard_titleDoesNotDisplayed() {
        val title = "Title"
        setContent(isTitleShow = false, title = title)
        composeTestRule.onNodeWithText(title)
            .assertDoesNotExist()
    }

    @Test
    fun uikit_textCard_topDecorDividerIsDisplayed() {
        setContent(isDecorShow = true)
        composeTestRule.onNodeWithTagById(R.string.top_decor_divider_test_tag)
            .assertExists("No top decor divider")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun uikit_textCard_bottomDecorDividerIsDisplayed() {
        setContent(isDecorShow = true)
        composeTestRule.onNodeWithTagById(R.string.bottom_decor_divider_test_tag)
            .assertExists("No bottom decor divider")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun uikit_textCard_topDecorDividerDoesNotDisplayed() {
        setContent(isDecorShow = false)
        composeTestRule.onNodeWithTagById(R.string.top_decor_divider_test_tag)
            .assertDoesNotExist()
    }

    @Test
    fun uikit_textCard_bottomDecorDividerDoesNotDisplayed() {
        setContent(isDecorShow = false)
        composeTestRule.onNodeWithTagById(R.string.bottom_decor_divider_test_tag)
            .assertDoesNotExist()
    }

    private fun setContent(
        text: String = "",
        isTitleShow: Boolean = false,
        isDecorShow: Boolean = false,
        title: String = ""
    ) {
        composeTestRule.setContent {
            FairyTalesTheme {
                TextCard(
                    text = text,
                    textSizeProvider = { 24.0F },
                    isTitleShow = isTitleShow,
                    isDecorShow = isDecorShow,
                    title = title,
                )
            }
        }
    }
}
