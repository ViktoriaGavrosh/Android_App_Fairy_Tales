package com.viktoriagavrosh.fairytales.ui.screens.detailscreens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.fairytales.model.FolkWork
import com.viktoriagavrosh.fairytales.ui.elements.FolkWorkImage
import com.viktoriagavrosh.fairytales.ui.elements.bars.ContentTopBar
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme
import com.viktoriagavrosh.fairytales.ui.utils.MockData
import com.viktoriagavrosh.fairytales.ui.utils.UILogic

@Composable
fun DetailScreen(
    folkWork: FolkWork,
    logic: UILogic,
    isPuzzleType: Boolean,
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
            text = folkWork.title,
            isShowHomeScreen = false,
            onDetailScreenBackClick = logic.onDetailScreenBackClick,
            isFavoriteWorks = false,
            onTopBarHeartClicked = logic.onTopBarHeartClicked
        )
        if (isExpandedScreen) {
            HorizontalDetailScreen(
                folkWork = folkWork,
                isPuzzleType = isPuzzleType,
                modifier = Modifier
            )
        } else {
            VerticalDetailScreen(
                folkWork = folkWork,
                isPuzzleType = isPuzzleType,
                modifier = Modifier.fillMaxHeight()
            )
        }
    }
}

@Composable
fun Answer(
    selectedWork: FolkWork,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        FolkWorkImage(
            title = selectedWork.answer ?: "",
            imageUri = selectedWork.imageUri ?: "",
            isBlur = false
        )
        Text(
            text = selectedWork.answer ?: "",               // TODO my обработать по-другому
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun DetailScreenPreview() {
    FairyTalesTheme {
        DetailScreen(
            isPuzzleType = true,
            folkWork = MockData.fakeFolkWork,
            logic = UILogic(),
            isExpandedScreen = false
        )
    }
}

@Preview(widthDp = 1000)
@Composable
fun HorizontalDetailScreenPreview() {
    FairyTalesTheme {
        DetailScreen(
            isPuzzleType = true,
            folkWork = MockData.fakeFolkWork,
            logic = UILogic(),
            isExpandedScreen = true
        )
    }
}