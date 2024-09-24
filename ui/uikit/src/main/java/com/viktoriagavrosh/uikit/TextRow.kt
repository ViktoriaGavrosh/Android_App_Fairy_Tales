package com.viktoriagavrosh.uikit

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.uitheme.FairyTalesTheme

@Composable
fun TextRow(
    text: String,
    title: String,
    textSizeProvider: () -> Float,
    isNotFullScreen: Boolean,
    modifier: Modifier = Modifier,
    isTitleShow: Boolean = false,
) {
    Row(
        modifier = modifier
    ) {
        if (isNotFullScreen) {
            Spacer(modifier = Modifier.weight(1F))
            TextCard(
                text = text,
                textSizeProvider = textSizeProvider,
                isTitleShow = isTitleShow,
            )
            Spacer(modifier = Modifier.weight(1F))
        } else {
            TextCard(
                text = text,
                title = title,
                textSizeProvider = textSizeProvider,
                isTitleShow = isTitleShow,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun FullScreenTextRowPreview() {
    FairyTalesTheme {
        TextRow(
            text = "Text",
            title = "Title",
            textSizeProvider = { 24.0F },
            isNotFullScreen = false,
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TextRowPreview() {
    FairyTalesTheme {
        TextRow(
            text = "Text",
            title = "Title",
            textSizeProvider = { 24.0F },
            isNotFullScreen = true,
        )
    }
}
