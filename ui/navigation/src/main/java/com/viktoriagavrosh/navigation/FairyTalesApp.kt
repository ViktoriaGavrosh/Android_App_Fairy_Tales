package com.viktoriagavrosh.navigation

import android.util.Log
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.viktoriagavrosh.details.uiscreens.screens.detailscreens.DetailScreen
import com.viktoriagavrosh.home.uiscreens.FairyTalesViewModel
import com.viktoriagavrosh.home.uiscreens.screens.HomeScreen
import javax.inject.Inject

/**
 * Top level composable that represents screens for the application
 */
@Composable
fun FairyTalesApp(
    modifier: Modifier = Modifier,
    windowSize: WindowWidthSizeClass,
    //viewModel: FairyTalesViewModel = viewModel(), //TODO delete
) {
    /*    TODO delete
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val logic = UILogic(
        onTabClick = viewModel::updateCompositionType,
        onHeartClick = viewModel::updateWorkFavorite,
        onTopBarHeartClick = viewModel::updateListFavoriteWorks,
    )

     */
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "home",
    ) {
        composable(
            route = "home"
        ) {
            HomeScreen(
                //uiState = uiState,    TODO delete
               // logic = logic,    TODO delete
                windowSize = windowSize,
                onCardClick = { folkWork ->
                    val id = folkWork.id   // TODO try to delete
                    navController.navigate(route = "details/$id")
                },
                modifier = modifier
            )
        }
        composable(
            route = "details/{value}"
        ) { backStackEntry ->
            val folkWorkId = backStackEntry.arguments?.getString("value")?.toInt() ?: 0
            //viewModel.updateSelectedWork(folkWorkId)
            DetailScreen(
                folkWorkId = folkWorkId,
                //logic = logic,
                //isPuzzleType = uiState.folkWorkType == FolkWorkType.Puzzle,
                //isStoryType = uiState.folkWorkType == FolkWorkType.Story,
                isExpandedScreen = windowSize == WindowWidthSizeClass.Expanded,
                onDetailScreenBackClick = { navController.navigate("home") },
                modifier = modifier,
            )
        }
    }
}
