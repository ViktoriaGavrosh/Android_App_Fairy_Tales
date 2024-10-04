package com.viktoriagavrosh.uikit

import androidx.activity.ComponentActivity
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.viktoriagavrosh.librarymenu.utils.onNodeWithContentDescriptionForStringId
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalMaterial3Api::class)
class ScreenTopBarUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun uikit_screenTopBar_titleIsDisplayed() {
        val title = "Title"
        setContent(text = title)
        composeTestRule.onNodeWithText(title)
            .assertExists("No title on topBar")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun uikit_screenTopBar_backButtonIsDisplayed() {
        setContent(isBackIconShow = true)
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.back)
            .assertExists("No back button on topBar")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun uikit_screenTopBar_backButtonDoesNotDisplayed() {
        setContent(isBackIconShow = false)
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.back)
            .assertDoesNotExist()
    }

    @Test
    fun uikit_screenTopBar_settingsButtonIsDisplayed() {
        setContent(isSettingsIconShow = true)
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.settings)
            .assertExists("No settings button on topBar")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun uikit_screenTopBar_settingsButtonDoesNotDisplayed() {
        setContent(isSettingsIconShow = false)
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.settings)
            .assertDoesNotExist()
    }

    @Test
    fun uikit_screenTopBar_infoButtonIsDisplayed() {
        setContent(isInfoShow = true)
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.info)
            .assertExists("No info button on topBar")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun uikit_screenTopBar_infoButtonDoesNotDisplayed() {
        setContent(isInfoShow = false)
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.info)
            .assertDoesNotExist()
    }

    @Test
    fun uikit_screenTopBar_dropdownButtonIsDisplayed() {
        setContent(isInfoShow = true, isSettingsIconShow = true)
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.actions)
            .assertExists("No dropdown button on topBar")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun uikit_screenTopBar_dropdownButtonDoesNotDisplayed() {
        setContent(isInfoShow = false, isSettingsIconShow = false)
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.actions)
            .assertDoesNotExist()
    }

    @Test
    fun uikit_screenTopBar_settingsButtonOnDropdownMenuIsDisplayed() {
        setContent(isSettingsIconShow = true, isInfoShow = true)
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.actions)
            .performClick()
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.settings)
            .assertExists("No settings button on dropdownMenu")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun uikit_screenTopBar_infoButtonOnDropdownMenuIsDisplayed() {
        setContent(isSettingsIconShow = true, isInfoShow = true)
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.actions)
            .performClick()
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.info)
            .assertExists("No info button on dropdownMenu")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    private fun setContent(
        text: String = "",
        isSettingsIconShow: Boolean = false,
        isBackIconShow: Boolean = false,
        isInfoShow: Boolean = false,
    ) {
        composeTestRule.setContent {
            FairyTalesTheme {
                ScreenTopBar(
                    text = text,
                    scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
                    isSettingsIconShow = isSettingsIconShow,
                    isBackIconShow = isBackIconShow,
                    isInfoShow = isInfoShow,
                )
            }
        }
    }
}
