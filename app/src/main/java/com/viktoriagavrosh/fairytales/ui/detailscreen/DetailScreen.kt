package com.viktoriagavrosh.fairytales.ui.detailscreen

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.model.TaleUi
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme

/**
 * Composable to display details of selected tale
 */
@Composable
fun DetailScreen(
    taleId: Int,
    isExpandedScreen: Boolean,
    onDetailScreenBackClick: () -> Unit,
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: DetailViewModel = hiltViewModel(
        creationCallback = { factory: DetailViewModel.TaleViewModelFactory ->
            factory.create(taleId)
        }
    )
    val screenState by viewModel.tales.collectAsStateWithLifecycle()
    val textSizeFromDatastore by viewModel.textSize.collectAsStateWithLifecycle()

    DetailScreen(
        screenState = screenState,
        textSizeFromDatastore = textSizeFromDatastore,
        isExpandedScreen = isExpandedScreen,
        onDetailScreenBackClick = onDetailScreenBackClick,
        onSettingsClick = onSettingsClick,
        onTextSizeUpdate = viewModel::updateTextSize,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    // TODO private ?
    screenState: DetailScreenState,
    textSizeFromDatastore: Float,
    isExpandedScreen: Boolean,
    onDetailScreenBackClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onTextSizeUpdate: (Float) -> Unit,
    modifier: Modifier = Modifier,
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)

    Column(modifier = modifier) {
        DetailTopBar(
            text = screenState.tale?.title ?: "",
            onDetailScreenBackClick = onDetailScreenBackClick,
            scrollBehavior = scrollBehavior,
            onSettingsClick = onSettingsClick,
            modifier = Modifier.fillMaxWidth()
        )
        when (screenState) {
            is DetailScreenState.Error -> {
                ErrorDetailScreen(modifier = Modifier.fillMaxSize())
            }

            is DetailScreenState.None -> {}   // TODO default value

            is DetailScreenState.Success -> {
                ContentDetailScreen(
                    tale = screenState.tale ?: TaleUi(),
                    isExpandedScreen = isExpandedScreen,
                    textSize = textSizeFromDatastore,
                    onTextSizeUpdate = onTextSizeUpdate,
                    modifier = if (isExpandedScreen) {
                        Modifier
                            .fillMaxSize()
                            .nestedScroll(scrollBehavior.nestedScrollConnection)
                    } else {
                        Modifier
                            .fillMaxHeight()
                            .nestedScroll(scrollBehavior.nestedScrollConnection)
                    }
                )
            }
        }
    }
}

/**
 * App bar to display title and back navigation
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailTopBar(
    // TODO maybe to merge with topBar of SettingsScreen and move to file in elements.bars
    text: String,
    onDetailScreenBackClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            Text(
                text = text,
                style = MaterialTheme.typography.displaySmall,
            )
        },
        modifier = modifier,
        navigationIcon = {
            IconButton(
                onClick = { onDetailScreenBackClick() },
                modifier = Modifier.padding(
                    start = dimensionResource(id = R.dimen.padding_medium),
                ),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = stringResource(R.string.back),
                    modifier = Modifier.size(dimensionResource(id = R.dimen.top_bar_icon_size))
                )
            }
        },
        actions = {
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
private fun DetailTopBarPreview() {
    FairyTalesTheme {
        DetailTopBar(
            text = "top bar",
            onDetailScreenBackClick = {},
            scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
            onSettingsClick = {}
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalDetailScreenPreview() {
    FairyTalesTheme {
        DetailScreen(
            screenState = DetailScreenState.Success(
                TaleUi(
                    title = "title",
                    text = "text",
                )
            ),
            textSizeFromDatastore = 24.0F,
            isExpandedScreen = false,
            onDetailScreenBackClick = {},
            onSettingsClick = {},
            onTextSizeUpdate = {}
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalPuzzleDetailScreenPreview() {
    FairyTalesTheme {
        DetailScreen(
            screenState = DetailScreenState.Success(
                TaleUi(
                    genre = "puzzle",
                    title = "title",
                    text = "text",
                )
            ),
            textSizeFromDatastore = 24.0F,
            isExpandedScreen = false,
            onDetailScreenBackClick = {},
            onSettingsClick = {},
            onTextSizeUpdate = {}
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalStoryDetailScreenPreview() {
    FairyTalesTheme {
        DetailScreen(
            screenState = DetailScreenState.Success(
                TaleUi(
                    genre = "story",
                    title = "title",
                    text = "text",
                )
            ),
            textSizeFromDatastore = 24.0F,
            isExpandedScreen = false,
            onDetailScreenBackClick = {},
            onSettingsClick = {},
            onTextSizeUpdate = {}
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HorizontalDetailScreenPreview() {
    FairyTalesTheme {
        DetailScreen(
            screenState = DetailScreenState.Success(
                TaleUi(
                    title = "title",
                    text = "text",
                )
            ),
            textSizeFromDatastore = 24.0F,
            isExpandedScreen = true,
            onDetailScreenBackClick = {},
            onSettingsClick = {},
            onTextSizeUpdate = {}
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HorizontalPuzzleDetailScreenPreview() {
    FairyTalesTheme {
        DetailScreen(
            screenState = DetailScreenState.Success(
                TaleUi(
                    genre = "puzzle",
                    title = "title",
                    text = "text",
                )
            ),
            textSizeFromDatastore = 24.0F,
            isExpandedScreen = true,
            onDetailScreenBackClick = {},
            onSettingsClick = {},
            onTextSizeUpdate = {}
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HorizontalStoryDetailScreenPreview() {
    FairyTalesTheme {
        DetailScreen(
            screenState = DetailScreenState.Success(
                TaleUi(
                    genre = "story",
                    title = "title",
                    text = "text",
                )
            ),
            textSizeFromDatastore = 24.0F,
            isExpandedScreen = true,
            onDetailScreenBackClick = {},
            onSettingsClick = {},
            onTextSizeUpdate = {}
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalErrorScreenPreview() {
    FairyTalesTheme {
        DetailScreen(
            screenState = DetailScreenState.Error(),
            textSizeFromDatastore = 24.0F,
            isExpandedScreen = false,
            onDetailScreenBackClick = {},
            onSettingsClick = {},
            onTextSizeUpdate = {}
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HorizontalErrorScreenPreview() {
    FairyTalesTheme {
        DetailScreen(
            screenState = DetailScreenState.Error(),
            textSizeFromDatastore = 24.0F,
            isExpandedScreen = true,
            onDetailScreenBackClick = {},
            onSettingsClick = {},
            onTextSizeUpdate = {}
        )
    }
}
