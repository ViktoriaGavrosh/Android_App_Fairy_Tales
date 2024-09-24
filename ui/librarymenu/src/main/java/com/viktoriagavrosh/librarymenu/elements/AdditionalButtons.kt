package com.viktoriagavrosh.librarymenu.elements

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
internal fun AdditionalButtons(
    onAddTaleClick: () -> Unit,
    onRandomClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column (
        modifier = modifier.width(dimensionResource(R.dimen.additional_button_width)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        MenuButton(
            text = stringResource(R.string.random_tale),
            onClick = onRandomClick,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_large))
        )
        MenuButton(
            text = stringResource(R.string.add_tale_button),
            onClick = onAddTaleClick,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_large))
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AdditionalButtonsPreview() {
    FairyTalesTheme {
        AdditionalButtons(
            onRandomClick = {},
            onAddTaleClick = {}
        )
    }
}
