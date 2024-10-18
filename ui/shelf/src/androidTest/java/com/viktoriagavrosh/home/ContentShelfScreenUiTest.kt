package com.viktoriagavrosh.home

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.viktoriagavrosh.home.fake.FakeSource
import com.viktoriagavrosh.home.utils.onNodeWithTagById
import com.viktoriagavrosh.home.utils.onNodeWithTextById
import com.viktoriagavrosh.shelf.elements.ContentScreen
import com.viktoriagavrosh.shelf.utils.Tabs
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class ContentShelfScreenUiTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun contentScreen_verticalScreen_verifyBottomTabBarWithTale() {
        setContentScreen(
            isVerticalScreen = true,
            selectedTab = Tabs.TaleTab.Animal,
        )
        composeTestRule.onNodeWithTagById(R.string.compact_screen_test_tag)
            .assertExists("No bottom navigation bar")
    }

    @Test
    fun homeScreen_horizontalScreen_verifyLeftTabRailWithTale() {
        setContentScreen(
            isVerticalScreen = false,
            selectedTab = Tabs.TaleTab.Animal,
        )
        composeTestRule.onNodeWithTagById(R.string.expanded_screen_test_tag)
            .assertExists("No navigation rail")
    }

    @Test
    fun contentScreen_verticalScreen_verifyBottomTabBarWithFolk() {
        setContentScreen(
            isVerticalScreen = true,
            selectedTab = Tabs.FolkTab.Poem,
        )
        composeTestRule.onNodeWithTagById(R.string.compact_screen_test_tag)
            .assertExists("No bottom navigation bar")
    }

    @Test
    fun homeScreen_horizontalScreen_verifyLeftTabRailWithFolk() {
        setContentScreen(
            isVerticalScreen = false,
            selectedTab = Tabs.FolkTab.Poem,
        )
        composeTestRule.onNodeWithTagById(R.string.expanded_screen_test_tag)
            .assertExists("No navigation rail")
    }

    @Test
    fun contentScreen_verticalScreen_verifyScreenWithoutBottomTabBar() {
        setContentScreen(
            isVerticalScreen = true,
            selectedTab = Tabs.Riddle,
        )
        composeTestRule.onNodeWithTagById(R.string.compact_screen_test_tag)
            .assertDoesNotExist()
    }

    @Test
    fun homeScreen_horizontalScreen_verifyScreenWithoutLeftTabRail() {
        setContentScreen(
            isVerticalScreen = false,
            selectedTab = Tabs.Riddle,
        )
        composeTestRule.onNodeWithTagById(R.string.expanded_screen_test_tag)
            .assertDoesNotExist()
    }

    @Test
    fun contentScreen_verticalScreen_verifyTopBarTitleWithTaleAnimal() {
        setContentScreen(
            isVerticalScreen = true,
            selectedTab = Tabs.TaleTab.Animal,
        )
        composeTestRule.onNodeWithTextById(R.string.title_animal_tales)
            .assertExists("No animal tale title on top bar")
    }

    @Test
    fun contentScreen_verticalScreen_verifyTopBarTitleWithTaleFairy() {
        setContentScreen(
            isVerticalScreen = true,
            selectedTab = Tabs.TaleTab.Fairy,
        )
        composeTestRule.onNodeWithTextById(R.string.title_fairy_tales)
            .assertExists("No fairy tale title on top bar")
    }

    @Test
    fun contentScreen_verticalScreen_verifyTopBarTitleWithTalePeople() {
        setContentScreen(
            isVerticalScreen = true,
            selectedTab = Tabs.TaleTab.People,
        )
        composeTestRule.onNodeWithTextById(R.string.title_people_tales)
            .assertExists("No people tale title on top bar")
    }

    @Test
    fun contentScreen_verticalScreen_verifyTopBarTitleWithFolkPoem() {
        setContentScreen(
            isVerticalScreen = true,
            selectedTab = Tabs.FolkTab.Poem,
        )
        composeTestRule.onNodeWithTextById(R.string.title_poem_folk)
            .assertExists("No poem folk title on top bar")
    }

    @Test
    fun contentScreen_verticalScreen_verifyTopBarTitleWithFolkCounting() {
        setContentScreen(
            isVerticalScreen = true,
            selectedTab = Tabs.FolkTab.Counting,
        )
        composeTestRule.onNodeWithTextById(R.string.title_counting_folk)
            .assertExists("No counting folk title on top bar")
    }

    @Test
    fun contentScreen_verticalScreen_verifyTopBarTitleWithFolkLullaby() {
        setContentScreen(
            isVerticalScreen = true,
            selectedTab = Tabs.FolkTab.Lullaby,
        )
        composeTestRule.onNodeWithTextById(R.string.title_lullaby_folk)
            .assertExists("No lullaby folk title on top bar")
    }

    @Test
    fun contentScreen_verticalScreen_verifyTopBarTitleWithRiddle() {
        setContentScreen(
            isVerticalScreen = true,
            selectedTab = Tabs.Riddle,
        )
        composeTestRule.onNodeWithTextById(R.string.title_riddles)
            .assertExists("No riddle title on top bar")
    }

    @Test
    fun contentScreen_verticalScreen_verifyTopBarTitleWithTaleFavorite() {
        setContentScreen(
            isVerticalScreen = true,
            selectedTab = Tabs.Favorite,
        )
        composeTestRule.onNodeWithTextById(R.string.title_favorite)
            .assertExists("No favorite tale title on top bar")
    }

    @Test
    fun contentScreen_verticalScreen_verifyTopBarTitleWithNightTale() {
        setContentScreen(
            isVerticalScreen = true,
            selectedTab = Tabs.Night,
        )
        composeTestRule.onNodeWithTextById(R.string.title_night)
            .assertExists("No night tale title on top bar")
    }

    @Test
    fun contentScreen_horizontalScreen_verifyTopBarTitleWithTaleAnimal() {
        setContentScreen(
            isVerticalScreen = false,
            selectedTab = Tabs.TaleTab.Animal,
        )
        composeTestRule.onNodeWithTextById(R.string.title_animal_tales)
            .assertExists("No animal tale title on top bar")
    }

    @Test
    fun contentScreen_horizontalScreen_verifyTopBarTitleWithTaleFairy() {
        setContentScreen(
            isVerticalScreen = false,
            selectedTab = Tabs.TaleTab.Fairy,
        )
        composeTestRule.onNodeWithTextById(R.string.title_fairy_tales)
            .assertExists("No fairy tale title on top bar")
    }

    @Test
    fun contentScreen_horizontalScreen_verifyTopBarTitleWithTalePeople() {
        setContentScreen(
            isVerticalScreen = false,
            selectedTab = Tabs.TaleTab.People,
        )
        composeTestRule.onNodeWithTextById(R.string.title_people_tales)
            .assertExists("No people tale title on top bar")
    }

    @Test
    fun contentScreen_horizontalScreen_verifyTopBarTitleWithFolkPoem() {
        setContentScreen(
            isVerticalScreen = false,
            selectedTab = Tabs.FolkTab.Poem,
        )
        composeTestRule.onNodeWithTextById(R.string.title_poem_folk)
            .assertExists("No poem folk title on top bar")
    }

    @Test
    fun contentScreen_horizontalScreen_verifyTopBarTitleWithFolkCounting() {
        setContentScreen(
            isVerticalScreen = false,
            selectedTab = Tabs.FolkTab.Counting,
        )
        composeTestRule.onNodeWithTextById(R.string.title_counting_folk)
            .assertExists("No counting folk title on top bar")
    }

    @Test
    fun contentScreen_horizontalScreen_verifyTopBarTitleWithFolkLullaby() {
        setContentScreen(
            isVerticalScreen = false,
            selectedTab = Tabs.FolkTab.Lullaby,
        )
        composeTestRule.onNodeWithTextById(R.string.title_lullaby_folk)
            .assertExists("No lullaby folk title on top bar")
    }

    @Test
    fun contentScreen_horizontalScreen_verifyTopBarTitleWithRiddle() {
        setContentScreen(
            isVerticalScreen = false,
            selectedTab = Tabs.Riddle,
        )
        composeTestRule.onNodeWithTextById(R.string.title_riddles)
            .assertExists("No riddle title on top bar")
    }

    @Test
    fun contentScreen_horizontalScreen_verifyTopBarTitleWithTaleFavorite() {
        setContentScreen(
            isVerticalScreen = false,
            selectedTab = Tabs.Favorite,
        )
        composeTestRule.onNodeWithTextById(R.string.title_favorite)
            .assertExists("No favorite tale title on top bar")
    }

    @Test
    fun contentScreen_horizontalScreen_verifyTopBarTitleWithNightTale() {
        setContentScreen(
            isVerticalScreen = false,
            selectedTab = Tabs.Night,
        )
        composeTestRule.onNodeWithTextById(R.string.title_night)
            .assertExists("No night tale title on top bar")
    }

    private fun setContentScreen(isVerticalScreen: Boolean, selectedTab: Tabs) {
        composeTestRule.setContent {
            FairyTalesTheme {
                ContentScreen(
                    booksProvider = { FakeSource.books },
                    selectedTabProvider = { selectedTab },
                    isVerticalScreen = isVerticalScreen,
                    onCardClick = {},
                    onHeartClick = {},
                    onBackClick = {},
                    onTabClick = {},
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}
