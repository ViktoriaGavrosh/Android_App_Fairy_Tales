package com.viktoriagavrosh.uikit

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.viktoriagavrosh.uitheme.FairyTalesTheme

@Composable
fun TextCard(
    text: String,
    textSize: Float,
    modifier: Modifier = Modifier,
    title: String = "",
) {
    var fontSize by remember {
        mutableFloatStateOf(0.0F)
    }
    if (fontSize == 0.0F) fontSize = textSize

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.padding_small))
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.corner)))
            .background(color = MaterialTheme.colorScheme.onPrimary),
    ) {
        if (title.isNotEmpty()) {
            Text(
                text = title,
                style = MaterialTheme.typography.displaySmall,
                fontSize = fontSize.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small)),
            )
        }
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

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TextCardPreview() {
    FairyTalesTheme {
        TextCard(
            text = "Text",
            textSize = 24.0F,
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun WithTitleTextCardPreview() {
    FairyTalesTheme {
        TextCard(
            text = "Text",
            textSize = 24.0F,
            title = "Title",
        )
    }
}
