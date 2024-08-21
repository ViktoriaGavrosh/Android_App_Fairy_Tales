package com.viktoriagavrosh.fairytales.ui.elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.viktoriagavrosh.fairytales.R

/**
 * Composable to display answer of puzzle
 */
@Composable
fun Answer(
    answer: String?,
    imageUrl: String?,
    isBigImage: Boolean,
    modifier: Modifier = Modifier,
) {
    if (answer == null) {
        Box(modifier = modifier) {
            Text(
                text = stringResource(R.string.not_answer_text),
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
            )
        }
    } else {
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

}
