package com.viktoriagavrosh.fairytales.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.data.FolkWorkType
import com.viktoriagavrosh.fairytales.model.FolkWork
import com.viktoriagavrosh.fairytales.ui.FairyTalesUiState
import com.viktoriagavrosh.fairytales.ui.elements.GridFolkWorks
import com.viktoriagavrosh.fairytales.ui.elements.ListFolkWorks
import com.viktoriagavrosh.fairytales.ui.elements.bars.ContentTopBar
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme
import com.viktoriagavrosh.fairytales.ui.utils.UILogic

/**
 * Composable to display [FolkWork] list or grid screen
 */
@Composable
fun HomeScreen(
    logic: UILogic,
    uiState: FairyTalesUiState,
    isExpandedScreen: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = if (isExpandedScreen) {
            modifier.padding(end = dimensionResource(id = R.dimen.right_padding_horizontal_screen))
        } else {
            modifier
        }
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
            onTopBarHeartClick = logic.onTopBarHeartClick
        )
        if (isExpandedScreen) {
            GridFolkWorks(
                folkWorks = uiState.folkWorks,
                isBlurImage = uiState.folkWorkType == FolkWorkType.Puzzle,
                onCardClick = logic.onCardClick,
                onHeartClick = logic.onHeartClick,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            ListFolkWorks(
                folkWorks = uiState.folkWorks,
                isBlurImage = uiState.folkWorkType == FolkWorkType.Puzzle,
                onCardClick = logic.onCardClick,
                onHeartClick = logic.onHeartClick,
                modifier = Modifier
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