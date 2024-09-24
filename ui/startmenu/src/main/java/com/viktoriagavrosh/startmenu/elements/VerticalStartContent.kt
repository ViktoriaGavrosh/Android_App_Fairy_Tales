package com.viktoriagavrosh.startmenu.elements

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.uikit.Cover
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uikit.buttons.MenuButton
import com.viktoriagavrosh.uikit.decor.DecorHorizontalDivider
import com.viktoriagavrosh.uitheme.FairyTalesTheme

@Composable
internal fun VerticalStartContent(
    onFavoriteClick: () -> Unit,
    onNightClick: () -> Unit,
    onLibraryClick: () -> Unit,
    onLastTaleClick: () -> Unit,
    onRandomClick: () -> Unit,
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Cover(
            text = stringResource(R.string.cover_title),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(R.dimen.padding_double_extra_large),
                    bottom = dimensionResource(R.dimen.padding_extra_large)
                )
        )
        MainButtonColumn(
            onFavoriteClick = onFavoriteClick,
            onLibraryClick = onLibraryClick,
            onNightClick = onNightClick,
            modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_double_extra_large)),
        )
        DecorHorizontalDivider(
            modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium))
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            AdditionalButtonColumn(
                onLastTaleClick = onLastTaleClick,
                onRandomClick = onRandomClick,
            )
        }
        DecorHorizontalDivider(
            modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium))
        )
        MenuButton(
            text = stringResource(R.string.settings_title),
            onClick = onSettingsClick,
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalContentPreview() {
    FairyTalesTheme {
        VerticalStartContent(
            onSettingsClick = {},
            onNightClick = {},
            onRandomClick = {},
            onLibraryClick = {},
            onFavoriteClick = {},
            onLastTaleClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}
