package com.viktoriagavrosh.details

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.viktoriagavrosh.details.model.TaleUiDetail
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display details of selected [TaleUiDetail] on compact and medium screens
 */
@Composable
internal fun VerticalDetailScreen(
    tale: TaleUiDetail,
    fontSize: Float,
    modifier: Modifier = Modifier
) {
    var bigCard by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        if (tale.genre != "puzzle") {
            TaleImage(
                title = tale.title,
                imageUri = tale.imageUri ?: "",
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
            if (tale.genre != "story") {
                Spacer(modifier = Modifier.weight(1F))
                TextDetail(
                    tale = tale,
                    fontSize = fontSize,
                )
                Spacer(modifier = Modifier.weight(1F))
            } else {
                TextDetail(
                    tale = tale,
                    fontSize = fontSize,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
        if (tale.genre == "puzzle") {
            if (bigCard) {
                Answer(
                    answer = tale.answer ?: "",
                    imageUri = tale.imageUri ?: "",
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
}

/**
 * Composable to display title [TaleUiDetail]
 */
@Composable
internal fun TextDetail(
    tale: TaleUiDetail,
    fontSize: Float,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.padding_small))
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.corner)))
            .background(color = MaterialTheme.colorScheme.onPrimary),
    ) {
        if (tale.genre == "story") {
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

/**
 * Composable to display answer of puzzle
 */
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
            imageUri = imageUri,
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

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalDetailScreenPreview() {
    FairyTalesTheme {
        VerticalDetailScreen(
            tale = TaleUiDetail(
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
            tale = TaleUiDetail(
                title = "Title",
                text = "Text",
                genre = "story"
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
            tale = TaleUiDetail(
                title = "Title",
                text = "Text",
                genre = "puzzle",
                answer = "Answer",
            ),
            fontSize = 24.0F,
        )
    }
}
