package com.viktoriagavrosh.uikit.text

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uikit.decor.DecorHorizontalEmptyDivider
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display card with text and decor elements
 *
 * @param text text that is displayed
 * @param textSizeProvider provides text size value
 * @param modifier the modifier to be applied to this layout node
 * @param isTitleShow if true, title will show
 * @param isDecorShow f true, decor elements will show
 * @param title title of text
 */
@Composable
fun TextCard(
    text: String,
    textSizeProvider: () -> Float,
    modifier: Modifier = Modifier,
    isTitleShow: Boolean = false,
    isDecorShow: Boolean = true,
    title: String = "",
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.padding_small))
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.corner)))
            .background(color = MaterialTheme.colorScheme.onPrimary),
    ) {
        if (isDecorShow) {
            DecorHorizontalEmptyDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(stringResource(R.string.top_decor_divider_test_tag))
            )
        }
        if (isTitleShow) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontSize = textSizeProvider().sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = dimensionResource(id = R.dimen.padding_medium)),
            )
        }
        Text(
            text = text,
            fontSize = textSizeProvider().sp,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.padding_small))
                .animateContentSize(),
        )
        if (isDecorShow) {
            DecorHorizontalEmptyDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(stringResource(R.string.bottom_decor_divider_test_tag))
            )
        }
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TextCardPreview() {
    FairyTalesTheme {
        TextCard(
            text = "Text",
            textSizeProvider = { 24.0F },
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun WithoutDecorTextCardPreview() {
    FairyTalesTheme {
        TextCard(
            text = "Text",
            textSizeProvider = { 24.0F },
            isDecorShow = false,
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
            textSizeProvider = { 24.0F },
            title = "Title",
            isTitleShow = true,
        )
    }
}
