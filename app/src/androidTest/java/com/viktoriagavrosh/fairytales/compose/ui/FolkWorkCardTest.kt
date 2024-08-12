package com.viktoriagavrosh.fairytales.compose.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.compose.utils.onNodeWithContentDescriptionForStringId
import com.viktoriagavrosh.fairytales.data.TaleType
import com.viktoriagavrosh.fairytales.ui.TalesUiState
import com.viktoriagavrosh.fairytales.ui.elements.TaleCard
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class FolkWorkCardTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun folkWorkCard_verifyContent() {
        val fakeUiState = TalesUiState().copy(taleType = TaleType.Story)
        composeTestRule.setContent {
            FairyTalesTheme {
                TaleCard(
                    tale = fakeUiState.selectedTale,
                    isBlurImage = false,
                    onCardClick = {},
                    onHeartClick = {}
                )
            }
        }
        composeTestRule.onNodeWithText(fakeUiState.selectedTale.title).assertExists("No card title")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.not_favorite)
            .assertExists("No heart on top bar")
        composeTestRule.onNodeWithContentDescription(fakeUiState.selectedTale.title)
            .assertExists("No image")
    }

    @Test
    fun folkWorkCard_verifyDarkHeart() {
        val fakeFolkWork = TalesUiState().selectedTale.copy(isFavorite = true)
        composeTestRule.setContent {
            FairyTalesTheme {
                TaleCard(
                    tale = fakeFolkWork,
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
