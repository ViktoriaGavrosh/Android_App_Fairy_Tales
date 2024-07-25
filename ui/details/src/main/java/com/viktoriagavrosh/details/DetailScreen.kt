package com.viktoriagavrosh.details

import android.content.res.Configuration
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
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
    modifier: Modifier = Modifier,
) {
    val viewModel: TaleViewModel = hiltViewModel(
        creationCallback = { factory: TaleViewModel.TaleViewModelFactory ->
            factory.create(taleId)
        }
    )
    val tale by viewModel.tales.collectAsState()

    DetailScreen(
        tale = tale,
        isExpandedScreen = isExpandedScreen,
        onDetailScreenBackClick = onDetailScreenBackClick,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailScreen(
    tale: TaleUiDetail,
    isExpandedScreen: Boolean,
    onDetailScreenBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)

    Column(
        modifier = modifier
    ) {
        DetailsTopBar(
            text = tale.title,
            onDetailScreenBackClick = onDetailScreenBackClick,
            scrollBehavior = scrollBehavior,
            modifier = Modifier.fillMaxWidth(),
        )
        ContentDetailScreen(
            tale = tale,
            isExpandedScreen = isExpandedScreen,
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

/**
 * App bar to display title and  conditionally display the back navigation
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailsTopBar(
    text: String,
    onDetailScreenBackClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier
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
                    start = dimensionResource(id = R.dimen.padding_medium)
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = stringResource(R.string.back),
                    modifier = Modifier.size(dimensionResource(id = R.dimen.top_bar_icon_size))
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
private fun DetailsTopBarPreview() {
    FairyTalesTheme {
        DetailsTopBar(
            text = "Top bar",
            onDetailScreenBackClick = {},
            scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalDetailScreenPreview() {
    FairyTalesTheme {
        DetailScreen(
            tale = TaleUiDetail(
                title = "title",
                text = "text"
            ),
            isExpandedScreen = false,
            onDetailScreenBackClick = {}
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalPuzzleDetailScreenPreview() {
    FairyTalesTheme {
        DetailScreen(
            tale = TaleUiDetail(
                genre = "puzzle",
                title = "title",
                text = "text"
            ),
            isExpandedScreen = false,
            onDetailScreenBackClick = {}
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalStoryDetailScreenPreview() {
    FairyTalesTheme {
        DetailScreen(
            tale = TaleUiDetail(
                genre = "story",
                title = "title",
                text = "text"
            ),
            isExpandedScreen = false,
            onDetailScreenBackClick = {}
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HorizontalDetailScreenPreview() {
    FairyTalesTheme {
        DetailScreen(
            tale = TaleUiDetail(
                title = "title",
                text = "text"
            ),
            isExpandedScreen = true,
            onDetailScreenBackClick = {}
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HorizontalPuzzleDetailScreenPreview() {
    FairyTalesTheme {
        DetailScreen(
            tale = TaleUiDetail(
                genre = "puzzle",
                title = "title",
                text = "text"
            ),
            isExpandedScreen = true,
            onDetailScreenBackClick = {}
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HorizontalStoryDetailScreenPreview() {
    FairyTalesTheme {
        DetailScreen(
            tale = TaleUiDetail(
                genre = "story",
                title = "title",
                text = "text"
            ),
            isExpandedScreen = true,
            onDetailScreenBackClick = {}
        )
    }
}
