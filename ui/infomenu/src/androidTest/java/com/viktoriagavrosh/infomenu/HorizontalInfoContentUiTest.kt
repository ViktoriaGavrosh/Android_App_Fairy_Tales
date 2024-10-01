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
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeUp
import androidx.compose.ui.unit.dp
import com.viktoriagavrosh.infomenu.elements.HorizontalInfoContent
import com.viktoriagavrosh.infomenu.fake.FakeSource
import com.viktoriagavrosh.infomenu.model.TaleInfo
import com.viktoriagavrosh.infomenu.utils.onNodeWithTagById
import com.viktoriagavrosh.infomenu.utils.onNodeWithTextById
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class HorizontalInfoContentUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun infoContent_horizontalScreen_imageIsDisplayed() {
        setHorizontalInfoContent()
        composeTestRule.onNodeWithContentDescription(FakeSource.tale.title)
            .assertExists("No image")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun startContent_horizontalScreen_titleIsDisplayed() {
        setHorizontalInfoContent()
        composeTestRule.onNodeWithText(FakeSource.tale.title)
            .assertExists("No title")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun infoContent_horizontalScreen_readButtonIsDisplayed() {
        setHorizontalInfoContent()
        composeTestRule.onNodeWithTagById(R.string.horizontal_info_content_test_tag)
            .performTouchInput { swipeUp() }
        composeTestRule.onNodeWithTextById(R.string.read_button)
            .assertExists("No read button")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun infoContent_horizontalScreen_readButtonSizeIsRelevant() {
        setHorizontalInfoContent()
        composeTestRule.onNodeWithTagById(R.string.horizontal_info_content_test_tag)
            .performTouchInput { swipeUp() }
        composeTestRule.onNodeWithTextById(R.string.read_button)
            .assertHeightIsAtLeast(48.dp)
    }

    private fun setHorizontalInfoContent(tale: TaleInfo = FakeSource.tale) {
        composeTestRule.setContent {
            FairyTalesTheme {
                HorizontalInfoContent(
                    taleProvider = { tale },
                    onReadClick = {},
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
