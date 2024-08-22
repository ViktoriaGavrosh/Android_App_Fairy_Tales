package com.viktoriagavrosh.fairytales.ui.detailscreen

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.model.TaleUi
import com.viktoriagavrosh.fairytales.ui.ScreenState
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme
import com.viktoriagavrosh.fairytales.ui.utils.onNodeWithContentDescriptionById
import com.viktoriagavrosh.fairytales.ui.utils.onNodeWithTextById
import org.junit.Rule
import org.junit.Test

class DetailScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun detailScreen_verticalScreen_backButtonIsDisplayed() {
        val fakeTale = TaleUi(title = "title")
        setDetailScreen(fakeTale, isExpanded = false)
        composeTestRule.onNodeWithContentDescriptionById(R.string.back)
            .assertExists("No back icon")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun detailScreen_verticalScreen_titleIsDisplayed() {
        val fakeTale = TaleUi(title = "title")
        setDetailScreen(fakeTale, isExpanded = false)
        composeTestRule.onNodeWithText(fakeTale.title)
            .assertExists("No title")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun detailScreen_horizontalScreen_backButtonIsDisplayed() {
        val fakeTale = TaleUi(title = "title")
        setDetailScreen(fakeTale, isExpanded = true)
        composeTestRule.onNodeWithContentDescriptionById(R.string.back)
            .assertExists("No back icon")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun detailScreen_horizontalScreen_titleIsDisplayed() {
        val fakeTale = TaleUi(title = "title")
        setDetailScreen(fakeTale, isExpanded = true)
        composeTestRule.onNodeWithText(fakeTale.title)
            .assertExists("No title")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun detailScreen_verticalScreen_settingsButtonIsDisplayed() {
        val fakeTale = TaleUi(title = "title")
        setDetailScreen(fakeTale, isExpanded = false)
        composeTestRule.onNodeWithContentDescriptionById(R.string.settings)
            .assertExists("No settings icon on top bar")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun detailScreen_horizontalScreen_settingsButtonIsDisplayed() {
        val fakeTale = TaleUi(title = "title")
        setDetailScreen(fakeTale, isExpanded = true)
        composeTestRule.onNodeWithContentDescriptionById(R.string.settings)
            .assertExists("No settings icon on top bar")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun detailScreen_verticalErrorScreen_verifyContent() {
        composeTestRule.setContent {
            FairyTalesTheme {
                DetailScreen(
                    screenState = ScreenState.Error(),
                    textSizeFromDatastore = 24.0F,
                    isExpandedScreen = false,
                    onDetailScreenBackClick = {},
                    onSettingsClick = {},
                    onTextSizeUpdate = {},
                )
            }
        }
        composeTestRule.onNodeWithTextById(R.string.detail_error_message)
            .assertExists("No error text")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun detailScreen_horizontalErrorScreen_verifyContent() {
        composeTestRule.setContent {
            FairyTalesTheme {
                DetailScreen(
                    screenState = ScreenState.Error(),
                    textSizeFromDatastore = 24.0F,
                    isExpandedScreen = true,
                    onDetailScreenBackClick = {},
                    onSettingsClick = {},
                    onTextSizeUpdate = {},
                )
            }
        }
        composeTestRule.onNodeWithTextById(R.string.detail_error_message)
            .assertExists("No error text")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    private fun setDetailScreen(fakeTale: TaleUi, isExpanded: Boolean) {
        composeTestRule.setContent {
            FairyTalesTheme {
                DetailScreen(
                    screenState = ScreenState.Success(fakeTale),
                    textSizeFromDatastore = 24.0F,
                    isExpandedScreen = isExpanded,
                    onDetailScreenBackClick = {},
                    onSettingsClick = {},
                    onTextSizeUpdate = {},
                )
            }
        }
    }
}
