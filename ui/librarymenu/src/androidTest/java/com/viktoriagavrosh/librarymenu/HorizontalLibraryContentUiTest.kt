package com.viktoriagavrosh.librarymenu

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.unit.dp
import com.viktoriagavrosh.librarymenu.elements.HorizontalLibraryContent
import com.viktoriagavrosh.librarymenu.utils.onNodeWithTextById
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class HorizontalLibraryContentUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun libraryContent_horizontalScreen_talesButtonIsDisplayed() {
        setHorizontalContent()
        composeTestRule.onNodeWithTextById(R.string.tale_button)
            .assertExists("No tales button")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun libraryContent_horizontalScreen_riddlesButtonIsDisplayed() {
        setHorizontalContent()
        composeTestRule.onNodeWithTextById(R.string.riddle_button)
            .assertExists("No riddles button")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun libraryContent_horizontalScreen_folksButtonIsDisplayed() {
        setHorizontalContent()
        composeTestRule.onNodeWithTextById(R.string.folk_button)
            .assertExists("No folks button")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun libraryContent_horizontalScreen_randomTaleButtonIsDisplayed() {
        setHorizontalContent()
        composeTestRule.onNodeWithTextById(R.string.random_tale)
            .assertExists("No random tale button")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun libraryContent_horizontalScreen_addTaleButtonIsDisplayed() {
        setHorizontalContent()
        composeTestRule.onNodeWithTextById(R.string.add_tale_button)
            .assertExists("No add tale button")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun libraryContent_horizontalScreen_talesButtonSizeIsRelevant() {
        setHorizontalContent()
        composeTestRule.onNodeWithTextById(R.string.tale_button)
            .assertHeightIsAtLeast(48.dp)
    }

    @Test
    fun libraryContent_horizontalScreen_riddlesButtonSizeIsRelevant() {
        setHorizontalContent()
        composeTestRule.onNodeWithTextById(R.string.riddle_button)
            .assertHeightIsAtLeast(48.dp)
    }

    @Test
    fun libraryContent_horizontalScreen_folksButtonSizeIsRelevant() {
        setHorizontalContent()
        composeTestRule.onNodeWithTextById(R.string.folk_button)
            .assertHeightIsAtLeast(48.dp)
    }

    @Test
    fun libraryContent_horizontalScreen_randomTaleButtonSizeIsRelevant() {
        setHorizontalContent()
        composeTestRule.onNodeWithTextById(R.string.random_tale)
            .assertHeightIsAtLeast(48.dp)
    }

    @Test
    fun libraryContent_horizontalScreen_addTaleButtonSizeIsRelevant() {
        setHorizontalContent()
        composeTestRule.onNodeWithTextById(R.string.add_tale_button)
            .assertHeightIsAtLeast(48.dp)
    }

    private fun setHorizontalContent() {
        composeTestRule.setContent {
            FairyTalesTheme {
                HorizontalLibraryContent(
                    onTaleClick = {},
                    onRiddleClick = {},
                    onFolkClick = {},
                    onAddTaleClick = {},
                    onRandomClick = {},
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
