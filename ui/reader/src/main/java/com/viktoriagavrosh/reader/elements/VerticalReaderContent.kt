package com.viktoriagavrosh.reader.elements

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.reader.model.ReadBook
import com.viktoriagavrosh.repositories.utils.ShelfGenre
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uikit.image.BookImageRow
import com.viktoriagavrosh.uikit.text.TextRow
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display details of selected [ReadBook] on compact and medium screens
 */
@Composable
internal fun VerticalReaderContent(
    bookProvider: () -> ReadBook,
    textSizeProvider: () -> Float,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val book = bookProvider()
    Column(
        modifier = modifier.verticalScroll(scrollState),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BookImageRow(
            title = book.title,
            imageUrl = book.imageUrl ?: "",
            modifier = Modifier
                .padding(
                    top = dimensionResource(id = R.dimen.padding_large),
                    bottom = dimensionResource(id = R.dimen.padding_small)
                ),
        )
        TextRow(
            text = book.text,
            title = book.title,
            textSizeProvider = textSizeProvider,
            isTitleShow = book.genre !is ShelfGenre.Folks,
            isNotFullScreen = book.genre is ShelfGenre.Folks,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalReadContentPreview() {
    FairyTalesTheme {
        VerticalReaderContent(
            bookProvider = {
                ReadBook(
                    title = "Title",
                    text = "Text",
                    genre = ShelfGenre.Nights,
                )
            },
            textSizeProvider = { 24.0F },
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun FullScreenVerticalReadContentPreview() {
    FairyTalesTheme {
        VerticalReaderContent(
            bookProvider = {
                ReadBook(
                    title = "Title",
                    text = "Text",
                    genre = ShelfGenre.Folks.Poem,
                )
            },
            textSizeProvider = { 24.0F },
            modifier = Modifier.fillMaxSize(),
        )
    }
}
