package com.viktoriagavrosh.riddle.elements

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
 * Composable to display details of selected [ReadRiddle] on compact and medium screens
 */
@Composable
internal fun VerticalRiddleContent(
    riddleProvider: () -> ReadRiddle,
    textSizeProvider: () -> Float,
    modifier: Modifier = Modifier
) {
    var bigCard by remember {
        mutableStateOf(false)
    }
    val riddle = riddleProvider()

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        TextRow(
            text = riddle.text,
            title = riddle.title,
            textSizeProvider = textSizeProvider,
            isNotFullScreen = true,
            modifier = Modifier.fillMaxWidth(),
        )
        if (bigCard) {
            Answer(
                answer = riddle.answer,
                imageUrl = riddle.imageUrl ?: "",
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
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                )
            }
        }
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalRiddleContentPreview() {
    FairyTalesTheme {
        VerticalRiddleContent(
            riddleProvider = {
                ReadRiddle(
                    title = "Title",
                    text = "Text",
                    answer = "answer",
                )
            },
            textSizeProvider = { 24.0F },
        )
    }
}
