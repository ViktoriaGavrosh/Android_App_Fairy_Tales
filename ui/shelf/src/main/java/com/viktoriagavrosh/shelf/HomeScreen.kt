package com.viktoriagavrosh.shelf

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.viktoriagavrosh.repositories.utils.ShelfGenre
import com.viktoriagavrosh.shelf.elements.Genre
import com.viktoriagavrosh.shelf.elements.bars.BottomNavigateBar
import com.viktoriagavrosh.shelf.elements.bars.HomeNavigationRail
import com.viktoriagavrosh.shelf.model.Book
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display different screens depending on window size
 */
@Composable
fun HomeScreen(
    windowSize: WindowWidthSizeClass,
    onCardClick: (Book) -> Unit,
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ShelfViewModel = hiltViewModel(
        creationCallback = { factory: ShelfViewModel.ShelfViewModelFactory ->
            factory.create(genreName = "fairy")  // TODO 111
        }
    ),
) {
    /*  TODO 111
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val screenState by viewModel.screenState.collectAsStateWithLifecycle(HomeScreenState.None())

    HomeScreen(
        uiState = uiState,
        screenState = screenState,
        windowSize = windowSize,
        onCardClick = onCardClick,
        onTabClick = viewModel::updateScreenState,
        onTopBarHeartClick = viewModel::updateFavoriteTalesList,
        onHeartClick = viewModel::updateTaleFavorite,
        onSettingsClick = onSettingsClick,
        modifier = modifier,
    )

     */
}
/*
@Composable
internal fun HomeScreen(
    uiState: TalesListUiState,
    screenState: HomeScreenState,
    windowSize: WindowWidthSizeClass,
    onCardClick: (Book) -> Unit,
    onTabClick: (Genre) -> Unit,
    onTopBarHeartClick: () -> Unit,
    onHeartClick: (Book) -> Unit,
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (windowSize == WindowWidthSizeClass.Compact) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            ContentScreen(
                screenState = screenState,
                topBarTextId = uiState.genre.textId,
                isFavoriteTalesList = uiState.isFavoriteTalesShown,
                onHeartClick = onHeartClick,
                onTopBarHeartClick = onTopBarHeartClick,
                isCompactScreen = true,
                onCardClick = onCardClick,
                onTabClick = onTabClick,
                onSettingsClick = onSettingsClick,
                modifier = Modifier.weight(1F)
            )
            BottomNavigateBar(
                selectedGenre = uiState.genre,
                onTabClick = onTabClick,
                modifier = Modifier.fillMaxWidth()
            )
        }
    } else {
        Row(modifier = modifier) {
            HomeNavigationRail(
                selectedGenre = uiState.genre,
                onTabClick = onTabClick,
                modifier = Modifier.fillMaxHeight()
            )
            ContentScreen(
                screenState = screenState,
                topBarTextId = uiState.genre.textId,
                isFavoriteTalesList = uiState.isFavoriteTalesShown,
                onHeartClick = onHeartClick,
                onTopBarHeartClick = onTopBarHeartClick,
                onCardClick = onCardClick,
                onTabClick = onTabClick,
                onSettingsClick = onSettingsClick,
                isCompactScreen = false,
            )
        }
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CompactHomeScreenPreview() {
    FairyTalesTheme {
        HomeScreen(
            uiState = TalesListUiState(),
            screenState = HomeScreenState.Success(
                List(4) {
                    Book(
                        id = it,
                        title = "title",
                    )
                }
            ),
            windowSize = WindowWidthSizeClass.Compact,
            onCardClick = {},
            onTabClick = {},
            onTopBarHeartClick = {},
            onHeartClick = {},
            onSettingsClick = {},
        )
    }
}

@Preview(name = "Light", widthDp = 700)
@Preview(name = "Dark", widthDp = 700, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MediumHomeScreenPreview() {
    FairyTalesTheme {
        HomeScreen(
            uiState = TalesListUiState(),
            screenState = HomeScreenState.Success(
                List(4) {
                    Book(
                        id = it,
                        title = "title",
                    )
                }
            ),
            windowSize = WindowWidthSizeClass.Medium,
            onCardClick = {},
            onTabClick = {},
            onTopBarHeartClick = {},
            onHeartClick = {},
            onSettingsClick = {},
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ExpandedHomeScreenPreview() {
    FairyTalesTheme {
        HomeScreen(
            uiState = TalesListUiState(),
            screenState = HomeScreenState.Success(
                List(4) {
                    Book(
                        id = it,
                        title = "title",
                    )
                }
            ),
            windowSize = WindowWidthSizeClass.Expanded,
            onCardClick = {},
            onTabClick = {},
            onTopBarHeartClick = {},
            onHeartClick = {},
            onSettingsClick = {},
        )
    }
}


 */
