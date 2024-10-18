package com.viktoriagavrosh.uikit.decor

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display decor element
 *
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
fun DecorHorizontalDivider(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DecorIcon()
        HorizontalDivider(
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
private fun DecorHorizontalDividerPreview() {
    FairyTalesTheme {
        DecorHorizontalDivider()
    }
}
