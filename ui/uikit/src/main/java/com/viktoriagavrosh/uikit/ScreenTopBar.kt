package com.viktoriagavrosh.uikit

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display top bar
 *
 * @param text text on top bar
 * @param scrollBehavior holds various offset values that will be applied by this top app bar to set up its height and colors.
 * @param modifier the modifier to be applied to this layout node
 * @param isSettingsIconShow if true, settings icon is shown
 * @param isBackIconShow if true, back icon is shown
 * @param isInfoShow if true, info icon is shown
 * @param onSettingsClick callback that is executed when settings icon is clicked
 * @param onBackClick callback that is executed when back icon on cart is clicked
 * @param onInfoClick callback that is executed when info icon is clicked
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
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_large))
            )
        },
        modifier = modifier,
        navigationIcon = {
            if (isBackIconShow) BackButton(onBackClick = onBackClick)
        },
        actions = {
            if (isInfoShow && isSettingsIconShow) {
                DropdownButton(
                    onInfoClick = onInfoClick,
                    onSettingsClick = onSettingsClick,
                )
            } else {
                if (isInfoShow) InfoButton(onInfoClick = onInfoClick)
                if (isSettingsIconShow) SettingsButton(onSettingsClick = onSettingsClick)
            }
        },
        scrollBehavior = scrollBehavior,
    )
}

/**
 * Composable to display button, that holds some icons
 *
 * @param onInfoClick callback that is executed when info icon is clicked
 * @param onSettingsClick callback that is executed when settings icon is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
private fun DropdownButton(
    onInfoClick: () -> Unit,
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(
        onClick = { expanded = true },
        modifier = modifier,
    ) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = stringResource(R.string.actions),
            modifier = Modifier
                .padding(
                    end = dimensionResource(id = R.dimen.padding_medium),
                    bottom = dimensionResource(id = R.dimen.padding_small)
                )
                .size(dimensionResource(id = R.dimen.top_bar_icon_size))
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = MaterialTheme.colorScheme.onPrimary,
        ) {
            val options = listOf(
                R.drawable.ic_info to R.string.info,
                R.drawable.ic_settings to R.string.settings
            )
            options.forEachIndexed { index, option ->
                DropdownMenuItem(
                    text = {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                painter = painterResource(option.first),
                                contentDescription = stringResource(option.second),
                                modifier = Modifier
                                    .size(dimensionResource(id = R.dimen.top_bar_icon_size))
                            )
                        }
                    },
                    onClick = when (index) {
                        0 -> onInfoClick
                        else -> onSettingsClick
                    },
                )
            }
        }
    }
}

/**
 * Composable to display back button
 *
 * @param onBackClick callback that is executed when back icon is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
private fun BackButton(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(
        onClick = { onBackClick() },
        modifier = modifier,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = stringResource(R.string.back),
            modifier = Modifier.size(dimensionResource(id = R.dimen.top_bar_icon_size))
        )
    }
}

/**
 * Composable to display settings button
 *
 * @param onSettingsClick callback that is executed when settings icon is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
private fun SettingsButton(
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Icon(
        painter = painterResource(id = R.drawable.ic_settings),
        contentDescription = stringResource(R.string.settings),
        modifier = modifier
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

/**
 * Composable to display info button
 *
 * @param onInfoClick callback that is executed when info icon is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
private fun InfoButton(
    onInfoClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Icon(
        painter = painterResource(id = R.drawable.ic_info),
        contentDescription = stringResource(R.string.info),
        modifier = modifier
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
