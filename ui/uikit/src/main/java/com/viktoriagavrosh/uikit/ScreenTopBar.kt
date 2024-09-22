package com.viktoriagavrosh.uikit

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * App bar to display title and  conditionally display the back navigation
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTopBar(
    text: String,
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier,
    isSettingsIconShow: Boolean = false,
    isBackIconShow: Boolean = true,
    isInfoShow: Boolean = false,
    onSettingsClick: () -> Unit = {},
    onBackClick: () -> Unit = {},
    onInfoClick: () -> Unit = {},
) {
    TopAppBar(
        title = {
            Text(
                text = text,
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier
                    .padding(start = dimensionResource(id = R.dimen.padding_large))
            )
        },
        modifier = modifier,
        navigationIcon = {
            if (isBackIconShow) {
                IconButton(
                    onClick = { onBackClick() },
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = stringResource(R.string.back),
                        modifier = Modifier.size(dimensionResource(id = R.dimen.top_bar_icon_size))
                    )
                }
            }
        },
        actions = {
            if (isInfoShow) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_info),
                    contentDescription = stringResource(R.string.info),
                    modifier = Modifier
                        .clickable {
                            onInfoClick()
                        }
                        .padding(
                            start = dimensionResource(id = R.dimen.padding_small),
                            bottom = dimensionResource(id = R.dimen.padding_small)
                        )
                        .size(dimensionResource(id = R.dimen.top_bar_icon_size))
                )
            }
            if (isSettingsIconShow) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_settings),
                    contentDescription = stringResource(R.string.settings),
                    modifier = Modifier
                        .clickable {
                            onSettingsClick()
                        }
                        .padding(
                            start = dimensionResource(id = R.dimen.padding_small),
                            end = dimensionResource(id = R.dimen.padding_medium),
                            bottom = dimensionResource(id = R.dimen.padding_small)
                        )
                        .size(dimensionResource(id = R.dimen.top_bar_icon_size))
                )
            }
        },
        scrollBehavior = scrollBehavior,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun JustTextTopBarPreview() {
    FairyTalesTheme {
        ScreenTopBar(
            text = "Text",
            isBackIconShow = false,
            scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
            onSettingsClick = {},
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun FullTopBarPreview() {
    FairyTalesTheme {
        ScreenTopBar(
            text = "Text",
            isSettingsIconShow = true,
            isInfoShow = true,
            scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
            onSettingsClick = {},
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun WithSettingsTopBarPreview() {
    FairyTalesTheme {
        ScreenTopBar(
            text = "Text",
            isSettingsIconShow = true,
            scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
            onSettingsClick = {},
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun WithBackTopBarPreview() {
    FairyTalesTheme {
        ScreenTopBar(
            text = "Text",
            scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
            onSettingsClick = {},
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun WithInfoTopBarPreview() {
    FairyTalesTheme {
        ScreenTopBar(
            text = "Text",
            scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
            isInfoShow = true,
            onSettingsClick = {},
        )
    }
}
