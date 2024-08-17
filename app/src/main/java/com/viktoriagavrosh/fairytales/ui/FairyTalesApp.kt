package com.viktoriagavrosh.fairytales.ui

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.viktoriagavrosh.fairytales.ui.detailscreen.DetailScreen
import com.viktoriagavrosh.fairytales.ui.homescreen.HomeScreen
import com.viktoriagavrosh.fairytales.ui.settingsscreen.SettingsScreen

/**
 * Top level composable that represents screens for the application
 */
@Composable
fun FairyTalesApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavigationDestination.HomeScreen.screenName,
    ) {
        composable(
            route = NavigationDestination.HomeScreen.screenName,
        ) {
            HomeScreen(
                windowSize = windowSize,
                onCardClick = { tale ->
                    navController.navigate(
                        route = "${NavigationDestination.DetailScreen.screenName}/${tale.id}"
                    )
                },
                onSettingsClick = {
                    navController.navigate(NavigationDestination.SettingsScreen.screenName)
                },
                modifier = modifier,
            )
        }
        composable(
            route = "${NavigationDestination.DetailScreen.screenName}/{value}"
        ) { backStackEntry ->
            val taleId = backStackEntry.arguments?.getString("value")?.toInt() ?: 0
            DetailScreen(
                taleId = taleId,
                isExpandedScreen = windowSize == WindowWidthSizeClass.Expanded,
                onDetailScreenBackClick = {
                    navController.navigate(NavigationDestination.HomeScreen.screenName)
                },
                onSettingsClick = {
                    navController.navigate(NavigationDestination.SettingsScreen.screenName)
                },
                modifier = modifier,
            )
        }
        composable(
            route = NavigationDestination.SettingsScreen.screenName
        ) {
            SettingsScreen(
                onBackClick = { navController.navigateUp() }
            )
        }
    }
}

enum class NavigationDestination(val screenName: String) {
    HomeScreen(screenName = "home"),
    DetailScreen(screenName = "details"),
    SettingsScreen(screenName = "settings"),
}
