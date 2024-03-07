package com.viktoriagavrosh.fairytales.ui.elements

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.model.FolkWork

@Composable
fun ListFolkWorks(
    folkWorks: List<FolkWork>,
    isBlurImage: Boolean,
    onCardClick: (FolkWork) -> Unit,
    onHeartClicked: (FolkWork) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        contentPadding = contentPadding,
        modifier = modifier
    ) {
        items(folkWorks) { folkWork ->
            FolkWorkCard(
                isBlurImage = isBlurImage,
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

@Preview
@Composable
fun ListFolkWorksPreview() {
    ListFolkWorks(
        isBlurImage = true,
        folkWorks = emptyList(),
        onCardClick = {},
        onHeartClicked = {}
    )
}