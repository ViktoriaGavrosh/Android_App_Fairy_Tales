package com.viktoriagavrosh.navigation

/* TODO  слишком сложно пака
@HiltAndroidTest
//@UninstallModules(value = AppModule::class)
class NavigationTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this) //createComposeRule()

    @get:Rule
    val composeTestRule = createComposeRule()//createAndroidComposeRule<MainActivity>()

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun testNavigateToDetailsScreen() {
        composeTestRule.setContent {
            FairyTalesApp(windowSize = WindowWidthSizeClass.Compact)
        }
        val selectedFolkWorkId = "4"

        composeTestRule.onNodeWithTag(selectedFolkWorkId).performClick()
        composeTestRule.onNodeWithText("Vertical detail screen").assertIsDisplayed()
    }

    /*
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
        composeTestRule.onNodeWithText("Vertical detail screen")
            .assertExists("No navigation from HomeScreen to DetailScreen")
    }

    @Test
    fun navigation_clickCard_navigateDetailScreenToHomeScreen() {
        val selectedFolkWorkId = "4"
        composeTestRule.setContent {
            FairyTalesTheme {
                FairyTalesApp(windowSize = WindowWidthSizeClass.Compact)
            }
        }
        composeTestRule.onNodeWithTag(selectedFolkWorkId).performClick()
        composeTestRule.onNodeWithContentDescription("back").performClick()
        composeTestRule.onNodeWithText("Казк\u0456")
            .assertExists("No navigation from DetailScreen to HomeScreen")
    }

     */
}


 */
