package com.viktoriagavrosh.fairytales.ui.homescreen

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
import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.model.TaleUi
import com.viktoriagavrosh.fairytales.ui.elements.GridTales
import com.viktoriagavrosh.fairytales.ui.elements.ListTales
import com.viktoriagavrosh.fairytales.ui.elements.TaleType
import com.viktoriagavrosh.fairytales.ui.elements.bars.ContentTopBar
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme

/**
 * Composable to display list or grid of tales
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentHomeScreen(
    screenState: HomeScreenState,
    @StringRes topBarTextId: Int,  // TODO change to String
    isFavoriteTalesList: Boolean,
    isCompactScreen: Boolean,
    onHeartClick: (TaleUi) -> Unit,
    onTopBarHeartClick: () -> Unit,
    onCardClick: (TaleUi) -> Unit,
    onTabClick: (TaleType) -> Unit,
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)

    Scaffold(
        modifier = modifier,
        topBar = {
            ContentTopBar(
                text = stringResource(id = topBarTextId),
                isHeartShow = isFavoriteTalesList,
                onHeartClick = onTopBarHeartClick,
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

            is HomeScreenState.None -> {}  // TODO default value

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
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.error_text),   // TODO check strings.xml !
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displaySmall,
        )
        Button(
            onClick = { onTabClick(TaleType.Story) },  // TODO add different types
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
            screenState = HomeScreenState.Success(
                List(4) {
                    TaleUi(id = it, title = "title")
                }
            ),
            topBarTextId = R.string.title_fairy_tales,
            isFavoriteTalesList = false,
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
            screenState = HomeScreenState.Success(
                List(2) {
                    TaleUi(
                        id = it,
                        title = "title",
                        isFavorite = true,
                    )
                }
            ),
            topBarTextId = R.string.title_fairy_tales,
            isFavoriteTalesList = true,
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
            screenState = HomeScreenState.Success(
                List(4) {
                    TaleUi(id = it, title = "title")
                }
            ),
            topBarTextId = R.string.title_fairy_tales,
            isFavoriteTalesList = false,
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
            screenState = HomeScreenState.Error(),
            topBarTextId = R.string.title_fairy_tales,
            isFavoriteTalesList = false,
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
            screenState = HomeScreenState.Error(),
            topBarTextId = R.string.title_fairy_tales,
            isFavoriteTalesList = false,
            isCompactScreen = true,
            onHeartClick = {},
            onTopBarHeartClick = {},
            onCardClick = {},
            onTabClick = {},
            onSettingsClick = {}
        )
    }
}
