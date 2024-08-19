package com.viktoriagavrosh.fairytales.ui.detailscreen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.model.TaleUi
import com.viktoriagavrosh.fairytales.ui.elements.TaleImage
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme

/**
 * Composable to display details of selected tale on expanded screen
 */
@Composable
fun HorizontalDetailScreen(
    tale: TaleUi,
    fontSize: Float,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        if (tale.genre != "puzzle") {      // TODO change String to enum
            ImageHorizontal(
                // TODO may by merge all Image Composable ?
                title = tale.title,
                imageUrl = tale.imageUrl ?: "",  // TODO do something with it
            )
        }
        Row {    // TODO extract to fun ?
            Spacer(
                modifier = if (tale.genre == "story") {
                    Modifier.width(dimensionResource(id = R.dimen.padding_small))
                } else {
                    Modifier.weight(1F)
                }
            )
            TextDetail(
                text = tale.text,
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
        if (tale.genre == "puzzle") {
            AnswerHorizontal(    // TODO merge all answers ?
                answer = tale.answer ?: "",  // TODO do something with it
                imageUrl = tale.imageUrl ?: "",  // TODO do something with it
                modifier = Modifier
                    .padding(bottom = dimensionResource(id = R.dimen.padding_small))
            )
        }
    }
}

@Composable
private fun ImageHorizontal(
    title: String,
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {   // TODO try verticalAlignment instead Spacers
        Spacer(modifier = Modifier.weight(1F))
        TaleImage(
            title = title,
            imageUrl = imageUrl,
            modifier = Modifier
                .weight(2F)
                .padding(top = dimensionResource(id = R.dimen.padding_small))
        )
        Spacer(modifier = Modifier.weight(1F))
    }
}

@Composable
private fun AnswerHorizontal(
    // TODO maybe treatment answer: String? (= null)  here ???
    answer: String,
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    var bigCard by remember {
        mutableStateOf(false)
    }
    if (bigCard) {   // TODO extract 2 funs
        Row {          // TODO try verticalAlignment instead Spacers
            Spacer(modifier = Modifier.weight(1F))
            Answer(
                answer = answer,
                imageUrl = imageUrl,
                isBigImage = false,
                modifier = modifier   // TODO maybe Modifier, but modifier up to row ?
                    .weight(2F)
                    .padding(dimensionResource(id = R.dimen.padding_small))
            )
            Spacer(modifier = Modifier.weight(1F))
        }
    } else {
        Button(
            onClick = { bigCard = true },
            modifier = modifier
                .padding(dimensionResource(id = R.dimen.padding_small)),
        ) {
            Text(
                text = stringResource(id = R.string.answer_button),
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HorizontalDetailScreenPreview() {
    FairyTalesTheme {
        HorizontalDetailScreen(
            tale = TaleUi(text = "text"),
            fontSize = 24.0F,
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PuzzleHorizontalDetailScreenPreview() {
    FairyTalesTheme {
        HorizontalDetailScreen(
            tale = TaleUi(
                genre = "puzzle",
                text = "text",
                answer = "answer",
            ),
            fontSize = 24.0F,
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun StoryHorizontalDetailScreenPreview() {
    FairyTalesTheme {
        HorizontalDetailScreen(
            tale = TaleUi(
                genre = "story",
                text = "text",
            ),
            fontSize = 24.0F,
        )
    }
}
