package com.viktoriagavrosh.fairytales.ui.elements

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.model.TaleUi
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme
import com.viktoriagavrosh.fairytales.ui.utils.onNodeWithContentDescriptionById
import org.junit.Rule
import org.junit.Test

class TaleCardTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun taleCard_titleIsDisplayed() {
        val fakeTale = TaleUi(title = "Title")
        setTaleCard(fakeTale)
        composeTestRule.onNodeWithText("Title")
            .assertExists("No title on card")
            .assertIsDisplayed()
    }

    @Test
    fun taleCard_imageIsDisplayed() {
        val fakeTale = TaleUi(title = "Title")
        setTaleCard(fakeTale)
        composeTestRule.onNodeWithContentDescription("Title")
            .assertExists("No image on card")
            .assertIsDisplayed()
    }

    @Test
    fun taleCard_lightHeartIsDisplayed() {
        val fakeTale = TaleUi()
        setTaleCard(fakeTale)
        composeTestRule.onNodeWithContentDescriptionById(R.string.not_favorite)
            .assertExists("No light heart on card")
            .assertIsDisplayed()
    }

    @Test
    fun taleCard_darkHeartIsDisplayed() {
        val fakeTale = TaleUi(isFavorite = true)
        setTaleCard(fakeTale)
        composeTestRule.onNodeWithContentDescriptionById(R.string.favorite)
            .assertExists("No dark heart on card")
            .assertIsDisplayed()
    }

    private fun setTaleCard(fakeTale: TaleUi) {
        composeTestRule.setContent {
            FairyTalesTheme {
                TaleCard(
                    tale = fakeTale,
                    onCardClick = {},
                    onHeartClick = {}
                )
            }
        }
    }
}
