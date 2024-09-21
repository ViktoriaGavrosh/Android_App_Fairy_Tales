package com.viktoriagavrosh.navigation

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.viktoriagavrosh.read.ReadScreen
import com.viktoriagavrosh.repositories.utils.ShelfGenre
import com.viktoriagavrosh.riddle.RiddleScreen
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
                        route = "${NavigationDestination.ReadScreen.screen}/${id}"
                    )
                },
                onBackClick = { navController.navigateUp() },
                modifier = modifier,
            )
        }
        composable(
            route = "${NavigationDestination.ReadScreen.screen}/{value}"
        ) { backStackEntry ->
            val taleId = backStackEntry.arguments?.getString("value")?.toInt() ?: 0
            ReadScreen(
                bookId = taleId,
                genre = ShelfGenre.Nights,   // TODO  заглушка
                isVerticalScreen = isVerticalScreen,
                onBackClick = {
                    navController.navigate(NavigationDestination.HomeScreen.screen)
                },
                onSettingsClick = {
                    navController.navigate(NavigationDestination.SettingsScreen.screen)
                },
                onInfoClick = {},   // TODO  заглушка
                modifier = modifier,
            )
        }
        composable(
            route = "${NavigationDestination.RiddleScreen.screen}/{value}"
        ) { backStackEntry ->
            val riddleId = backStackEntry.arguments?.getString("value")?.toInt() ?: 0
            RiddleScreen(
                riddleId = riddleId,
                isVerticalScreen = isVerticalScreen,
                onBackClick = {
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
    ReadScreen(screen = "read"),
    SettingsScreen(screen = "settings"),
    RiddleScreen(screen = "riddle"),
}
