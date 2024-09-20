package com.viktoriagavrosh.shelf

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.viktoriagavrosh.repositories.utils.ShelfGenre
import com.viktoriagavrosh.repositories.utils.toShelfGenre
import com.viktoriagavrosh.shelf.elements.ContentScreen
import com.viktoriagavrosh.shelf.model.Book
import com.viktoriagavrosh.shelf.utils.Tabs
import com.viktoriagavrosh.uikit.ErrorScreen
import com.viktoriagavrosh.uikit.utils.ScreenState
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display different screens depending on window size
 */
@Composable
fun ShelfScreen(
    genreName: String,
    isVerticalScreen: Boolean,
    onCardClick: (Int) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: ShelfViewModel = hiltViewModel(
        creationCallback = { factory: ShelfViewModel.ShelfViewModelFactory ->
            factory.create(genreName = genreName)
        }
    )

    val screenState by viewModel.screenState.collectAsStateWithLifecycle()

    ShelfScreen(
        screenState = screenState,
        genre = genreName.toShelfGenre(),
        tabs = viewModel.tabs,
        isVerticalScreen = isVerticalScreen,
        onCardClick = onCardClick,
        onTabClick = viewModel::updateScreenState,
        onBackClick = onBackClick,
        onHeartClick = viewModel::updateBookFavorite,
        onErrorButtonClick = viewModel::updateScreenState,
        modifier = modifier,
    )
}

@Composable
internal fun ShelfScreen(
    screenState: ScreenState<List<Book>>,
    genre: ShelfGenre,
    tabs: List<Tabs>,
    isVerticalScreen: Boolean,
    onCardClick: (Int) -> Unit,
    onTabClick: (ShelfGenre) -> Unit,
    onBackClick: () -> Unit,
    onHeartClick: (Book) -> Unit,
    onErrorButtonClick: (ShelfGenre) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (screenState) {
        is ScreenState.None -> {}
        is ScreenState.Error -> {
            ErrorScreen(
                onButtonClick = { onErrorButtonClick(genre) },
                modifier = modifier,
            )
        }

        is ScreenState.Success -> {
            ContentScreen(
                books = screenState.data ?: emptyList(),
                genre = genre,
                tabs = tabs,
                isVerticalScreen = isVerticalScreen,
                onCardClick = onCardClick,
                onTabClick = onTabClick,
                onBackClick = onBackClick,
                onHeartClick = onHeartClick,
                modifier = modifier,
            )
        }
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CompactHomeScreenPreview() {
    FairyTalesTheme {
        ShelfScreen(
            screenState = ScreenState.Success(
                List(4) {
                    Book(
                        id = it,
                        title = "title",
                        imageUrl = "",
                    )
                }
            ),
            genre = ShelfGenre.Folks.Poem,
            tabs = Tabs.FolkTab.entries,
            isVerticalScreen = true,
            onCardClick = {},
            onTabClick = {},
            onBackClick = {},
            onHeartClick = {},
            onErrorButtonClick = {},
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ExpandedHomeScreenPreview() {
    FairyTalesTheme {
        ShelfScreen(
            screenState = ScreenState.Success(
                List(4) {
                    Book(
                        id = it,
                        title = "title",
                        imageUrl = "",
                    )
                }
            ),
            genre = ShelfGenre.Folks.Poem,
            tabs = Tabs.FolkTab.entries,
            isVerticalScreen = false,
            onCardClick = {},
            onTabClick = {},
            onBackClick = {},
            onHeartClick = {},
            onErrorButtonClick = {},
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalErrorShelfScreenPreview() {
    FairyTalesTheme {
        ShelfScreen(
            screenState = ScreenState.Error(),
            genre = ShelfGenre.Folks.Poem,
            tabs = emptyList(),
            isVerticalScreen = true,
            onCardClick = {},
            onTabClick = {},
            onBackClick = {},
            onHeartClick = {},
            onErrorButtonClick = {},
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HorizontalErrorContentScreenPreview() {
    FairyTalesTheme {
        ShelfScreen(
            screenState = ScreenState.Error(),
            genre = ShelfGenre.Folks.Poem,
            tabs = emptyList(),
            isVerticalScreen = true,
            onCardClick = {},
            onTabClick = {},
            onBackClick = {},
            onHeartClick = {},
            onErrorButtonClick = {},
            modifier = Modifier.fillMaxSize(),
        )
    }
}
