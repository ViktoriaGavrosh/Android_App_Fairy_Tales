package com.viktoriagavrosh.details

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.viktoriagavrosh.details.model.TaleUiDetail
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display details of selected [TaleUiDetail] on expanded screen
 */
@Composable
fun HorizontalDetailScreen(
    tale: TaleUiDetail,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        if (tale.genre != "puzzle") {
            ImageHorizontal(
                title = tale.title,
                imageUri = tale.imageUri ?: ""
            )
        }
        Row {
            Spacer(
                modifier = if (tale.genre == "story") {
                    Modifier.width(dimensionResource(id = R.dimen.padding_small))
                } else {
                    Modifier.weight(1F)
                }
            )
            TextDetail(
                text = tale.text,
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
            AnswerHorizontal(
                answer = tale.answer ?: "",
                imageUri = tale.imageUri ?: "",
                modifier = Modifier
                    .padding(bottom = dimensionResource(id = R.dimen.padding_small))
            )
        }
    }
}

@Composable
private fun ImageHorizontal(
    title: String,
    imageUri: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.weight(1F))
        TaleImage(
            title = title,
            imageUri = imageUri,
            modifier = Modifier
                .weight(2F)
                .padding(top = dimensionResource(id = R.dimen.padding_small))
        )
        Spacer(modifier = Modifier.weight(1F))
    }
}

/**
 * Composable that represents an image of [TaleUiDetail]
 */
@Composable
internal fun TaleImage(
    modifier: Modifier = Modifier,
    title: String,
    imageUri: String,
    isBlur: Boolean = false
) {
    AsyncImage(
        model = ImageRequest
            .Builder(context = LocalContext.current)
            .data(imageUri)
            .crossfade(true)
            .build(),
        contentDescription = title,
        error = painterResource(id = R.drawable.error),
        placeholder = painterResource(id = R.drawable.placeholder),
        contentScale = ContentScale.Crop,
        modifier = if (isBlur) {
            modifier
                .aspectRatio(1.5F)
                .blur(
                    radiusX = 20.dp,
                    radiusY = 20.dp,
                    edgeTreatment = BlurredEdgeTreatment(
                        RoundedCornerShape(dimensionResource(id = R.dimen.corner))
                    )
                )
        } else {
            modifier
                .aspectRatio(1.5F)
                .clip(
                    RoundedCornerShape(
                        dimensionResource(id = R.dimen.corner)
                    )
                )
        }
    )
}

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

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HorizontalDetailScreenPreview() {
    FairyTalesTheme {
        HorizontalDetailScreen(
            tale = TaleUiDetail(
                text = "Text"
            )
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PuzzleHorizontalDetailScreenPreview() {
    FairyTalesTheme {
        HorizontalDetailScreen(
            tale = TaleUiDetail(
                genre = "puzzle",
                text = "Text",
                answer = "Answer"
            )
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun StoryHorizontalDetailScreenPreview() {
    FairyTalesTheme {
        HorizontalDetailScreen(
            tale = TaleUiDetail(
                genre = "story",
                text = "Text"
            )
        )
    }
}
