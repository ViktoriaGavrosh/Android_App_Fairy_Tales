package com.viktoriagavrosh.fairytales.ui.elements

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.data.FolkWorkType
import com.viktoriagavrosh.fairytales.model.FolkWork
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme

@Composable
fun GridFolkWorks(
    folkWorks: List<FolkWork>,
    currentFolkWorkType: FolkWorkType,
    selectedWork: FolkWork,
    onCardClick: (FolkWork) -> Unit,
    onHeartClicked: (FolkWork, FolkWorkType) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = contentPadding,
        modifier = modifier
    ) {
        items(folkWorks) { folkWork ->
            CardComposition(
                currentFolkWorkType = currentFolkWorkType,
                selectedWork = selectedWork,
                folkWork = folkWork,
                onCardClick = onCardClick,
                onHeartClicked = onHeartClicked,
                modifier = Modifier.padding(
                    dimensionResource(id = R.dimen.padding_medium)
                )
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun GridFolkWorksPreview() {

    val fakeFolkWork = FolkWork(
        id = 0,
        genre = "story",
        title = "Story",
        text = "Story",
        answer = null,
        imageUri = null,
        audioUri = null,
        isFavorite = false
    )
    FairyTalesTheme {
        GridFolkWorks(
            currentFolkWorkType = FolkWorkType.Story,
            selectedWork = fakeFolkWork,
            folkWorks = List(5) {fakeFolkWork},
            onCardClick = {},
            onHeartClicked = { _, _ -> }
        )
    }
}