package com.viktoriagavrosh.riddle.elements

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.viktoriagavrosh.riddle.model.ReadRiddle
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uikit.text.TextRow
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display RiddleScreen content (horizontal screen orientation)
 *
 * @param riddleProvider provides id of selected riddle
 * @param textSizeProvider provides text size value
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
internal fun HorizontalRiddleContent(
    riddleProvider: () -> ReadRiddle,
    textSizeProvider: () -> Float,
    modifier: Modifier = Modifier
) {
    var bigCard by remember {
        mutableStateOf(false)
    }
    val riddle = riddleProvider()

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextRow(
            text = riddle.text,
            title = riddle.title,
            textSizeProvider = textSizeProvider,
            isNotFullScreen = true,
            modifier = Modifier.weight(1F),
        )
        Column(
            modifier = Modifier.weight(1F),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            if (bigCard) {
                Answer(
                    answer = riddle.answer,
                    imageUrl = riddle.imageUrl ?: "",
                    isBigImage = false,
                    modifier = Modifier
                        .padding(top = dimensionResource(id = R.dimen.padding_small))
                )
            } else {
                Button(
                    onClick = { bigCard = true },
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_small))
                ) {
                    Text(
                        text = stringResource(R.string.answer_button),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
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
            riddleProvider = {
                ReadRiddle(
                    text = "Text",
                    title = "Title",
                    answer = "answer",
                )
            },
            textSizeProvider = { 24.0F },
        )
    }
}
