package com.viktoriagavrosh.settings

import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.rules.ActivityScenarioRule

import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class SettingsScreenUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun settingsScreen_verticalScreen_verifyBackButton() {
        setSettingsScreen()

        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.back)
            .assertExists("No icon back")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun settingsScreen_verticalScreen_titleBarContent() {
        setSettingsScreen()

        composeTestRule.onNodeWithTextById(R.string.settings_title)
            .assertExists("No settings topBar title ")
            .assertHasNoClickAction()
            .assertHasNoClickAction()
    }

    private fun setSettingsScreen() {
        composeTestRule.setContent {
            FairyTalesTheme {
                SettingsScreen(
                    uiState = SettingsState(
                        textSize = 24.0F,
                    ),
                    onTextSizeUpdate = {},
                    onBackClick = {}
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

internal fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.onNodeWithTagForStringId(
    @StringRes id: Int
): SemanticsNodeInteraction =
    onNodeWithTag(activity.getString(id))
