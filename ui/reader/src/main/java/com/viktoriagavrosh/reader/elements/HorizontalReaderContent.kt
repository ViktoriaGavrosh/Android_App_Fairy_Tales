package com.viktoriagavrosh.reader.elements

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.reader.model.ReadBook
import com.viktoriagavrosh.repositories.utils.ShelfGenre
import com.viktoriagavrosh.uikit.BookImage
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uikit.TextRow
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display details of selected [ReadBook] on expanded screen
 */
@Composable
internal fun HorizontalReaderContent(
    bookProvider: () -> ReadBook,
    textSizeProvider: () -> Float,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    if (bookProvider().genre is ShelfGenre.Folks) {
        FolkContent(
            bookProvider = bookProvider,
            textSizeProvider = textSizeProvider,
            modifier = modifier.verticalScroll(scrollState)
        )
    } else {
        TaleContent(
            bookProvider = bookProvider,
            textSizeProvider = textSizeProvider,
            modifier = modifier.verticalScroll(scrollState)
        )
    }
}

@Composable
private fun FolkContent(
    bookProvider: () -> ReadBook,
    textSizeProvider: () -> Float,
    modifier: Modifier = Modifier,
) {
    val book = bookProvider()

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
                textSizeProvider = textSizeProvider,
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
    bookProvider: () -> ReadBook,
    textSizeProvider: () -> Float,
    modifier: Modifier = Modifier,
) {
    val book = bookProvider()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier,
    ) {
        BookImage(
            title = book.title,
            imageUrl = book.imageUrl ?: "",
            modifier = Modifier
                .width(dimensionResource(R.dimen.image_width))
                .padding(top = dimensionResource(id = R.dimen.padding_small)),
        )
        TextRow(
            text = book.text,
            title = book.title,
            textSizeProvider = textSizeProvider,
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
        HorizontalReaderContent(
            bookProvider = {
                ReadBook(
                    text = "Text",
                    title = "Title",
                    genre = ShelfGenre.Nights,
                )
            },
            textSizeProvider = { 24.0F },
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun FullScreenHorizontalReadContentPreview() {
    FairyTalesTheme {
        HorizontalReaderContent(
            bookProvider = {
                ReadBook(
                    text = "Text",
                    title = "Title",
                    genre = ShelfGenre.Folks.Poem,
                )
            },
            textSizeProvider = { 24.0F },
        )
    }
}
