package com.viktoriagavrosh.ui.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.viktoriagavrosh.details.R
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme
//import com.viktoriagavrosh.ui.uiscreens.FairyTalesUiState
import com.viktoriagavrosh.home.uiscreens.FolkWorkType
//import com.viktoriagavrosh.ui.uiscreens.elements.FolkWorkCard
import com.viktoriagavrosh.ui.utils.onNodeWithContentDescriptionForStringId
import org.junit.Rule
import org.junit.Test
/*
class FolkWorkUiDetailsCardTest {
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


 */
