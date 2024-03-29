package com.viktoriagavrosh.fairytales.compose.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.compose.utils.onNodeWithContentDescriptionForStringId
import com.viktoriagavrosh.fairytales.data.FolkWorkType
import com.viktoriagavrosh.fairytales.ui.FairyTalesUiState
import com.viktoriagavrosh.fairytales.ui.elements.FolkWorkCard
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme
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
                    folkWork = fakeUiState.selectedWork,
                    isBlurImage = false,
                    onCardClick = {},
                    onHeartClick = {}
                )
            }
        }
        composeTestRule.onNodeWithText(fakeUiState.selectedWork.title).assertExists("No card title")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.not_favorite)
            .assertExists("No heart on top bar")
        composeTestRule.onNodeWithContentDescription(fakeUiState.selectedWork.title)
            .assertExists("No image")
    }

    @Test
    fun folkWorkCard_verifyDarkHeart() {
        val fakeFolkWork = FairyTalesUiState().selectedWork.copy(isFavorite = true)
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