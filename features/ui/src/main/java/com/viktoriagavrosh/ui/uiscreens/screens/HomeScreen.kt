package com.viktoriagavrosh.ui.uiscreens.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.viktoriagavrosh.fairytales.model.FolkWork
import com.viktoriagavrosh.fairytales.ui.elements.GridFolkWorks
import com.viktoriagavrosh.fairytales.ui.elements.ListFolkWorks
import com.viktoriagavrosh.fairytales.ui.elements.bars.ContentTopBar
import com.viktoriagavrosh.ui.R
import com.viktoriagavrosh.ui.uiscreens.FairyTalesUiState
import com.viktoriagavrosh.ui.uiscreens.FolkWorkType
import com.viktoriagavrosh.ui.uiscreens.utils.UILogic

/**
 * Composable to display [FolkWork] list or grid screen
 */
@Composable
fun HomeScreen(
    logic: UILogic,
    uiState: FairyTalesUiState,
    isExpandedScreen: Boolean,
    onCardClick: (FolkWork) -> Unit,
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
            text = stringResource(id = uiState.folkWorkType.textId),
            isShowHomeScreen = true,
            onDetailScreenBackClick = {},
            isFavoriteWorks = uiState.isFavoriteWorks,
            onTopBarHeartClick = logic.onTopBarHeartClick
        )
        if (isExpandedScreen) {
            GridFolkWorks(
                folkWorks = uiState.folkWorks,
                isBlurImage = uiState.folkWorkType == FolkWorkType.Puzzle,
                onCardClick = onCardClick,
                onHeartClick = logic.onHeartClick,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            ListFolkWorks(
                folkWorks = uiState.folkWorks,
                isBlurImage = uiState.folkWorkType == FolkWorkType.Puzzle,
                onCardClick = onCardClick,
                onHeartClick = logic.onHeartClick,
                modifier = Modifier
            )
        }
    }
}
/*
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


 */
