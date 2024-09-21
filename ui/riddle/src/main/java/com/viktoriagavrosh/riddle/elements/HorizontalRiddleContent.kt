package com.viktoriagavrosh.riddle.elements

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
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
import com.viktoriagavrosh.riddle.R
import com.viktoriagavrosh.riddle.model.ReadRiddle
import com.viktoriagavrosh.uikit.TextRow
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display details of selected [ReadRiddle] on expanded screen
 */
@Composable
internal fun HorizontalRiddleContent(
    riddle: ReadRiddle,
    textSize: Float,
    modifier: Modifier = Modifier
) {
    var bigCard by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextRow(
            text = riddle.text,
            title = riddle.title,
            textSize = textSize,
            isNotFullScreen = true,
            modifier = Modifier.weight(1F),
        )
        Column(
            modifier = Modifier.weight(1F),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            if (bigCard) {
                Row {
                    Spacer(modifier = Modifier.weight(1F))
                    Answer(
                        answer = riddle.answer,
                        imageUrl = riddle.imageUrl ?: "",
                        isBigImage = false,
                        modifier = Modifier
                            .weight(2F)
                            .padding(top = dimensionResource(id = R.dimen.padding_small))
                    )
                    Spacer(modifier = Modifier.weight(1F))
                }
            } else {
                Button(
                    onClick = { bigCard = true },
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_small))
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

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HorizontalRiddleContentPreview() {
    FairyTalesTheme {
        HorizontalRiddleContent(
            riddle = ReadRiddle(
                text = "Text",
                title = "Title",
                answer = "answer",
            ),
            textSize = 24.0F,
        )
    }
}
