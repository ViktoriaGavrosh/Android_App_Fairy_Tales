package com.viktoriagavrosh.fairytales.ui.detailscreen

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.model.TaleUi
import com.viktoriagavrosh.fairytales.ui.elements.TaleImage
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme

/**
 * Composable to display details of selected tale on compact and medium screens
 */
@Composable
fun VerticalDetailScreen(
    tale: TaleUi,
    fontSize: Float,
    modifier: Modifier = Modifier,
) {
    var bigCard by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
    ) {     // TODO extract a few funs
        if (tale.genre != "puzzle") {
            TaleImage(
                title = tale.title,
                imageUrl = tale.imageUrl ?: "",    // TODO do something with it
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = dimensionResource(id = R.dimen.padding_extra_large),
                        top = dimensionResource(id = R.dimen.padding_large),
                        end = dimensionResource(id = R.dimen.padding_extra_large),
                        bottom = dimensionResource(id = R.dimen.padding_small),
                    )

            )
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            if (tale.genre != "story") {
                Spacer(modifier = Modifier.weight(1F)) // TODO try verticalAlignment instead Spacers
                TextDetail(
                    text = tale.text,
                    fontSize = fontSize,
                )
                Spacer(modifier = Modifier.weight(1F))
            } else {
                TextDetail(
                    text = tale.text,
                    fontSize = fontSize,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        if (tale.genre == "puzzle") {
            if (bigCard) {
                Answer(
                    answer = tale.answer ?: "",  // TODO do something with it
                    imageUrl = tale.imageUrl ?: "",
                    isBigImage = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = dimensionResource(id = R.dimen.padding_medium)),
                )
            } else {
                Button(
                    onClick = { bigCard = true },
                    modifier = Modifier
                        .padding(top = dimensionResource(id = R.dimen.padding_small))
                        .align(Alignment.CenterHorizontally),
                ) {
                    Text(
                        text = stringResource(id = R.string.answer_button),
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
        }
    }
}

/**
 * Composable to display tale`s text
 */
@Composable
fun TextDetail(
    text: String,
    fontSize: Float,
    modifier: Modifier = Modifier,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
        ),
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.padding_small)),
    ) {
        Text(
            text = text,
            fontSize = fontSize.sp,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_small))
                .animateContentSize(),
        )
    }
}

/**
 * Composable to display answer of puzzle
 */
@Composable
fun Answer(
    answer: String,
    imageUrl: String,
    isBigImage: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        TaleImage(
            title = answer,
            imageUrl = imageUrl,
            modifier = if (isBigImage) {
                Modifier
                    .fillMaxWidth()
                    .padding(
                        start = dimensionResource(id = R.dimen.padding_extra_large),
                        top = dimensionResource(id = R.dimen.padding_large),
                        end = dimensionResource(id = R.dimen.padding_extra_large),
                        bottom = dimensionResource(id = R.dimen.padding_small),
                    )
            } else Modifier
        )
        Text(
            text = answer,
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalDetailScreenPreview() {
    FairyTalesTheme {
        VerticalDetailScreen(
            tale = TaleUi(
                text = "Text",
            ),
            fontSize = 24.0F,
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun StoryVerticalDetailScreenPreview() {
    FairyTalesTheme {
        VerticalDetailScreen(
            tale = TaleUi(
                genre = "story",
                text = "Text",
            ),
            fontSize = 24.0F,
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PuzzleVerticalDetailScreenPreview() {
    FairyTalesTheme {
        VerticalDetailScreen(
            tale = TaleUi(
                genre = "puzzle",
                text = "Text",
                answer = "answer",
            ),
            fontSize = 24.0F,
        )
    }
}
