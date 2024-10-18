package com.viktoriagavrosh.startmenu

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeUp
import androidx.compose.ui.unit.dp
import com.viktoriagavrosh.startmenu.elements.HorizontalStartContent
import com.viktoriagavrosh.startmenu.utils.onNodeWithTagById
import com.viktoriagavrosh.startmenu.utils.onNodeWithTextById
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class HorizontalStartContentUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun startContent_horizontalScreen_coverIsDisplayed() {
        setHorizontalStartContent()
        composeTestRule.onNodeWithTagById(R.string.cover_test_tag)
            .assertExists("No cover")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun startContent_horizontalScreen_favoriteTalesButtonIsDisplayed() {
        setHorizontalStartContent()
        composeTestRule.onNodeWithTextById(R.string.title_favorite)
            .assertExists("No favorite tales button")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun startContent_horizontalScreen_nightTalesButtonIsDisplayed() {
        setHorizontalStartContent()
        composeTestRule.onNodeWithTagById(R.string.horizontal_start_content_test_tag)
            .performTouchInput { swipeUp() }
        composeTestRule.onNodeWithTextById(R.string.title_night)
            .assertExists("No night tales button")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun startContent_horizontalScreen_libraryButtonIsDisplayed() {
        setHorizontalStartContent()
        composeTestRule.onNodeWithTagById(R.string.horizontal_start_content_test_tag)
            .performTouchInput { swipeUp() }
        composeTestRule.onNodeWithTextById(R.string.library)
            .assertExists("No library button")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun startContent_horizontalScreen_lastTaleButtonIsDisplayed() {
        setHorizontalStartContent()
        composeTestRule.onNodeWithTextById(R.string.last_tale)
            .assertExists("No last tale button")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun startContent_horizontalScreen_randomTaleButtonIsDisplayed() {
        setHorizontalStartContent()
        composeTestRule.onNodeWithTagById(R.string.horizontal_start_content_test_tag)
            .performTouchInput { swipeUp() }
        composeTestRule.onNodeWithTextById(R.string.random_tale)
            .assertExists("No random tale button")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun startContent_horizontalScreen_settingsButtonIsDisplayed() {
        setHorizontalStartContent()
        composeTestRule.onNodeWithTagById(R.string.horizontal_start_content_test_tag)
            .performTouchInput { swipeUp() }
        composeTestRule.onNodeWithTextById(R.string.settings_title)
            .assertExists("No settings button")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun startContent_horizontalScreen_favoriteTalesButtonSizeIsRelevant() {
        setHorizontalStartContent()
        composeTestRule.onNodeWithTextById(R.string.title_favorite)
            .assertHeightIsAtLeast(48.dp)
    }

    @Test
    fun startContent_horizontalScreen_nightTalesButtonSizeIsRelevant() {
        setHorizontalStartContent()
        composeTestRule.onNodeWithTagById(R.string.horizontal_start_content_test_tag)
            .performTouchInput { swipeUp() }
        composeTestRule.onNodeWithTextById(R.string.title_night)
            .assertHeightIsAtLeast(48.dp)
    }

    @Test
    fun startContent_horizontalScreen_libraryButtonSizeIsRelevant() {
        setHorizontalStartContent()
        composeTestRule.onNodeWithTagById(R.string.horizontal_start_content_test_tag)
            .performTouchInput { swipeUp() }
        composeTestRule.onNodeWithTextById(R.string.library)
            .assertHeightIsAtLeast(48.dp)
    }

    @Test
    fun startContent_horizontalScreen_lastTaleButtonSizeIsRelevant() {
        setHorizontalStartContent()
        composeTestRule.onNodeWithTextById(R.string.last_tale)
            .assertHeightIsAtLeast(48.dp)
    }

    @Test
    fun startContent_horizontalScreen_randomTaleButtonSizeIsRelevant() {
        setHorizontalStartContent()
        composeTestRule.onNodeWithTagById(R.string.horizontal_start_content_test_tag)
            .performTouchInput { swipeUp() }
        composeTestRule.onNodeWithTextById(R.string.random_tale)
            .assertHeightIsAtLeast(48.dp)
    }

    @Test
    fun startContent_horizontalScreen_settingsButtonSizeIsRelevant() {
        setHorizontalStartContent()
        composeTestRule.onNodeWithTagById(R.string.horizontal_start_content_test_tag)
            .performTouchInput { swipeUp() }
        composeTestRule.onNodeWithTextById(R.string.settings_title)
            .assertHeightIsAtLeast(48.dp)
    }

    private fun setHorizontalStartContent() {
        composeTestRule.setContent {
            FairyTalesTheme {
                HorizontalStartContent(
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
