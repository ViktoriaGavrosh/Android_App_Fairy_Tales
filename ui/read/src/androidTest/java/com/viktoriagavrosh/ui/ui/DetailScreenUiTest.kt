package com.viktoriagavrosh.ui.ui

import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.viktoriagavrosh.details.DetailScreenState
import com.viktoriagavrosh.details.R
import com.viktoriagavrosh.details.ReadScreen
import com.viktoriagavrosh.details.model.TaleUiDetail
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class DetailScreenUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun detailScreen_verticalScreen_verifyBarContent() {
        val fakeTale = TaleUiDetail(
            title = "title",
        )
        setDetailScreen(fakeTale, isExpanded = false)
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.back)
            .assertExists("No image")
        composeTestRule.onNodeWithText(fakeTale.title)
            .assertExists("No title")
    }

    @Test
    fun detailScreen_horizontalScreen_verifyBarContent() {
        val fakeTale = TaleUiDetail(
            title = "title",
        )
        setDetailScreen(fakeTale, isExpanded = true)
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.back)
            .assertExists("No image")
        composeTestRule.onNodeWithText(fakeTale.title)
            .assertExists("No title")
    }

    @Test
    fun detailScreen_verticalErrorScreen_verifyContent() {
        composeTestRule.setContent {
            FairyTalesTheme {
                ReadScreen(
                    screenState = DetailScreenState.Error(),
                    textSizeFromDataStore = 24.0F,
                    isExpandedScreen = false,
                    onDetailScreenBackClick = {},
                    onSettingsClick = {},
                    onTextSizeUpdate = {},
                )
            }
        }
        composeTestRule.onNodeWithTextById(R.string.error_message_text)
            .assertExists("No error text")
    }

    @Test
    fun detailScreen_horizontalErrorScreen_verifyContent() {
        composeTestRule.setContent {
            FairyTalesTheme {
                ReadScreen(
                    screenState = DetailScreenState.Error(),
                    textSizeFromDataStore = 24.0F,
                    isExpandedScreen = true,
                    onDetailScreenBackClick = {},
                    onSettingsClick = {},
                    onTextSizeUpdate = {},
                )
            }
        }
        composeTestRule.onNodeWithTextById(R.string.error_message_text)
            .assertExists("No error text")
    }

    private fun setDetailScreen(fakeTale: TaleUiDetail, isExpanded: Boolean) {
        composeTestRule.setContent {
            FairyTalesTheme {
                ReadScreen(
                    screenState = DetailScreenState.Success(fakeTale),
                    textSizeFromDataStore = 24.0F,
                    isExpandedScreen = isExpanded,
                    onDetailScreenBackClick = {},
                    onSettingsClick = {},
                    onTextSizeUpdate = {},
                )
            }
        }
    }
}

internal fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.onNodeWithContentDescriptionForStringId(
    @StringRes id: Int
): SemanticsNodeInteraction =
    onNodeWithContentDescription(activity.getString(id))

internal fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.onNodeWithTextById(
    @StringRes id: Int
): SemanticsNodeInteraction =
    onNodeWithText(activity.getString(id))
