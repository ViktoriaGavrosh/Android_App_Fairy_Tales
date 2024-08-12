package com.viktoriagavrosh.fairytales.ui.elements

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.model.FolkWork

/**
 * Composable to display a list of [FolkWork]-s on compact and medium screens
 */
@Composable
fun ListTales(
    tales: List<FolkWork>,
    isBlurImage: Boolean,
    onCardClick: (FolkWork) -> Unit,
    onHeartClick: (FolkWork) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        contentPadding = contentPadding,
        modifier = modifier
    ) {
        items(tales) { folkWork ->
            TaleCard(
                isBlurImage = isBlurImage,
                tale = folkWork,
                onCardClick = onCardClick,
                onHeartClick = onHeartClick,
                modifier = Modifier.padding(
                    dimensionResource(id = R.dimen.padding_medium)
                )
                    .testTag(folkWork.id.toString())
            )
        }
    }
}

@Preview
@Composable
fun ListTalesPreview() {
    ListTales(
        isBlurImage = true,
        tales = emptyList(),
        onCardClick = {},
        onHeartClick = {}
    )
}
