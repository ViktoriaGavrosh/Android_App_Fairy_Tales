package com.viktoriagavrosh.fairytales.ui.elements.bars

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme

/**
 * App bar to display title
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentTopBar(    // TODO mode to HomeScreen and to do private
    text: String,
    isHeartShow: Boolean,
    onHeartClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val heartIconId: Int
    val contentDescriptionHeartIconId: Int

    if (isHeartShow) {
        heartIconId = R.drawable.ic_favorite_true
        contentDescriptionHeartIconId = R.string.favorite_tales
    } else {
        heartIconId = R.drawable.ic_favorite_false
        contentDescriptionHeartIconId = R.string.all_tales
    }

    TopAppBar(
        title = {
            Text(
                text = text,
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier
                    .padding(start = dimensionResource(id = R.dimen.padding_large)),
            )
        },
        modifier = modifier,
        actions = {
            Icon(
                painter = painterResource(id = heartIconId),
                contentDescription = stringResource(id = contentDescriptionHeartIconId),
                modifier = Modifier
                    .clickable { onHeartClick() }
                    .padding(
                        start = dimensionResource(id = R.dimen.padding_small),
                        end = dimensionResource(id = R.dimen.padding_medium),
                        bottom = dimensionResource(id = R.dimen.padding_small)
                    )
                    .size(dimensionResource(id = R.dimen.top_bar_icon_size))
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_settings),
                contentDescription = stringResource(id = R.string.settings),
                modifier = Modifier
                    .clickable { onSettingsClick() }
                    .padding(
                        start = dimensionResource(id = R.dimen.padding_small),
                        end = dimensionResource(id = R.dimen.padding_medium),
                        bottom = dimensionResource(id = R.dimen.padding_small)
                    )
                    .size(dimensionResource(id = R.dimen.top_bar_icon_size))
            )
        },
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ContentTopBarPreview() {
    FairyTalesTheme {
        ContentTopBar(
            text = "Text",
            isHeartShow = false,
            onHeartClick = {},
            scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
            onSettingsClick = {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun FavoriteContentTopBarPreview() {
    FairyTalesTheme {
        ContentTopBar(
            text = "Text",
            isHeartShow = true,
            onHeartClick = {},
            scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
            onSettingsClick = {}
        )
    }
}
