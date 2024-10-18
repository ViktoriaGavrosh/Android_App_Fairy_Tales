package com.viktoriagavrosh.infomenu

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
import com.viktoriagavrosh.infomenu.elements.VerticalInfoContent
import com.viktoriagavrosh.infomenu.fake.FakeSource
import com.viktoriagavrosh.infomenu.model.TaleInfo
import com.viktoriagavrosh.infomenu.utils.onNodeWithTextById
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class VerticalInfoContentUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun infoContent_verticalScreen_imageIsDisplayed() {
        setVerticalInfoContent()
        composeTestRule.onNodeWithContentDescription(FakeSource.tale.title)
            .assertExists("No image")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun startContent_verticalScreen_titleIsDisplayed() {
        setVerticalInfoContent()
        composeTestRule.onNodeWithText(FakeSource.tale.title)
            .assertExists("No title")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun infoContent_verticalScreen_readButtonIsDisplayed() {
        setVerticalInfoContent()
        composeTestRule.onNodeWithTextById(R.string.read_button)
            .assertExists("No read button")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun infoContent_verticalScreen_readButtonSizeIsRelevant() {
        setVerticalInfoContent()
        composeTestRule.onNodeWithTextById(R.string.read_button)
            .assertHeightIsAtLeast(48.dp)
    }

    private fun setVerticalInfoContent(tale: TaleInfo = FakeSource.tale) {
        composeTestRule.setContent {
            FairyTalesTheme {
                VerticalInfoContent(
                    taleProvider = { tale },
                    onReadClick = {},
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
