package com.viktoriagavrosh.startmenu.elements

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.startmenu.R
import com.viktoriagavrosh.uikit.Cover
import com.viktoriagavrosh.uikit.buttons.DesignMenuButton
import com.viktoriagavrosh.uikit.buttons.MenuButton
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
        modifier = modifier.padding(
            horizontal = dimensionResource(R.dimen.padding_double_extra_large)
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
    ) {
        Cover(
            modifier = Modifier.fillMaxWidth()
        )
        DesignMenuButton(
            iconId = R.drawable.ic_favorite,
            text = stringResource(R.string.favorite_tales_title),
            onClick = onFavoriteClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(R.dimen.padding_medium))
        )
        Column(modifier = Modifier.fillMaxWidth()) {
            DesignMenuButton(
                iconId = R.drawable.ic_night,
                text = stringResource(R.string.night_tales),
                onClick = onNightClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(R.dimen.padding_medium))
            )
            DesignMenuButton(
                iconId = R.drawable.ic_library,
                text = stringResource(R.string.library),
                onClick = onLibraryClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(R.dimen.padding_medium))
            )
        }
        Row (
            modifier = Modifier
            .fillMaxWidth()
            .padding(top = dimensionResource(R.dimen.padding_medium))
        ) {
            Spacer(modifier = Modifier.weight(1F))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                MenuButton(
                    text = stringResource(R.string.last_tale),
                    onClick = onLastTaleClick,
                    modifier = Modifier
                )

                MenuButton(
                    text = stringResource(R.string.random_tale),
                    onClick = onRandomClick,
                    modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_medium))
                )
            }
            Spacer(modifier = Modifier.weight(1F))
        }
        DesignMenuButton(
            iconId = R.drawable.ic_settings,
            text = stringResource(R.string.settings_button),
            onClick = onSettingsClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(R.dimen.padding_medium))
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
