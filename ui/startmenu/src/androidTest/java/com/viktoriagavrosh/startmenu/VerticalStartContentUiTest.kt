package com.viktoriagavrosh.startmenu

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.unit.dp
import com.viktoriagavrosh.startmenu.elements.VerticalStartContent
import com.viktoriagavrosh.startmenu.utils.onNodeWithTagById
import com.viktoriagavrosh.startmenu.utils.onNodeWithTextById
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class VerticalStartContentUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun startContent_verticalScreen_coverIsDisplayed() {
        setVerticalStartContent()
        composeTestRule.onNodeWithTagById(R.string.cover_test_tag)
            .assertExists("No cover")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun startContent_verticalScreen_favoriteTalesButtonIsDisplayed() {
        setVerticalStartContent()
        composeTestRule.onNodeWithTextById(R.string.title_favorite)
            .assertExists("No favorite tales button")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun startContent_verticalScreen_nightTalesButtonIsDisplayed() {
        setVerticalStartContent()
        composeTestRule.onNodeWithTextById(R.string.title_night)
            .assertExists("No night tales button")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun startContent_verticalScreen_libraryButtonIsDisplayed() {
        setVerticalStartContent()
        composeTestRule.onNodeWithTextById(R.string.library)
            .assertExists("No library button")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun startContent_verticalScreen_lastTaleButtonIsDisplayed() {
        setVerticalStartContent()
        composeTestRule.onNodeWithTextById(R.string.last_tale)
            .assertExists("No last tale button")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun startContent_verticalScreen_randomTaleButtonIsDisplayed() {
        setVerticalStartContent()
        composeTestRule.onNodeWithTextById(R.string.random_tale)
            .assertExists("No random tale button")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun startContent_verticalScreen_settingsButtonIsDisplayed() {
        setVerticalStartContent()
        composeTestRule.onNodeWithTextById(R.string.settings_title)
            .assertExists("No settings button")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun startContent_verticalScreen_favoriteTalesButtonSizeIsRelevant() {
        setVerticalStartContent()
        composeTestRule.onNodeWithTextById(R.string.title_favorite)
            .assertHeightIsAtLeast(48.dp)
    }

    @Test
    fun startContent_verticalScreen_nightTalesButtonSizeIsRelevant() {
        setVerticalStartContent()
        composeTestRule.onNodeWithTextById(R.string.title_night)
            .assertHeightIsAtLeast(48.dp)
    }

    @Test
    fun startContent_verticalScreen_libraryButtonSizeIsRelevant() {
        setVerticalStartContent()
        composeTestRule.onNodeWithTextById(R.string.library)
            .assertHeightIsAtLeast(48.dp)
    }

    @Test
    fun startContent_verticalScreen_lastTaleButtonSizeIsRelevant() {
        setVerticalStartContent()
        composeTestRule.onNodeWithTextById(R.string.last_tale)
            .assertHeightIsAtLeast(48.dp)
    }

    @Test
    fun startContent_verticalScreen_randomTaleButtonSizeIsRelevant() {
        setVerticalStartContent()
        composeTestRule.onNodeWithTextById(R.string.random_tale)
            .assertHeightIsAtLeast(48.dp)
    }

    @Test
    fun startContent_verticalScreen_settingsButtonSizeIsRelevant() {
        setVerticalStartContent()
        composeTestRule.onNodeWithTextById(R.string.settings_title)
            .assertHeightIsAtLeast(48.dp)
    }

    private fun setVerticalStartContent() {
        composeTestRule.setContent {
            FairyTalesTheme {
                VerticalStartContent(
                    onSettingsClick = {},
                    onNightClick = {},
                    onRandomClick = {},
                    onLibraryClick = {},
                    onFavoriteClick = {},
                    onLastTaleClick = {},
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
