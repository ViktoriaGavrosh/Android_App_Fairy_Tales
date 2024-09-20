package com.viktoriagavrosh.settings.elements

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.uitheme.FairyTalesTheme

@Composable
internal fun VerticalSettingsContent(
    textSize: Float,
    onTextSizeUpdate: (Float) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        TextSizeRow(
            textSize = textSize,
            onTextSizeUpdate = onTextSizeUpdate,
            modifier = Modifier.fillMaxWidth()
        )
        // TODO DecorHorizontalDivider will be here
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalSettingsContentPreview() {
    FairyTalesTheme {
        VerticalSettingsContent(
            textSize = 24.0F,
            onTextSizeUpdate = {},
        )
    }
}
