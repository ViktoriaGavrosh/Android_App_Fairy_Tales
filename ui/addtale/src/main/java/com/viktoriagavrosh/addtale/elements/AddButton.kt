package com.viktoriagavrosh.addtale.elements

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display add tale button
 *
 * @param isTaleValidProvider provides boolean. If true, new tale can be saved
 * @param onClick callback that is executed when button is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
internal fun AddButton(
    isTaleValidProvider: () -> Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterEnd,
    ) {
        Button(
            onClick = onClick,
            enabled = isTaleValidProvider(),
        ) {
            Text(
                text = stringResource(R.string.add_tale_button_text),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
            )
        }
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AddButtonPreview() {
    FairyTalesTheme {
        AddButton(
            onClick = {},
            isTaleValidProvider = { true },
        )
    }
}
