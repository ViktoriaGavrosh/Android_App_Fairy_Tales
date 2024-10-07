package com.viktoriagavrosh.uikit.decor

import android.content.res.Configuration
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uitheme.FairyTalesTheme

@Composable
fun DecorIcon(
    modifier: Modifier = Modifier,
    isWhite: Boolean = false,
) {
    Icon(
        painter = painterResource(R.drawable.ic_decor),
        contentDescription = null,
        tint = if (isWhite) {
            MaterialTheme.colorScheme.onPrimary
        } else {
            MaterialTheme.colorScheme.primary
        },
        modifier = modifier
            .size(dimensionResource(R.dimen.decor_icon_size)),
    )
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun FlowersRowPreview() {
    FairyTalesTheme {
        DecorIcon()
    }
}
