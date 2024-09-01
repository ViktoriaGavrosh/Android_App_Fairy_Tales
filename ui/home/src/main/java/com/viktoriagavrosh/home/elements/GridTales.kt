package com.viktoriagavrosh.home.elements

import android.content.res.Configuration
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
import com.viktoriagavrosh.home.R
import com.viktoriagavrosh.home.model.TaleUiHome
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display a grid of [TaleUiHome]-s on expanded screen
 */
@Composable
internal fun GridTales(
    tales: List<TaleUiHome>,
    onCardClick: (TaleUiHome) -> Unit,
    onHeartClick: (TaleUiHome) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyVerticalGrid(
        columns = GridCells
            .Adaptive(minSize = dimensionResource(id = R.dimen.card_min_size_in_grid)),
        contentPadding = contentPadding,
        modifier = modifier
    ) {
        items(tales) { tale ->
            TaleCard(
                tale = tale,
                minLineText = 2,
                onCardClick = onCardClick,
                onHeartClick = onHeartClick,
                modifier = Modifier.padding(
                    dimensionResource(id = R.dimen.padding_medium)
                )
            )
        }
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun GridTalesPreview() {
    FairyTalesTheme {
        GridTales(
            tales = List(5) {
                TaleUiHome(
                    title = "Story"
                )
            },
            onCardClick = {},
            onHeartClick = {}
        )
    }
}
