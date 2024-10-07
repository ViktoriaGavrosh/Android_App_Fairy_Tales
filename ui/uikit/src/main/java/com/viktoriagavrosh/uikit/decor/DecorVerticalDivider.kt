package com.viktoriagavrosh.uikit.decor

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uitheme.FairyTalesTheme

@Composable
fun DecorVerticalDivider(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        DecorIcon()
        VerticalDivider(
            modifier = Modifier.weight(1F),
            thickness = dimensionResource(R.dimen.divider_thickness),
            color = MaterialTheme.colorScheme.primary,
        )
        DecorIcon()
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DecorVerticalDividerPreview() {
    FairyTalesTheme {
        DecorVerticalDivider()
    }
}
