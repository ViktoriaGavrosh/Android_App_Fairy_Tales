package com.viktoriagavrosh.fairytales.ui.screens

import android.app.Activity
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.fairytales.data.FolkWorkType
import com.viktoriagavrosh.fairytales.model.FolkWork
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme
import com.viktoriagavrosh.fairytales.ui.utils.FairyTalesContentType
import com.viktoriagavrosh.fairytales.ui.utils.FairyTalesNavigationType

@Composable
fun ListAndDetailScreen(
    folkWorks: List<FolkWork>,
    currentFolkWorkType: FolkWorkType,
    selectedWork: FolkWork,
    navigationType: FairyTalesNavigationType,
    contentType: FairyTalesContentType,
    onCardClick: (FolkWork) -> Unit,
    onHeartClicked: (FolkWork, FolkWorkType) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        ListCompositionsScreen(
            folkWorks = folkWorks,
            currentFolkWorkType = currentFolkWorkType,
            selectedWork = selectedWork,
            navigationType = navigationType,
            onCardClick = onCardClick,
            onHeartClicked = onHeartClicked,
            modifier = Modifier.weight(1F)
        )
        val activity = LocalContext.current as Activity
        DetailScreen(
            currentFolkWorkType = currentFolkWorkType,
            selectedWork = selectedWork,
            contentType = contentType,
            onDetailScreenBackClick = { activity.finish() },
            modifier = Modifier.weight(1F)
        )
    }
}

@Preview(widthDp = 1000)
@Composable
fun ListAndDetailScreenPreview() {
    FairyTalesTheme {
        ListAndDetailScreen(
            folkWorks = emptyList(),
            currentFolkWorkType = FolkWorkType.Story,
            selectedWork = FolkWork(
                id = 0,
                genre = "story",
                title = "Story",
                text = "Story",
                answer = null,
                imageUri = null,
                audioUri = null,
                isFavorite = false
            ),
            navigationType = FairyTalesNavigationType.PERMANENT_NAVIGATION_DRAWER,
            contentType = FairyTalesContentType.LIST_AND_DETAILS,
            onCardClick = {},
            onHeartClicked = {_,_ ->}
        )
    }
}