package com.viktoriagavrosh.uikit.buttons

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uitheme.FairyTalesTheme

@Composable
fun DesignMenuButton(
    @DrawableRes iconId: Int,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        modifier = modifier.heightIn(min = dimensionResource(R.dimen.design_button_height)),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(iconId),
                contentDescription = text,
                modifier = Modifier.size(dimensionResource(R.dimen.top_bar_icon_size))
            )
            Text(
                text = text,
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.headlineSmall,
            )
        }
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DesignMenuButtonPreview() {
    FairyTalesTheme {
        DesignMenuButton(
            iconId = R.drawable.ic_favorite_false,
            text = "Text",
            onClick = {},
        )
    }
}
