package com.viktoriagavrosh.navigation

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.viktoriagavrosh.ui.uiscreens.FairyTalesViewModel
import com.viktoriagavrosh.ui.uiscreens.FolkWorkType
import com.viktoriagavrosh.ui.uiscreens.screens.ContentScreen
import com.viktoriagavrosh.ui.uiscreens.screens.detailscreens.DetailScreen
import com.viktoriagavrosh.ui.uiscreens.utils.UILogic

/**
 * Top level composable that represents screens for the application
 */
@Composable
fun FairyTalesApp(
    modifier: Modifier = Modifier,
    windowSize: WindowWidthSizeClass,
    viewModel: FairyTalesViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val logic = UILogic(
        onTabClick = viewModel::updateCompositionType,
        onHeartClick = viewModel::updateWorkFavorite,
        onTopBarHeartClick = viewModel::updateListFavoriteWorks,
    )
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "home",
    ) {
        composable(
            route = "home"
        ) {
            ContentScreen(
                uiState = uiState,
                logic = logic,
                windowSize = windowSize,
                onCardClick = { folkWork ->
                    val id = folkWork.id
                    navController.navigate(route = "details/$id")
                },
                modifier = modifier
            )
        }
        composable(
            route = "details/{value}"
        ) { backStackEntry ->
            val folkWorkId = backStackEntry.arguments?.getString("value")?.toInt() ?: 0
            viewModel.updateSelectedWork(folkWorkId)
            DetailScreen(
                folkWork = uiState.selectedWork,
                logic = logic,
                isPuzzleType = uiState.folkWorkType == FolkWorkType.Puzzle,
                isStoryType = uiState.folkWorkType == FolkWorkType.Story,
                isExpandedScreen = windowSize == WindowWidthSizeClass.Expanded,
                onDetailScreenBackClick = { navController.navigate("home") },
                modifier = modifier
            )
        }
    }
}
