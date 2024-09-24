package com.viktoriagavrosh.startmenu.elements

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uikit.buttons.MenuButton
import com.viktoriagavrosh.uitheme.FairyTalesTheme

@Composable
internal fun AdditionalButtonColumn(
    onLastTaleClick: () -> Unit,
    onRandomClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.width(dimensionResource(R.dimen.additional_button_width))
    ) {
        MenuButton(
            text = stringResource(R.string.last_tale),
            onClick = onLastTaleClick,
        )

        MenuButton(
            text = stringResource(R.string.random_tale),
            onClick = onRandomClick,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_medium))
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AdditionalButtonColumnPreview() {
    FairyTalesTheme {
        AdditionalButtonColumn(
            onRandomClick = {},
            onLastTaleClick = {},
        )
    }
}
