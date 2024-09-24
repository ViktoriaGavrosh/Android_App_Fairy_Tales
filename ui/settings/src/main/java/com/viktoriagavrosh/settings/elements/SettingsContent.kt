package com.viktoriagavrosh.settings.elements

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display Settings
 */
@Composable
internal fun SettingsContent(
    textSizeProvider: () -> Float,
    isVerticalScreen: Boolean,
    onTextSizeUpdate: (Float) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (isVerticalScreen) {
        VerticalSettingsContent(
            textSizeProvider = textSizeProvider,
            onTextSizeUpdate = onTextSizeUpdate,
            modifier = modifier,
        )
    } else {
        HorizontalSettingsContent(
            textSizeProvider = textSizeProvider,
            onTextSizeUpdate = onTextSizeUpdate,
            modifier = modifier,
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalSettingsContentPreview() {
    FairyTalesTheme {
        SettingsContent(
            textSizeProvider = { 24.0F },
            isVerticalScreen = true,
            onTextSizeUpdate = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HorizontalSettingsContentPreview() {
    FairyTalesTheme {
        SettingsContent(
            textSizeProvider = { 24.0F },
            isVerticalScreen = false,
            onTextSizeUpdate = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}
