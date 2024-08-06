package com.viktoriagavrosh.home

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.home.elements.GridTales
import com.viktoriagavrosh.home.elements.ListTales
import com.viktoriagavrosh.home.elements.TaleType
import com.viktoriagavrosh.home.elements.bars.ContentTopBar
import com.viktoriagavrosh.home.model.TaleUiHome
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display [TaleUiHome] list or grid screen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ContentScreen(
    screenState: HomeScreenState,
    @StringRes topBarTextId: Int,
    isFavoriteTalesList: Boolean,
    isCompactScreen: Boolean,
    onHeartClick: (TaleUiHome) -> Unit,
    onTopBarHeartClick: () -> Unit,
    onCardClick: (TaleUiHome) -> Unit,
    onTabClick: (TaleType) -> Unit,
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)
    Scaffold(
        modifier = modifier,
        topBar = {
            ContentTopBar(
                text = stringResource(id = topBarTextId),
                isFavoriteTalesList = isFavoriteTalesList,
                onTopBarHeartClick = onTopBarHeartClick,
                scrollBehavior = scrollBehavior,
                onSettingsClick = onSettingsClick,
                modifier = Modifier.fillMaxWidth()
            )
        }
    ) { paddingValues ->
        when (screenState) {
            is HomeScreenState.Error -> {
                ErrorHomeScreen(
                    onTabClick = onTabClick,
                    modifier = Modifier.fillMaxSize()
                )
            }

            is HomeScreenState.None -> {}

            is HomeScreenState.Success -> {
                if (isCompactScreen) {
                    ListTales(
                        tales = screenState.tales.orEmpty(),
                        onCardClick = onCardClick,
                        onHeartClick = onHeartClick,
                        modifier = Modifier
                            .padding(paddingValues)
                            .nestedScroll(scrollBehavior.nestedScrollConnection)
                    )
                } else {
                    GridTales(
                        tales = screenState.tales.orEmpty(),
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

@Composable
private fun ErrorHomeScreen(
    onTabClick: (TaleType) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.error_text),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displaySmall
        )
        Button(
            onClick = { onTabClick(TaleType.Story) },
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.padding_extra_large))
        ) {
            Text(
                text = stringResource(R.string.error_button_text),
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
        ContentScreen(
            screenState = HomeScreenState.Success(
                List(4) {
                    TaleUiHome(
                        id = it,
                        title = "title",
                    )
                }
            ),
            topBarTextId = R.string.title_fairy_tales,
            isFavoriteTalesList = false,
            isCompactScreen = true,
            onHeartClick = {},
            onTopBarHeartClick = {},
            onTabClick = {},
            onCardClick = {},
            onSettingsClick = {},
        )
    }
}

@Preview(name = "Light", widthDp = 700)
@Preview(name = "Dark", widthDp = 700, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalFavoriteContentScreenPreview() {
    FairyTalesTheme {
        ContentScreen(
            screenState = HomeScreenState.Success(
                List(2) {
                    TaleUiHome(
                        id = it,
                        title = "title",
                        isFavorite = true
                    )
                }
            ),
            topBarTextId = R.string.title_fairy_tales,
            isFavoriteTalesList = true,
            isCompactScreen = true,
            onHeartClick = {},
            onTopBarHeartClick = {},
            onTabClick = {},
            onCardClick = {},
            onSettingsClick = {},
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HorizontalContentScreenPreview() {
    FairyTalesTheme {
        ContentScreen(
            screenState = HomeScreenState.Success(
                List(4) {
                    TaleUiHome(
                        id = it,
                        title = "title",
                    )
                }
            ),
            topBarTextId = R.string.title_fairy_tales,
            isFavoriteTalesList = false,
            isCompactScreen = false,
            onHeartClick = {},
            onTopBarHeartClick = {},
            onTabClick = {},
            onCardClick = {},
            onSettingsClick = {},
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalErrorContentScreenPreview() {
    FairyTalesTheme {
        ContentScreen(
            screenState = HomeScreenState.Error(),
            topBarTextId = R.string.title_fairy_tales,
            isFavoriteTalesList = false,
            isCompactScreen = true,
            onHeartClick = {},
            onTopBarHeartClick = {},
            onTabClick = {},
            onCardClick = {},
            onSettingsClick = {},
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HorizontalErrorContentScreenPreview() {
    FairyTalesTheme {
        ContentScreen(
            screenState = HomeScreenState.Error(),
            topBarTextId = R.string.title_fairy_tales,
            isFavoriteTalesList = false,
            isCompactScreen = true,
            onHeartClick = {},
            onTopBarHeartClick = {},
            onTabClick = {},
            onCardClick = {},
            onSettingsClick = {},
        )
    }
}
