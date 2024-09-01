package com.viktoriagavrosh.details

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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.viktoriagavrosh.details.model.TaleUiDetail
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display details of selected [TaleUiDetail]
 */
@Composable
fun DetailScreen(
    taleId: Int,
    isExpandedScreen: Boolean,
    onDetailScreenBackClick: () -> Unit,
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: TaleViewModel = hiltViewModel(
        creationCallback = { factory: TaleViewModel.TaleViewModelFactory ->
            factory.create(taleId)
        }
    )
    val screenState by viewModel.tales.collectAsStateWithLifecycle()
    val textSizeFromDataStore by viewModel.textSize.collectAsStateWithLifecycle()

    DetailScreen(
        screenState = screenState,
        textSizeFromDataStore = textSizeFromDataStore,
        isExpandedScreen = isExpandedScreen,
        onDetailScreenBackClick = onDetailScreenBackClick,
        onSettingsClick = onSettingsClick,
        onTextSizeUpdate = viewModel::updateTextSize,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DetailScreen(
    screenState: DetailScreenState,
    textSizeFromDataStore: Float,
    isExpandedScreen: Boolean,
    onDetailScreenBackClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onTextSizeUpdate: (Float) -> Unit,
    modifier: Modifier = Modifier,
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)

    Column(
        modifier = modifier
    ) {
        DetailsTopBar(
            text = screenState.tale?.title ?: "",
            onDetailScreenBackClick = onDetailScreenBackClick,
            scrollBehavior = scrollBehavior,
            onSettingsClick = onSettingsClick,
            modifier = Modifier.fillMaxWidth(),
        )
        when (screenState) {
            is DetailScreenState.Error -> {
                ErrorScreen(modifier = Modifier.fillMaxSize())
            }

            is DetailScreenState.None -> {}

            is DetailScreenState.Success -> {
                ContentDetailScreen(
                    tale = screenState.tale ?: TaleUiDetail(),
                    isExpandedScreen = isExpandedScreen,
                    textSize = textSizeFromDataStore,
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
 * App bar to display title and  conditionally display the back navigation
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailsTopBar(
    text: String,
    onDetailScreenBackClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = text,
                style = MaterialTheme.typography.displaySmall,
                overflow = TextOverflow.Ellipsis,  // TODO ellipsis are on top of the line
                maxLines = 1,
            )
        },
        modifier = modifier,
        navigationIcon = {
            IconButton(
                onClick = { onDetailScreenBackClick() },
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
        },
        scrollBehavior = scrollBehavior,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DetailsTopBarPreview() {
    FairyTalesTheme {
        DetailsTopBar(
            text = "This text is on the top bar of the detail screen",
            onDetailScreenBackClick = {},
            scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
            onSettingsClick = {},
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
                TaleUiDetail(
                    title = "title",
                    text = "text"
                )
            ),
            textSizeFromDataStore = 24.0F,
            isExpandedScreen = false,
            onDetailScreenBackClick = {},
            onSettingsClick = {},
            onTextSizeUpdate = {},
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
                TaleUiDetail(
                    genre = "puzzle",
                    title = "title",
                    text = "text"
                )
            ),
            textSizeFromDataStore = 24.0F,
            isExpandedScreen = false,
            onDetailScreenBackClick = {},
            onSettingsClick = {},
            onTextSizeUpdate = {},
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
                TaleUiDetail(
                    genre = "story",
                    title = "title",
                    text = "text"
                )
            ),
            textSizeFromDataStore = 24.0F,
            isExpandedScreen = false,
            onDetailScreenBackClick = {},
            onSettingsClick = {},
            onTextSizeUpdate = {},
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
                TaleUiDetail(
                    title = "title",
                    text = "text"
                )
            ),
            textSizeFromDataStore = 24.0F,
            isExpandedScreen = true,
            onDetailScreenBackClick = {},
            onSettingsClick = {},
            onTextSizeUpdate = {},
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
                TaleUiDetail(
                    genre = "puzzle",
                    title = "title",
                    text = "text"
                )
            ),
            textSizeFromDataStore = 24.0F,
            isExpandedScreen = true,
            onDetailScreenBackClick = {},
            onSettingsClick = {},
            onTextSizeUpdate = {},
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
                TaleUiDetail(
                    genre = "story",
                    title = "title",
                    text = "text"
                )
            ),
            textSizeFromDataStore = 24.0F,
            isExpandedScreen = true,
            onDetailScreenBackClick = {},
            onSettingsClick = {},
            onTextSizeUpdate = {},
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
            textSizeFromDataStore = 24.0F,
            isExpandedScreen = false,
            onDetailScreenBackClick = {},
            onSettingsClick = {},
            onTextSizeUpdate = {},
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
            textSizeFromDataStore = 24.0F,
            isExpandedScreen = true,
            onDetailScreenBackClick = {},
            onSettingsClick = {},
            onTextSizeUpdate = {},
        )
    }
}
