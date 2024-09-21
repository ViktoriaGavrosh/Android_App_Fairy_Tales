package com.viktoriagavrosh.details.elements

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.details.R
import com.viktoriagavrosh.details.model.ReadBook
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
                modifier = Modifier.padding(
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
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),          // TODO 111
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            BookImage(
                title = book.title,
                imageUrl = book.imageUrl ?: "",
                modifier = Modifier
                    .weight(1F)
                    .padding(top = dimensionResource(id = R.dimen.padding_small))
                    .width(dimensionResource(R.dimen.image_width)),
            )
            TextRow(
                text = book.text,
                title = book.title,
                textSize = textSize,
                isNotFullScreen = true,
                modifier = Modifier.padding(
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

/*
Row {
    Spacer(
        modifier = if (tale.genre == "story") {
            Modifier.width(dimensionResource(id = R.dimen.padding_small))
        } else {
            Modifier.weight(1F)
        }
    )
    TextCard(
        tale = tale,
        fontSize = fontSize,
        modifier = Modifier
            .weight(3F)
            .padding(dimensionResource(id = R.dimen.padding_small))
    )
    Spacer(
        modifier = if (tale.genre == "story") {
            Modifier.width(dimensionResource(id = R.dimen.padding_small))
        } else {
            Modifier.weight(1F)
        }
    )
}
 */
/*
if (tale.genre == "puzzle") {
    AnswerHorizontal(
        answer = tale.answer ?: "",
        imageUri = tale.imageUri ?: "",
        modifier = Modifier
            .padding(bottom = dimensionResource(id = R.dimen.padding_small))
    )
}

 */
/*
@Composable
private fun AnswerHorizontal(
    answer: String,
    imageUri: String,
    modifier: Modifier = Modifier
) {
    var bigCard by remember {
        mutableStateOf(false)
    }
    if (bigCard) {
        Row {
            Spacer(modifier = Modifier.weight(1F))
            Answer(
                answer = answer,
                imageUri = imageUri,
                isBigImage = false,
                modifier = modifier
                    .weight(2F)
                    .padding(top = dimensionResource(id = R.dimen.padding_small))
            )
            Spacer(modifier = Modifier.weight(1F))
        }
    } else {
        Button(
            onClick = { bigCard = true },
            modifier = modifier
                .padding(dimensionResource(id = R.dimen.padding_small))
        ) {
            Text(
                text = stringResource(R.string.answer_button),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

 */

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
