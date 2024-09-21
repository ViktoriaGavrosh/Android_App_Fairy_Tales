package com.viktoriagavrosh.details.elements

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
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
 * Composable to display details of selected [ReadBook] on compact and medium screens
 */
@Composable
internal fun VerticalReadContent(
    book: ReadBook,
    textSize: Float,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        BookImage(
            title = book.title,
            imageUrl = book.imageUrl ?: "",
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = dimensionResource(id = R.dimen.padding_extra_large),
                    top = dimensionResource(id = R.dimen.padding_large),
                    end = dimensionResource(id = R.dimen.padding_extra_large),
                    bottom = dimensionResource(id = R.dimen.padding_small)
                ),
            isBlur = true,
        )
        TextRow(
            text = book.text,
            title = book.title,
            textSize = textSize,
            isNotFullScreen = book.genre is ShelfGenre.Folks,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}


/*
var bigCard by remember {
    mutableStateOf(false)
}
Column(
    modifier = modifier,
    verticalArrangement = Arrangement.Center
) {
    if (book.genre != "puzzle") {
        TaleImage(
            title = book.title,
            imageUri = book.imageUri ?: "",
            isBlur = false,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = dimensionResource(id = R.dimen.padding_extra_large),
                    top = dimensionResource(id = R.dimen.padding_large),
                    end = dimensionResource(id = R.dimen.padding_extra_large),
                    bottom = dimensionResource(id = R.dimen.padding_small)
                )
        )
    }
    Row(modifier = Modifier.fillMaxWidth()) {
        if (book.genre != "story") {
            Spacer(modifier = Modifier.weight(1F))
            TextDetail(
                tale = book,
                fontSize = fontSize,
            )
            Spacer(modifier = Modifier.weight(1F))
        } else {
            TextDetail(
                tale = book,
                fontSize = fontSize,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
    if (book.genre == "puzzle") {
        if (bigCard) {
            Answer(
                answer = book.answer ?: "",
                imageUri = book.imageUri ?: "",
                isBigImage = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(id = R.dimen.padding_medium))
            )
        } else {
            Button(
                onClick = { bigCard = true },
                modifier = Modifier
                    .padding(top = dimensionResource(id = R.dimen.padding_small))
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = stringResource(R.string.answer_button),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Composable
internal fun Answer(
answer: String,
imageUri: String,
isBigImage: Boolean,
modifier: Modifier = Modifier
) {
Column(
    modifier = modifier
) {
    TaleImage(
        title = answer,
        imageUrl = imageUri,
        modifier = if (isBigImage) {
            Modifier
                .fillMaxWidth()
                .padding(
                    start = dimensionResource(id = R.dimen.padding_extra_large),
                    top = dimensionResource(id = R.dimen.padding_large),
                    end = dimensionResource(id = R.dimen.padding_extra_large),
                    bottom = dimensionResource(id = R.dimen.padding_small)
                )
        } else {
            Modifier
        }
    )
    Text(
        text = answer,
        style = MaterialTheme.typography.displaySmall,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}
}

 */

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalReadContentPreview() {
    FairyTalesTheme {
        VerticalReadContent(
            book = ReadBook(
                title = "Title",
                text = "Text",
                genre = ShelfGenre.Nights,
            ),
            textSize = 24.0F,
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun FullScreenVerticalReadContentPreview() {
    FairyTalesTheme {
        VerticalReadContent(
            book = ReadBook(
                title = "Title",
                text = "Text",
                genre = ShelfGenre.Folks.Poem,
            ),
            textSize = 24.0F,
        )
    }
}
