package com.viktoriagavrosh.home

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.viktoriagavrosh.home.elements.TaleCard
import com.viktoriagavrosh.home.model.TaleUiHome
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class TaleCardTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val fakeList: List<TaleUiHome> = listOf(
        TaleUiHome(
            title = "Title",
        )
    )

    @Test
    fun folkWorkCard_verifyContent() {
        //val fakeUiState = TalesListUiState().copy(taleType = TaleType.Story)
        composeTestRule.setContent {
            FairyTalesTheme {
                TaleCard(
                    tale = fakeList[0],
                    onCardClick = {},
                    onHeartClick = {}
                )
            }
        }
        composeTestRule.onNodeWithText(fakeList[0].title).assertExists("No card title")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.not_favorite)
            .assertExists("No heart on top bar")
        composeTestRule.onNodeWithContentDescription(fakeList[0].title)
            .assertExists("No image")
    }

    @Test
    fun folkWorkCard_verifyDarkHeart() {
        val fakeTale = fakeList[0].copy(isFavorite = true)
        composeTestRule.setContent {
            FairyTalesTheme {
                TaleCard(
                    tale = fakeTale,
                    onCardClick = {},
                    onHeartClick = {}
                )
            }
        }
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.favorite)
            .assertExists("No dark heart on card")
    }
}
