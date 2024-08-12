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
import com.viktoriagavrosh.fairytales.data.TaleType
import com.viktoriagavrosh.fairytales.model.FolkWork
import com.viktoriagavrosh.fairytales.ui.TalesUiState
import com.viktoriagavrosh.fairytales.ui.elements.GridTales
import com.viktoriagavrosh.fairytales.ui.elements.ListTales
import com.viktoriagavrosh.fairytales.ui.elements.bars.ContentTopBar
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme
import com.viktoriagavrosh.fairytales.ui.utils.UILogic

/**
 * Composable to display [FolkWork] list or grid screen
 */
@Composable
fun HomeScreen(
    logic: UILogic,
    uiState: TalesUiState,
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
                stringResource(id = uiState.taleType.textId)
            } else {
                uiState.selectedTale.title
            },
            isShowHomeScreen = uiState.isShowHomeScreen,
            onDetailScreenBackClick = logic.onDetailScreenBackClick,
            isFavoriteTales = uiState.isFavoriteTales,
            onTopBarHeartClick = logic.onTopBarHeartClick
        )
        if (isExpandedScreen) {
            GridTales(
                tales = uiState.tale,
                isBlurImage = uiState.taleType == TaleType.Puzzle,
                onCardClick = logic.onCardClick,
                onHeartClick = logic.onHeartClick,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            ListTales(
                tales = uiState.tale,
                isBlurImage = uiState.taleType == TaleType.Puzzle,
                onCardClick = logic.onCardClick,
                onHeartClick = logic.onHeartClick,
                modifier = Modifier
            )
        }
    }
}

@Preview
@Composable
fun VerticalHomeScreenPreview() {
    FairyTalesTheme {
        val fakeLogic = UILogic()
        FairyTalesTheme {
            HomeScreen(
                logic = fakeLogic,
                uiState = TalesUiState(),
                isExpandedScreen = false
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun HorizontalHomeScreenPreview() {
    val fakeLogic = UILogic()
    FairyTalesTheme {
        HomeScreen(
            logic = fakeLogic,
            uiState = TalesUiState(),
            isExpandedScreen = true
        )
    }
}
