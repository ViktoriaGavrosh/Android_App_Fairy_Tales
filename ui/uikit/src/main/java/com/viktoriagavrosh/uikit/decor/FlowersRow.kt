package com.viktoriagavrosh.uikit.decor

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uitheme.FairyTalesTheme

@Composable
fun FlowersRow(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
    ) {
        for (i in 1..3) {
            DecorIcon(
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)),
            )
        }
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun FlowersRowPreview() {
    FairyTalesTheme {
        FlowersRow()
    }
}
