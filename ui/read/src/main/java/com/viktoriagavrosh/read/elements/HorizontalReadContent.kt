package com.viktoriagavrosh.read.elements

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.read.R
import com.viktoriagavrosh.read.model.ReadBook
import com.viktoriagavrosh.repositories.utils.ShelfGenre
import com.viktoriagavrosh.uikit.BookImage
import com.viktoriagavrosh.uikit.TextRow
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display details of selected [ReadBook] on expanded screen
 */
@Composable
internal fun HorizontalReadContent(
    book: ReadBook,
    textSize: Float,
    modifier: Modifier = Modifier
) {
    if (book.genre is ShelfGenre.Folks) {
        FolkContent(
            book = book,
            textSize = textSize,
            modifier = modifier
        )
    } else {
        TaleContent(
            book = book,
            textSize = textSize,
            modifier = modifier
        )
    }
}

@Composable
private fun FolkContent(
    book: ReadBook,
    textSize: Float,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            BookImage(
                title = book.title,
                imageUrl = book.imageUrl ?: "",
                modifier = Modifier
                    .weight(1F)
                    .padding(vertical = dimensionResource(id = R.dimen.padding_small))
                    .padding(horizontal = dimensionResource(R.dimen.padding_double_extra_large)),
            )
            TextRow(
                text = book.text,
                title = book.title,
                textSize = textSize,
                isNotFullScreen = book.genre is ShelfGenre.Folks,
                modifier = Modifier
                    .padding(
                        horizontal = dimensionResource(R.dimen.padding_small)
                    )
                    .weight(1F)
            )
        }
    }
}

@Composable
private fun TaleContent(
    book: ReadBook,
    textSize: Float,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .padding(top = dimensionResource(id = R.dimen.padding_small))
                    .weight(1F),
                contentAlignment = Alignment.Center
            ) {
                BookImage(
                    title = book.title,
                    imageUrl = book.imageUrl ?: "",
                    modifier = Modifier
                        .width(dimensionResource(R.dimen.image_width)),
                )
            }
            TextRow(
                text = book.text,
                title = book.title,
                textSize = textSize,
                isNotFullScreen = true,
                modifier = Modifier
                    .padding(
                        horizontal = dimensionResource(R.dimen.padding_small)
                    )
                    .weight(1F)
            )
        }
        TextRow(
            text = book.text,
            title = book.title,
            textSize = textSize,
            isNotFullScreen = book.genre is ShelfGenre.Folks,
            modifier = Modifier.padding(
                horizontal = dimensionResource(R.dimen.padding_small)
            )
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HorizontalReadContentPreview() {
    FairyTalesTheme {
        HorizontalReadContent(
            book = ReadBook(
                text = "Text",
                title = "Title",
                genre = ShelfGenre.Nights,
            ),
            textSize = 24.0F,
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun FullScreenHorizontalReadContentPreview() {
    FairyTalesTheme {
        HorizontalReadContent(
            book = ReadBook(
                text = "Text",
                title = "Title",
                genre = ShelfGenre.Folks.Poem,
            ),
            textSize = 24.0F,
        )
    }
}
