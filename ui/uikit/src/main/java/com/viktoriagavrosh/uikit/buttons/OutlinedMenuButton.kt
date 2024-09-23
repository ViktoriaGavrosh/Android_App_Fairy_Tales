package com.viktoriagavrosh.uikit.buttons

import android.content.res.Configuration
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uitheme.FairyTalesTheme

@Composable
fun OutlinedMenuButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .heightIn(min = dimensionResource(R.dimen.button_height))
            .widthIn(min = dimensionResource(R.dimen.button_width)),
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
            modifier = Modifier
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun OutlinedMenuButtonPreview() {
    FairyTalesTheme {
        OutlinedMenuButton(
            text = "Text\ntext",
            onClick = {},
        )
    }
}
