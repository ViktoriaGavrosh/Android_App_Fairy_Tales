package com.viktoriagavrosh.home

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import com.viktoriagavrosh.home.fake.FakeSource
import com.viktoriagavrosh.shelf.elements.HorizontalBookshelf
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class HorizontalBookshelfUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun bookshelf_horizontalScreen_cardIsDisplayed() {
        setHorizontalContent()
        composeTestRule.onNodeWithTag("0")
            .assertExists("No card")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    private fun setHorizontalContent() {
        composeTestRule.setContent {
            FairyTalesTheme {
                HorizontalBookshelf(
                    paddingValues = PaddingValues(0.dp),
                    booksProvider = { FakeSource.books },
                    isHeartShow = true,
                    isBlurImages = false,
                    onCardClick = {},
                    onHeartClick = {},
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
