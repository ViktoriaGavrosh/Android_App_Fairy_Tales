package com.viktoriagavrosh.fairytales.ui

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.viktoriagavrosh.fairytales.data.FolkWorkType
import com.viktoriagavrosh.fairytales.ui.screens.ContentScreen
import com.viktoriagavrosh.fairytales.ui.screens.detailscreens.DetailScreen
import com.viktoriagavrosh.fairytales.ui.utils.UILogic

/**
 * Top level composable that represents screens for the application
 */
@Composable
fun FairyTalesApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier
) {
    val viewModel: FairyTalesViewModel = viewModel(factory = FairyTalesViewModel.factory)
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val logic = UILogic(
        onTabClick = viewModel::updateCompositionType,
        onCardClick = viewModel::navigateToDetailScreen,
        onDetailScreenBackClick = viewModel::navigateToHomeScreen,
        onHeartClick = viewModel::updateWorkFavorite,
        onTopBarHeartClick = viewModel::updateListFavoriteWorks
    )

    if (uiState.isShowHomeScreen) {
        ContentScreen(
            uiState = uiState,
            logic = logic,
            windowSize = windowSize,
            modifier = modifier
        )
    } else {
        DetailScreen(
            folkWork = uiState.selectedWork,
            logic = logic,
            isPuzzleType = uiState.folkWorkType == FolkWorkType.Puzzle,
            isStoryType = uiState.folkWorkType == FolkWorkType.Story,
            isExpandedScreen = windowSize == WindowWidthSizeClass.Expanded,
            modifier = modifier
        )
    }
}

@Preview
@Composable
fun FairyTalesAppPreview() {
    FairyTalesApp(
        windowSize = WindowWidthSizeClass.Compact
    )
}
