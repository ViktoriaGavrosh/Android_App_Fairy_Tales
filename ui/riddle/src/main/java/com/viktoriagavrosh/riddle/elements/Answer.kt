package com.viktoriagavrosh.riddle.elements

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uikit.image.BookImageRow
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display answer content
 *
 * @param answer answer of riddle
 * @param imageUrl url of image for riddle
 * @param isBigImage if true - image takes all width of screen
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
internal fun Answer(
    answer: String,
    imageUrl: String,
    isBigImage: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        BookImageRow(
            title = answer,
            imageUrl = imageUrl,
            modifier = if (isBigImage) {
                Modifier
                    .fillMaxWidth()
                    .padding(
                        top = dimensionResource(id = R.dimen.padding_large),
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
private fun AnswerPreview() {
    FairyTalesTheme {
        Answer(
            answer = "Answer",
            imageUrl = "",
            isBigImage = true,

            )
    }
}
