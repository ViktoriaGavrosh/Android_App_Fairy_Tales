package com.viktoriagavrosh.fairytales.ui.homescreen

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.model.TaleUi
import com.viktoriagavrosh.fairytales.ui.ScreenState
import com.viktoriagavrosh.fairytales.ui.elements.Genre
import com.viktoriagavrosh.fairytales.ui.elements.GridTales
import com.viktoriagavrosh.fairytales.ui.elements.ListTales
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme

/**
 * Composable to display list or grid of tales
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentHomeScreen(
    screenState: ScreenState<List<TaleUi>>,
    topBarText: String,
    isFavoriteTalesShown: Boolean,
    isCompactScreen: Boolean,
    onHeartClick: (TaleUi) -> Unit,
    onTopBarHeartClick: () -> Unit,
    onCardClick: (TaleUi) -> Unit,
    onTabClick: (Genre) -> Unit,
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)

    Scaffold(
        modifier = modifier,
        topBar = {
            ContentTopBar(
                text = topBarText,
                isHeartShow = isFavoriteTalesShown,
                onHeartClick = onTopBarHeartClick,
                scrollBehavior = scrollBehavior,
                onSettingsClick = onSettingsClick,
                modifier = Modifier.fillMaxWidth()
            )
        }
    ) { paddingValues ->
        when (screenState) {
            is ScreenState.Error -> {
                ErrorHomeScreen(
                    onTabClick = onTabClick,
                    modifier = Modifier.fillMaxSize()
                )
            }

            is ScreenState.None -> {}  // default value

            is ScreenState.Success -> {
                if (isCompactScreen) {
                    ListTales(
                        tales = screenState.data.orEmpty(),
                        onCardClick = onCardClick,
                        onHeartClick = onHeartClick,
                        modifier = Modifier
                            .padding(paddingValues)
                            .nestedScroll(scrollBehavior.nestedScrollConnection)
                    )
                } else {
                    GridTales(
                        tales = screenState.data.orEmpty(),
                        onCardClick = onCardClick,
                        onHeartClick = onHeartClick,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                            .nestedScroll(scrollBehavior.nestedScrollConnection)
                    )
                }
            }
        }
    }
}

/**
 * App bar to display title
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ContentTopBar(
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

@Composable
private fun ErrorHomeScreen(
    onTabClick: (Genre) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.error_text),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displaySmall,
        )
        Button(
            onClick = { onTabClick(Genre.Story) },  // TODO add different types
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_extra_large)),
        ) {
            Text(
                text = stringResource(id = R.string.error_button_text),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displaySmall,
            )
        }
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalContentScreenPreview() {
    FairyTalesTheme {
        ContentHomeScreen(
            screenState = ScreenState.Success(
                List(4) {
                    TaleUi(id = it, title = "title")
                }
            ),
            topBarText = stringResource(id = R.string.title_fairy_tales),
            isFavoriteTalesShown = false,
            isCompactScreen = true,
            onHeartClick = {},
            onTopBarHeartClick = {},
            onCardClick = {},
            onTabClick = {},
            onSettingsClick = {}
        )
    }
}

@Preview(name = "Light", widthDp = 700)
@Preview(name = "Dark", widthDp = 700, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalFavoriteContentScreenPreview() {
    FairyTalesTheme {
        ContentHomeScreen(
            screenState = ScreenState.Success(
                List(2) {
                    TaleUi(
                        id = it,
                        title = "title",
                        isFavorite = true,
                    )
                }
            ),
            topBarText = stringResource(id = R.string.title_fairy_tales),
            isFavoriteTalesShown = true,
            isCompactScreen = true,
            onHeartClick = {},
            onTopBarHeartClick = {},
            onCardClick = {},
            onTabClick = {},
            onSettingsClick = {}
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HorizontalContentScreenPreview() {
    FairyTalesTheme {
        ContentHomeScreen(
            screenState = ScreenState.Success(
                List(4) {
                    TaleUi(id = it, title = "title")
                }
            ),
            topBarText = stringResource(id = R.string.title_fairy_tales),
            isFavoriteTalesShown = false,
            isCompactScreen = false,
            onHeartClick = {},
            onTopBarHeartClick = {},
            onCardClick = {},
            onTabClick = {},
            onSettingsClick = {}
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalErrorContentScreenPreview() {
    FairyTalesTheme {
        ContentHomeScreen(
            screenState = ScreenState.Error(),
            topBarText = stringResource(id = R.string.title_fairy_tales),
            isFavoriteTalesShown = false,
            isCompactScreen = true,
            onHeartClick = {},
            onTopBarHeartClick = {},
            onCardClick = {},
            onTabClick = {},
            onSettingsClick = {}
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HorizontalErrorContentScreenPreview() {
    FairyTalesTheme {
        ContentHomeScreen(
            screenState = ScreenState.Error(),
            topBarText = stringResource(id = R.string.title_fairy_tales),
            isFavoriteTalesShown = false,
            isCompactScreen = true,
            onHeartClick = {},
            onTopBarHeartClick = {},
            onCardClick = {},
            onTabClick = {},
            onSettingsClick = {}
        )
    }
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
