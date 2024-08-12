package com.viktoriagavrosh.fairytales.ui.screens.detailscreens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.model.FolkWork
import com.viktoriagavrosh.fairytales.ui.elements.bars.ContentTopBar
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme
import com.viktoriagavrosh.fairytales.ui.utils.MockData
import com.viktoriagavrosh.fairytales.ui.utils.UILogic

/**
 * Composable to display details of selected [FolkWork]
 */
@Composable
fun DetailScreen(
    tale: FolkWork,
    logic: UILogic,
    isPuzzleType: Boolean,
    isStoryType: Boolean,
    modifier: Modifier = Modifier,
    isExpandedScreen: Boolean
) {
    BackHandler {
        logic.onDetailScreenBackClick()
    }
    Column(
        modifier = modifier
    ) {
        ContentTopBar(
            text = tale.title,
            isShowHomeScreen = false,
            onDetailScreenBackClick = logic.onDetailScreenBackClick,
            isFavoriteTales = false,
            onTopBarHeartClick = logic.onTopBarHeartClick
        )
        if (isExpandedScreen) {
            HorizontalDetailScreen(
                tale = tale,
                isPuzzleType = isPuzzleType,
                isStoryType = isStoryType,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = dimensionResource(id = R.dimen.right_padding_horizontal_screen))
                    .testTag(stringResource(R.string.horizontal_detail_screen))
            )
        } else {
            VerticalDetailScreen(
                tale = tale,
                isPuzzleType = isPuzzleType,
                modifier = Modifier
                    .fillMaxHeight()
                    .testTag(stringResource(R.string.vertical_detail_screen))
            )
        }
    }
}

@Preview
@Composable
fun DetailScreenPreview() {
    FairyTalesTheme {
        DetailScreen(
            isPuzzleType = true,
            isStoryType = false,
            tale = MockData.fakeTale,
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
            tale = MockData.fakeTale,
            logic = UILogic(),
            isExpandedScreen = true
        )
    }
}
