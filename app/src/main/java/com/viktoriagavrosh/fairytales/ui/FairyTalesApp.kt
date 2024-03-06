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
import com.viktoriagavrosh.fairytales.ui.screens.DetailScreen
import com.viktoriagavrosh.fairytales.ui.utils.UILogic


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
        onHeartClicked = viewModel::updateWorkFavorite,
        onTopBarHeartClicked = viewModel::updateListFavoriteWorks
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
            uiState = uiState,
            logic = logic,
            isPuzzleType = uiState.folkWorkType == FolkWorkType.Puzzle,
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
