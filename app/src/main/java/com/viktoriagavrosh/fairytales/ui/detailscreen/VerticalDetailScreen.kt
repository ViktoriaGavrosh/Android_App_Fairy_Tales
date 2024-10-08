package com.viktoriagavrosh.fairytales.ui.detailscreen

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.model.TaleUi
import com.viktoriagavrosh.fairytales.ui.elements.Answer
import com.viktoriagavrosh.fairytales.ui.elements.Genre
import com.viktoriagavrosh.fairytales.ui.elements.TaleImage
import com.viktoriagavrosh.fairytales.ui.elements.TaleText
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
    ) {

        if (tale.genre != Genre.Puzzle) {
            TaleImage(
                title = tale.title,
                imageUrl = tale.imageUrl,
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
        TaleText(
            tale = tale,
            fontSize = fontSize,
            modifier = Modifier.fillMaxWidth()
        )
        if (tale.genre == Genre.Puzzle) {
            if (bigCard) {
                Answer(
                    answer = tale.answer,
                    imageUrl = tale.imageUrl,
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
    tale: TaleUi,
    fontSize: Float,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.padding_small))
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.corner)))
            .background(color = MaterialTheme.colorScheme.onPrimary),
    ) {
        if (tale.genre == Genre.Story) {
            Text(
                text = tale.title,
                style = MaterialTheme.typography.displaySmall,
                fontSize = fontSize.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small)),
            )
        }
        Text(
            text = tale.text,
            fontSize = fontSize.sp,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_small))
                .animateContentSize(),
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
                title = "Title",
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
                title = "Title",
                genre = Genre.Story,
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
                title = "Title",
                genre = Genre.Puzzle,
                text = "Text",
                answer = "answer",
            ),
            fontSize = 24.0F,
        )
    }
}
