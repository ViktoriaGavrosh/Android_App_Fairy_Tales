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
import com.viktoriagavrosh.fairytales.model.FolkWork
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme
import com.viktoriagavrosh.fairytales.ui.utils.MockData

@Composable
fun GridFolkWorks(
    folkWorks: List<FolkWork>,
    isBlurImage: Boolean,
    onCardClick: (FolkWork) -> Unit,
    onHeartClick: (FolkWork) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = contentPadding,
        modifier = modifier
    ) {
        items(folkWorks) { folkWork ->
            FolkWorkCard(
                isBlurImage = isBlurImage,
                folkWork = folkWork,
                onCardClick = onCardClick,
                onHeartClick = onHeartClick,
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
    FairyTalesTheme {
        GridFolkWorks(
            isBlurImage = false,
            folkWorks = List(5) {MockData.fakeFolkWork},
            onCardClick = {},
            onHeartClick = {}
        )
    }
}