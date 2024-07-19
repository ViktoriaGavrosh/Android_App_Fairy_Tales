package com.viktoriagavrosh.navigation

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.viktoriagavrosh.details.DetailScreen
import com.viktoriagavrosh.home.uiscreens.screens.HomeScreen

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
        startDestination = "home",
    ) {
        composable(
            route = "home"
        ) {
            HomeScreen(
                windowSize = windowSize,
                onCardClick = { folkWork ->
                    navController.navigate(route = "details/${folkWork.id}")
                },
                modifier = modifier
            )
        }
        composable(
            route = "details/{value}"
        ) { backStackEntry ->
            val folkWorkId = backStackEntry.arguments?.getString("value")?.toInt() ?: 0
            DetailScreen(
                folkWorkId = folkWorkId,
                isExpandedScreen = windowSize == WindowWidthSizeClass.Expanded,
                onDetailScreenBackClick = { navController.navigate("home") },
                modifier = modifier,
            )
        }
    }
}
