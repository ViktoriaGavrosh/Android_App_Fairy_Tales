package com.viktoriagavrosh.navigation

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.viktoriagavrosh.details.DetailScreen
import com.viktoriagavrosh.repositories.utils.ShelfGenre
import com.viktoriagavrosh.settings.SettingsScreen
import com.viktoriagavrosh.shelf.ShelfScreen

/**
 * Top level composable that represents screens for the application
 */
@Composable
fun FairyTalesApp(
    modifier: Modifier = Modifier,
    windowSize: WindowWidthSizeClass,
) {
    val navController = rememberNavController()

    val isVerticalScreen = windowSize == WindowWidthSizeClass.Compact

    NavHost(
        navController = navController,
        startDestination = NavigationDestination.HomeScreen.screen,
    ) {
        composable(
            route = NavigationDestination.HomeScreen.screen
        ) {
            ShelfScreen(
                genreName = ShelfGenre.Tales.Fairy.genreName,  // TODO 111 заглушка
                isVerticalScreen = isVerticalScreen,
                onCardClick = { id ->
                    navController.navigate(
                        route = "${NavigationDestination.DetailScreen.screen}/${id}"
                    )
                },
                onBackClick = { navController.navigateUp() },
                modifier = modifier,
            )
        }
        composable(
            route = "${NavigationDestination.DetailScreen.screen}/{value}"
        ) { backStackEntry ->
            val taleId = backStackEntry.arguments?.getString("value")?.toInt() ?: 0
            DetailScreen(
                taleId = taleId,
                isExpandedScreen = windowSize == WindowWidthSizeClass.Expanded,
                onDetailScreenBackClick = {
                    navController.navigate(NavigationDestination.HomeScreen.screen)
                },
                onSettingsClick = {
                    navController.navigate(NavigationDestination.SettingsScreen.screen)
                },
                modifier = modifier,
            )
        }
        composable(
            route = NavigationDestination.SettingsScreen.screen
        ) {
            SettingsScreen(
                isVerticalScreen = isVerticalScreen,
                onBackClick = {
                    navController.navigateUp()
                },
                modifier = modifier,
            )
        }
    }
}

enum class NavigationDestination(val screen: String) {
    HomeScreen(screen = "home"),
    DetailScreen(screen = "details"),
    SettingsScreen(screen = "settings")
}
