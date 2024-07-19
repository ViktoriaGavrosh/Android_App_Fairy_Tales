package com.viktoriagavrosh.home.uiscreens.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.viktoriagavrosh.fairytales.model.FolkWork
import com.viktoriagavrosh.home.R
import com.viktoriagavrosh.home.uiscreens.FairyTalesUiState
import com.viktoriagavrosh.home.uiscreens.FolkWorkType
import com.viktoriagavrosh.home.uiscreens.elements.GridFolkWorks
import com.viktoriagavrosh.home.uiscreens.elements.ListFolkWorks
import com.viktoriagavrosh.home.uiscreens.elements.bars.ContentTopBar
import com.viktoriagavrosh.home.uiscreens.utils.UILogic

/**
 * Composable to display [FolkWork] list or grid screen
 */
@Composable
internal fun ContentScreen(
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
            ContentScreen(
                logic = ,
                uiState = ,
                isExpandedScreen = ,
                onCardClick =
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
