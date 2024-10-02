package com.viktoriagavrosh.home

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.viktoriagavrosh.home.utils.onNodeWithContentDescriptionForStringId
import com.viktoriagavrosh.shelf.elements.BottomTabBar
import com.viktoriagavrosh.shelf.utils.Tabs
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class BottomTabBarUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun bottomTabBar_verticalScreen_taleAnimalTabIsSelected() {
        setContent(selectedTab = Tabs.TaleTab.Animal)
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.title_animal_tales)
            .assertExists("No animal tale tab")
            .assertIsDisplayed()
            .assertHasClickAction()
            .assertIsSelected()
    }

    @Test
    fun bottomTabBar_verticalScreen_taleFairyTabIsSelected() {
        setContent(selectedTab = Tabs.TaleTab.Fairy)
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.title_fairy_tales)
            .assertExists("No fairy tale tab")
            .assertIsDisplayed()
            .assertHasClickAction()
            .assertIsSelected()
    }

    @Test
    fun bottomTabBar_verticalScreen_talePeopleTabIsSelected() {
        setContent(selectedTab = Tabs.TaleTab.People)
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.title_people_tales)
            .assertExists("No people tale tab")
            .assertIsDisplayed()
            .assertHasClickAction()
            .assertIsSelected()
    }

    @Test
    fun bottomTabBar_verticalScreen_folkPoemTabIsSelected() {
        setContent(selectedTab = Tabs.FolkTab.Poem)
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.title_poem_folk)
            .assertExists("No poem folk tab")
            .assertIsDisplayed()
            .assertHasClickAction()
            .assertIsSelected()
    }

    @Test
    fun bottomTabBar_verticalScreen_folkCountingTabIsSelected() {
        setContent(selectedTab = Tabs.FolkTab.Counting)
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.title_counting_folk)
            .assertExists("No counting folk tab")
            .assertIsDisplayed()
            .assertHasClickAction()
            .assertIsSelected()
    }

    @Test
    fun bottomTabBar_verticalScreen_folkLullabyTabIsSelected() {
        setContent(selectedTab = Tabs.FolkTab.Lullaby)
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.title_lullaby_folk)
            .assertExists("No lullaby folk tab")
            .assertIsDisplayed()
            .assertHasClickAction()
            .assertIsSelected()
    }

    @Test
    fun bottomTabBar_verticalScreen_onlyOneTabIsSelected() {
        setContent(selectedTab = Tabs.TaleTab.Animal)
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.title_animal_tales)
            .assertIsSelected()
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.title_fairy_tales)
            .assertIsNotSelected()
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.title_people_tales)
            .assertIsNotSelected()
    }

    private fun setContent(selectedTab: Tabs) {
        composeTestRule.setContent {
            FairyTalesTheme {
                BottomTabBar(
                    selectedTabProvider = { selectedTab },
                    onTabClick = {}
                )
            }
        }
    }
}
