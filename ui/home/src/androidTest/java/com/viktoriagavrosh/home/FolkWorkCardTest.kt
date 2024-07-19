package com.viktoriagavrosh.home

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme
import com.viktoriagavrosh.home.R
import com.viktoriagavrosh.home.uiscreens.FairyTalesUiState
import com.viktoriagavrosh.home.uiscreens.FolkWorkType
import com.viktoriagavrosh.home.uiscreens.elements.FolkWorkCard
import org.junit.Rule
import org.junit.Test

class FolkWorkCardTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun folkWorkCard_verifyContent() {
        val fakeUiState = FairyTalesUiState().copy(folkWorkType = FolkWorkType.Story)
        composeTestRule.setContent {
            FairyTalesTheme {
                FolkWorkCard(
                    folkWork = fakeUiState.folkWorks[0],
                    isBlurImage = false,
                    onCardClick = {},
                    onHeartClick = {}
                )
            }
        }
        composeTestRule.onNodeWithText(fakeUiState.folkWorks[0].title).assertExists("No card title")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.not_favorite)
            .assertExists("No heart on top bar")
        composeTestRule.onNodeWithContentDescription(fakeUiState.folkWorks[0].title)
            .assertExists("No image")
    }

    @Test
    fun folkWorkCard_verifyDarkHeart() {
        val fakeFolkWork = FairyTalesUiState().folkWorks[0].copy(isFavorite = true)
        composeTestRule.setContent {
            FairyTalesTheme {
                FolkWorkCard(
                    folkWork = fakeFolkWork,
                    isBlurImage = false,
                    onCardClick = {},
                    onHeartClick = {}
                )
            }
        }
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.favorite)
            .assertExists("No dark heart on card")
    }
}
