package com.viktoriagavrosh.settings.elements

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uikit.SettingSlider
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display textSize setting
 *
 * @param textSizeProvider provides text size value
 * @param onTextSizeUpdate callback that is executed when text size is changed
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
internal fun TextSizeRow(
    textSizeProvider: () -> Float,
    onTextSizeUpdate: (Float) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = stringResource(R.string.textsize_title),
            modifier = Modifier.padding(
                end = dimensionResource(id = R.dimen.padding_extra_large),
                start = dimensionResource(id = R.dimen.padding_small)
            ),
            style = MaterialTheme.typography.titleLarge
        )
        SettingSlider(
            text = stringResource(R.string.letters),
            settingSizeProvider = textSizeProvider,
            onSettingSizeUpdate = onTextSizeUpdate,
            testTag = stringResource(R.string.textsize_slider),
        )
    }
}

@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TextSizeRowPreview() {
    FairyTalesTheme {
        TextSizeRow(
            textSizeProvider = { 80.0F },
            onTextSizeUpdate = {}
        )
    }
}
