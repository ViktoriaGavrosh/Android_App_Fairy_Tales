package com.viktoriagavrosh.settings.elements

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uikit.decor.DecorVerticalDivider
import com.viktoriagavrosh.uitheme.FairyTalesTheme

@Composable
internal fun HorizontalSettingsContent(
    textSizeProvider: () -> Float,
    onTextSizeUpdate: (Float) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .weight(1F)
                .padding(
                    start = dimensionResource(R.dimen.padding_medium)
                ),
        ) {
            TextSizeRow(
                textSizeProvider = textSizeProvider,
                onTextSizeUpdate = onTextSizeUpdate,
                modifier = Modifier.fillMaxWidth()
            )
        }
        DecorVerticalDivider(
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = dimensionResource(R.dimen.padding_medium))
        )
        Column(
            modifier = Modifier.weight(1F)
        ) {
            // needs for more settings
        }
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HorizontalSettingsContentPreview() {
    FairyTalesTheme {
        HorizontalSettingsContent(
            textSizeProvider = { 24.0F },
            onTextSizeUpdate = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}
