package com.viktoriagavrosh.addtale

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.viktoriagavrosh.addtale.elements.ContentAddTaleScreen
import com.viktoriagavrosh.addtale.model.NewTale
import com.viktoriagavrosh.addtale.utils.onNodeWithContentDescriptionForStringId
import com.viktoriagavrosh.addtale.utils.onNodeWithTextById
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class ContentAddTaleScreenUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun addTaleScreen_verticalScreen_backButtonOnTopBarIsDisplayed() {
        setContentScreen(isVerticalScreen = true)
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.back)
            .assertExists("No back button on topBar")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun addTaleScreen_verticalScreen_titleOnTopBarIsDisplayed() {
        setContentScreen(isVerticalScreen = true)
        composeTestRule.onNodeWithTextById(R.string.add_tale)
            .assertExists("No title on topBar")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun addTaleScreen_horizontalScreen_backButtonOnTopBarIsDisplayed() {
        setContentScreen(isVerticalScreen = false)
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.back)
            .assertExists("No back button on topBar")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun addTaleScreen_horizontalScreen_titleOnTopBarIsDisplayed() {
        setContentScreen(isVerticalScreen = false)
        composeTestRule.onNodeWithTextById(R.string.add_tale)
            .assertExists("No title on topBar")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    private fun setContentScreen(isVerticalScreen: Boolean) {
        composeTestRule.setContent {
            FairyTalesTheme {
                ContentAddTaleScreen(
                    taleProvider = { NewTale() },
                    isTaleValidProvider = { false },
                    isVerticalScreen = isVerticalScreen,
                    onTitleValueChange = {},
                    onTextValueChange = {},
                    onGenreValueChange = {},
                    onIsNightValueChange = {},
                    onBackClick = {},
                    onAddButtonClick = {},
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
