package com.viktoriagavrosh.fairytales.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.fairytales.data.FolkWorkType
import com.viktoriagavrosh.fairytales.ui.FairyTalesUiState
import com.viktoriagavrosh.fairytales.ui.elements.GridFolkWorks
import com.viktoriagavrosh.fairytales.ui.elements.ListFolkWorks
import com.viktoriagavrosh.fairytales.ui.elements.bars.ContentTopBar
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme
import com.viktoriagavrosh.fairytales.ui.utils.UILogic

@Composable
fun HomeScreen(
    logic: UILogic,
    uiState: FairyTalesUiState,
    isExpandedScreen: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        ContentTopBar(
            text = if (uiState.isShowHomeScreen) {
                stringResource(id = uiState.folkWorkType.textId)
            } else {
                uiState.selectedWork.title
            },
            isShowHomeScreen = uiState.isShowHomeScreen,
            onDetailScreenBackClick = logic.onDetailScreenBackClick,
            isFavoriteWorks = uiState.isFavoriteWorks,
            onTopBarHeartClicked = logic.onTopBarHeartClicked
        )
        if (isExpandedScreen) {
            GridFolkWorks(
                folkWorks = uiState.folkWorks,
                isBlurImage = uiState.folkWorkType == FolkWorkType.Puzzle,
                selectedWork = uiState.selectedWork,
                onCardClick = logic.onCardClick,
                onHeartClicked = logic.onHeartClicked,
                modifier = modifier
            )
        } else {
            ListFolkWorks(
                folkWorks = uiState.folkWorks,
                isBlurImage = uiState.folkWorkType == FolkWorkType.Puzzle,
                selectedWork = uiState.selectedWork,
                onCardClick = logic.onCardClick,
                onHeartClicked = logic.onHeartClicked,
                modifier = modifier
            )
        }
    }
}

@Preview
@Composable
fun FairyTalesHomeScreenPreview() {
    FairyTalesTheme {
        val fakeLogic = UILogic()
        FairyTalesTheme {
            HomeScreen(
                logic = fakeLogic,
                uiState = FairyTalesUiState(),
                isExpandedScreen = false
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun FairyTalesHomeScreenTabletPreview() {
    val fakeLogic = UILogic()
    FairyTalesTheme {
        HomeScreen(
            logic = fakeLogic,
            uiState = FairyTalesUiState(),
            isExpandedScreen = true
        )
    }
}