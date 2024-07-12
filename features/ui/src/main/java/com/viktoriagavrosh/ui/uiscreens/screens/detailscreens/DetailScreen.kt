package com.viktoriagavrosh.ui.uiscreens.screens.detailscreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.viktoriagavrosh.fairytales.model.FolkWork
import com.viktoriagavrosh.fairytales.ui.elements.bars.ContentTopBar
import com.viktoriagavrosh.fairytales.ui.screens.detailscreens.HorizontalDetailScreen
import com.viktoriagavrosh.fairytales.ui.screens.detailscreens.VerticalDetailScreen
import com.viktoriagavrosh.ui.R
import com.viktoriagavrosh.ui.uiscreens.utils.UILogic

/**
 * Composable to display details of selected [FolkWork]
 */
@Composable
fun DetailScreen(
    folkWork: FolkWork,
    logic: UILogic,
    isPuzzleType: Boolean,
    isStoryType: Boolean,
    modifier: Modifier = Modifier,
    onDetailScreenBackClick: () -> Unit,
    isExpandedScreen: Boolean
) {
    Column(
        modifier = modifier
    ) {
        ContentTopBar(
            text = folkWork.title,
            isShowHomeScreen = false,
            onDetailScreenBackClick = onDetailScreenBackClick,
            isFavoriteWorks = false,
            onTopBarHeartClick = logic.onTopBarHeartClick
        )
        if (isExpandedScreen) {
            HorizontalDetailScreen(
                folkWork = folkWork,
                isPuzzleType = isPuzzleType,
                isStoryType = isStoryType,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = dimensionResource(id = R.dimen.right_padding_horizontal_screen))
                    .testTag(stringResource(R.string.horizontal_detail_screen))
            )
        } else {
            VerticalDetailScreen(
                folkWork = folkWork,
                isPuzzleType = isPuzzleType,
                modifier = Modifier
                    .fillMaxHeight()
                    .testTag(stringResource(R.string.vertical_detail_screen))
            )
        }
    }
}
/*
@Preview
@Composable
fun DetailScreenPreview() {
    FairyTalesTheme {
        DetailScreen(
            isPuzzleType = true,
            isStoryType = false,
            folkWork = MockData.fakeFolkWork,
            logic = UILogic(),
            isExpandedScreen = false
        )
    }
}

@Preview(widthDp = 1000)
@Composable
fun DetailHorizontalScreenPreview() {
    FairyTalesTheme {
        DetailScreen(
            isPuzzleType = false,
            isStoryType = true,
            folkWork = MockData.fakeFolkWork,
            logic = UILogic(),
            isExpandedScreen = true
        )
    }
}


 */
