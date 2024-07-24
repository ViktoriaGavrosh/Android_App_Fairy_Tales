package com.viktoriagavrosh.home.elements

import android.content.res.Configuration
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
import com.viktoriagavrosh.home.R
import com.viktoriagavrosh.home.model.TaleUiHome
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display a list of [TaleUiHome]-s on compact and medium screens
 */
@Composable
internal fun ListTales(
    tales: List<TaleUiHome>,
    onCardClick: (TaleUiHome) -> Unit,
    onHeartClick: (TaleUiHome) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        contentPadding = contentPadding,
        modifier = modifier
    ) {
        items(tales) { tale ->
            TaleCard(
                tale = tale,
                onCardClick = onCardClick,
                onHeartClick = onHeartClick,
                modifier = Modifier
                    .padding(
                        dimensionResource(id = R.dimen.padding_medium)
                    )
                    .testTag(tale.id.toString())
            )
        }
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ListTalesPreview() {
    FairyTalesTheme {
        ListTales(
            tales = List(4) {
                TaleUiHome(
                    title = "Story"
                )
            },
            onCardClick = {},
            onHeartClick = {}
        )
    }
}


@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PuzzleListTalesPreview() {
    FairyTalesTheme {
        ListTales(
            tales = List(4) {
                TaleUiHome(
                    title = "Story"
                )
            },
            onCardClick = {},
            onHeartClick = {}
        )
    }
}
