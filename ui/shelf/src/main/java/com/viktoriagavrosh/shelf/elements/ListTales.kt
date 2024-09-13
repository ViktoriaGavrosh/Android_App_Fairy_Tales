package com.viktoriagavrosh.shelf.elements

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.viktoriagavrosh.shelf.model.Book
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display a list of [Book]-s on compact and medium screens
 */
@Composable
internal fun ListTales(
    tales: List<Book>,
    onCardClick: (Book) -> Unit,
    onHeartClick: (Book) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        contentPadding = contentPadding,
        modifier = modifier
    ) {
        items(tales) { tale ->
            /* TODO 111
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

             */
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
                Book(
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
                Book(
                    title = "Story"
                )
            },
            onCardClick = {},
            onHeartClick = {}
        )
    }
}
