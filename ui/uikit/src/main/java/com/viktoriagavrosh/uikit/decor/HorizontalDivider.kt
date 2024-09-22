package com.viktoriagavrosh.uikit.decor

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.uitheme.FairyTalesTheme

@Composable
fun HorizontalDivider(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
    ) {
        DecorIcon()
        HorizontalDivider()
        DecorIcon()
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun FlowersRowPreview() {
    FairyTalesTheme {
        HorizontalDivider()
    }
}
