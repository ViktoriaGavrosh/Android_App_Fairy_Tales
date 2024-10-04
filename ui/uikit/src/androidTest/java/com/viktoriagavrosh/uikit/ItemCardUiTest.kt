package com.viktoriagavrosh.uikit

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.viktoriagavrosh.librarymenu.utils.onNodeWithContentDescriptionForStringId
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class TaleCardTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun uikit_itemCard_titleIsDisplayed() {
        val title = "Card title"
        setContent(title = title)
        composeTestRule.onNodeWithText(title)
            .assertExists("No title on card")
            .assertIsDisplayed()
    }

    @Test
    fun uikit_itemCard_heartIsDisplayed() {
        setContent(isHeartShow = true)
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.not_favorite)
            .assertExists("No heart on card")
            .assertIsDisplayed()
    }

    @Test
    fun uikit_itemCard_heartDoesNotDisplayed() {
        setContent(isHeartShow = false)
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.not_favorite)
            .assertDoesNotExist()
    }

    @Test
    fun uikit_itemCard_imageDoesNotDisplayed() {
        val title = "Title"
        setContent(imageUrl = null, title = title)
        composeTestRule.onNodeWithContentDescription(title)
            .assertDoesNotExist()
    }

    private fun setContent(
        title: String = "Title",
        imageUrl: String? = "",
        isFavorite: Boolean = false,
        isHeartShow: Boolean = false,
        isBlur: Boolean = false,
    ) {
        composeTestRule.setContent {
            FairyTalesTheme {
                ItemCard(
                    title = title,
                    imageUrl = imageUrl,
                isFavorite = isFavorite,
                isHeartShow = isHeartShow,
                isBlur = isBlur,
                onCardClick = {},
                onHeartClick = {},
                )
            }
        }
    }
}
