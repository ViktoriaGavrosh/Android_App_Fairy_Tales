package com.viktoriagavrosh.librarymenu

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.viktoriagavrosh.librarymenu.elements.ContentLibraryScreen
import com.viktoriagavrosh.librarymenu.utils.onNodeWithContentDescriptionForStringId
import com.viktoriagavrosh.librarymenu.utils.onNodeWithTextById
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class ContentLibraryScreenUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun libraryScreen_verticalScreen_backButtonOnTopBarIsDisplayed() {
        setContentScreen(
            isVerticalScreen = true
        )
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.back)
            .assertExists("No back button on topBar")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun infoScreen_verticalScreen_titleOnTopBarIsDisplayed() {
        setContentScreen(
            isVerticalScreen = true
        )
        composeTestRule.onNodeWithTextById(R.string.library)
            .assertExists("No title on topBar")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun infoScreen_horizontalScreen_backButtonOnTopBarIsDisplayed() {
        setContentScreen(
            isVerticalScreen = false
        )
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.back)
            .assertExists("No back button on topBar")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun infoScreen_horizontalScreen_titleOnTopBarIsDisplayed() {
        setContentScreen(
            isVerticalScreen = false
        )
        composeTestRule.onNodeWithTextById(R.string.library)
            .assertExists("No title on topBar")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    private fun setContentScreen(isVerticalScreen: Boolean) {
        composeTestRule.setContent {
            FairyTalesTheme {
                ContentLibraryScreen(
                    isVerticalScreen = isVerticalScreen,
                    onTaleClick = {},
                    onRiddleClick = {},
                    onFolkClick = {},
                    onAddTaleClick = {},
                    onRandomClick = {},
                    onBackClick = {},
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
