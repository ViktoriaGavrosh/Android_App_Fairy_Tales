package com.viktoriagavrosh.infomenu

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.viktoriagavrosh.infomenu.elements.ContentInfoScreen
import com.viktoriagavrosh.infomenu.fake.FakeSource
import com.viktoriagavrosh.infomenu.model.TaleInfo
import com.viktoriagavrosh.infomenu.utils.onNodeWithContentDescriptionForStringId
import com.viktoriagavrosh.infomenu.utils.onNodeWithTextById
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class ContentInfoScreenUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun infoScreen_verticalScreen_backButtonOnTopBarIsDisplayed() {
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
        composeTestRule.onNodeWithTextById(R.string.tale_top_bar_title)
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
        composeTestRule.onNodeWithTextById(R.string.tale_top_bar_title)
            .assertExists("No title on topBar")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    private fun setContentScreen(tale: TaleInfo = FakeSource.tale, isVerticalScreen: Boolean) {
        composeTestRule.setContent {
            FairyTalesTheme {
                ContentInfoScreen(
                    taleProvider = { tale },
                    isVerticalScreen = isVerticalScreen,
                    onBackClick = {},
                    onReadClick = {},
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
