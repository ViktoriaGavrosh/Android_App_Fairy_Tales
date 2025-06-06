package com.viktoriagavrosh.startmenu.elements

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uikit.buttons.IconMenuButton
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display buttons with actions
 *
 * @param onFavoriteClick callback that is executed when favorite tale button is clicked
 * @param onNightClick callback that is executed when night tale button is clicked
 * @param onLibraryClick callback that is executed when library button is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
internal fun MainButtonColumn(
    onFavoriteClick: () -> Unit,
    onNightClick: () -> Unit,
    onLibraryClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        IconMenuButton(
            iconId = R.drawable.ic_favorite_false,
            text = stringResource(R.string.title_favorite),
            onClick = onFavoriteClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(R.dimen.padding_medium))
        )
        IconMenuButton(
            iconId = R.drawable.ic_night,
            text = stringResource(R.string.title_night),
            onClick = onNightClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(R.dimen.padding_extra_large))
        )
        IconMenuButton(
            iconId = R.drawable.ic_library,
            text = stringResource(R.string.library),
            onClick = onLibraryClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(R.dimen.padding_extra_large))
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MainButtonColumnPreview() {
    FairyTalesTheme {
        MainButtonColumn(
            onFavoriteClick = {},
            onNightClick = {},
            onLibraryClick = {},
        )
    }
}
