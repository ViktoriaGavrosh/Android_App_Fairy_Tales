package com.viktoriagavrosh.fairytales.compose.utils.navigation

/* TODO Эти тесты пока не работают (не настраивала)
class NavigationTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun navigation_clickCard_navigateHomeScreenToDetailScreen() {
        val selectedFolkWorkId = "4"
        composeTestRule.setContent {
            FairyTalesTheme {
                FairyTalesApp(windowSize = WindowWidthSizeClass.Compact)
            }
        }
        composeTestRule.onNodeWithTag(selectedFolkWorkId).performClick()
        composeTestRule.onNodeWithTagForStringId(R.string.vertical_detail_screen)
            .assertExists("No navigation from HomeScreen to DetailScreen")
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.back).performClick()
        composeTestRule.onNodeWithText("Казк\u0456")
            .assertExists("No navigation from DetailScreen to HomeScreen")
    }

    @Test
    @TestCompactWidth
    fun navigation_clickHeartOnTopBar_navigateToFavoriteList() {
        val selectedFolkWorkId = "4"
        composeTestRule.setContent {
            FairyTalesTheme {
                FairyTalesApp(windowSize = WindowWidthSizeClass.Compact)
            }
        }
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.all_folk_works)
            .performClick()
        composeTestRule.onNodeWithTag(selectedFolkWorkId).assertIsNotDisplayed()
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.favorite_folk_works)
            .performClick()
        composeTestRule.onNodeWithTag(selectedFolkWorkId).assertExists("No card")
    }
}


 */
