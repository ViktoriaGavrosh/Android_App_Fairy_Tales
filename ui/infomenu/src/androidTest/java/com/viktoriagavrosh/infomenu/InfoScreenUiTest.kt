package com.viktoriagavrosh.infomenu

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.viktoriagavrosh.infomenu.model.TaleInfo
import com.viktoriagavrosh.infomenu.utils.onNodeWithTagById
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class InfoScreenUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun infoScreen_verticalScreen_error() {
        setInfoScreen(
            isError = true,
            isVerticalScreen = true
        )
        composeTestRule.onNodeWithTagById(R.string.error_screen_test_tag)
            .assertExists("No error screen")
            .assertIsDisplayed()
    }

    @Test
    fun infoScreen_horizontalScreen_error() {
        setInfoScreen(
            isError = true,
            isVerticalScreen = false
        )
        composeTestRule.onNodeWithTagById(R.string.error_screen_test_tag)
            .assertExists("No error screen")
            .assertIsDisplayed()
    }


    private fun setInfoScreen(isError: Boolean, isVerticalScreen: Boolean) {
        composeTestRule.setContent {
            FairyTalesTheme {
                InfoScreen(
                    taleProvider = { TaleInfo() },
                    isErrorProvider = { isError },
                    isVerticalScreen = isVerticalScreen,
                    onBackClick = {},
                    onReadClick = {},
                    onErrorButtonClick = {},
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
