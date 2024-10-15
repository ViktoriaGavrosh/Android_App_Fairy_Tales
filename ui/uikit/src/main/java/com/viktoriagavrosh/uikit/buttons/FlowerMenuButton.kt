package com.viktoriagavrosh.uikit.buttons

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uikit.decor.DecorIcon
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display button with decor elements
 *
 * @param text text on button
 * @param onClick callback that is executed when button is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
fun FlowerMenuButton(
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
            DecorIcon(isWhite = true)
            Text(
                text = text,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall,
            )
            DecorIcon(isWhite = true)
        }
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DesignMenuButtonPreview() {
    FairyTalesTheme {
        FlowerMenuButton(
            text = "Text",
            onClick = {},
        )
    }
}
