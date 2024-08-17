package com.viktoriagavrosh.fairytales.ui.elements

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
import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.model.TaleUi
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme

/**
 * Composable to display grid of tales
 */
@Composable
fun GridTales(
    tales: List<TaleUi>,
    onCardClick: (TaleUi) -> Unit,
    onHeartClick: (TaleUi) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    LazyVerticalGrid(
        columns = GridCells
            .Adaptive(minSize = dimensionResource(id = R.dimen.grid_card_min_size)),
        contentPadding = contentPadding,
        modifier = modifier,
    ) {
        items(tales) {tale ->
            TaleCard(
                tale = tale,
                minLineText = 2,    // TODO check it !
                onCardClick = onCardClick,
                onHeartClick = onHeartClick,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium)),
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
                TaleUi(title = "Story")
            },
            onCardClick = {},
            onHeartClick = {},
        )
    }
}
