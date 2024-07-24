package com.viktoriagavrosh.navigation

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.viktoriagavrosh.details.DetailScreen
import com.viktoriagavrosh.home.HomeScreen

/**
 * Top level composable that represents screens for the application
 */
@Composable
fun FairyTalesApp(
    modifier: Modifier = Modifier,
    windowSize: WindowWidthSizeClass,
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavigationDestination.HomeScreen.screen,
    ) {
        composable(
            route = NavigationDestination.HomeScreen.screen
        ) {
            HomeScreen(
                windowSize = windowSize,
                onCardClick = { tale ->
                    navController.navigate(
                        route = "${NavigationDestination.DetailScreen.screen}/${tale.id}"
                    )
                },
                modifier = modifier
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
                modifier = modifier,
            )
        }
    }
}

enum class NavigationDestination(val screen: String) {
    HomeScreen(screen = "home"),
    DetailScreen(screen = "details")
}
