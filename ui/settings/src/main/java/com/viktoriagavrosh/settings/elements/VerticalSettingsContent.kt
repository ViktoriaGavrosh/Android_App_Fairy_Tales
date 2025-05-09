package com.viktoriagavrosh.settings.elements

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.uikit.decor.DecorHorizontalDivider
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display SettingsScreen content (vertical screen orientation)
 *
 * @param textSizeProvider provides text size value
 * @param onTextSizeUpdate callback that is executed when text size is changed
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
internal fun VerticalSettingsContent(
    textSizeProvider: () -> Float,
    onTextSizeUpdate: (Float) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        TextSizeRow(
            textSizeProvider = textSizeProvider,
            onTextSizeUpdate = onTextSizeUpdate,
            modifier = Modifier.fillMaxWidth()
        )
        DecorHorizontalDivider()
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalSettingsContentPreview() {
    FairyTalesTheme {
        VerticalSettingsContent(
            textSizeProvider = { 80.0F },
            onTextSizeUpdate = {},
        )
    }
}
