package com.viktoriagavrosh.shelf

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.viktoriagavrosh.repositories.utils.ShelfGenre
import com.viktoriagavrosh.shelf.elements.ContentScreen
import com.viktoriagavrosh.shelf.model.Book
import com.viktoriagavrosh.shelf.utils.Tabs
import com.viktoriagavrosh.uikit.ErrorScreen
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable for displaying shelf with books
 *
 * @param genre selected genre of books
 * @param isVerticalScreen describes screen orientation
 * @param onCardClick callback that is executed when card is clicked
 * @param onBackClick callback that is executed when back button is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
fun ShelfScreen(
    genre: ShelfGenre,
    isVerticalScreen: Boolean,
    onCardClick: (Int) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: ShelfViewModel = hiltViewModel(
        creationCallback = { factory: ShelfViewModel.ShelfViewModelFactory ->
            factory.create(genre = genre)
        }
    )

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    ShelfScreen(
        booksProvider = { uiState.value.books },
        selectedTabProvider = { uiState.value.selectedTab },
        isErrorProvider = { uiState.value.isError },
        isVerticalScreen = isVerticalScreen,
        onCardClick = onCardClick,
        onTabClick = viewModel::updateScreenState,
        onBackClick = onBackClick,
        onHeartClick = viewModel::updateBookFavorite,
        onErrorButtonClick = viewModel::initScreenState,
        modifier = modifier,
    )
}

/**
 * Composable for displaying shelf with books
 *
 * @param booksProvider provides [Book]'s
 * @param selectedTabProvider provides one of [Tabs], that is shown
 * @param isErrorProvider provides boolean. If true ErrorScreen will be shown
 * @param isVerticalScreen describes screen orientation
 * @param onCardClick callback that is executed when card is clicked
 * @param onTabClick callback that is executed when one of tabs is clicked
 * @param onBackClick callback that is executed when back button is clicked
 * @param onHeartClick callback that is executed when heart on card is clicked
 * @param onErrorButtonClick callback that is executed when button on ErrorScreen is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
internal fun ShelfScreen(
    booksProvider: () -> List<Book>,
    selectedTabProvider: () -> Tabs,
    isErrorProvider: () -> Boolean,
    isVerticalScreen: Boolean,
    onCardClick: (Int) -> Unit,
    onTabClick: (Tabs) -> Unit,
    onBackClick: () -> Unit,
    onHeartClick: (Book) -> Unit,
    onErrorButtonClick: (ShelfGenre) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (isErrorProvider()) {
        ErrorScreen(
            onButtonClick = { onErrorButtonClick(selectedTabProvider().genre) },
            modifier = modifier,
        )
    } else {
        ContentScreen(
            booksProvider = booksProvider,
            selectedTabProvider = selectedTabProvider,
            isVerticalScreen = isVerticalScreen,
            onCardClick = onCardClick,
            onTabClick = onTabClick,
            onBackClick = onBackClick,
            onHeartClick = onHeartClick,
            modifier = modifier,
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CompactHomeScreenPreview() {
    FairyTalesTheme {
        ShelfScreen(
            booksProvider = {
                List(4) {
                    Book(
                        id = it,
                        title = "title",
                        imageUrl = "",
                    )
                }
            },
            isVerticalScreen = true,
            isErrorProvider = { false },
            selectedTabProvider = { Tabs.FolkTab.Poem },
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
            booksProvider = {
                List(4) {
                    Book(
                        id = it,
                        title = "title",
                        imageUrl = "",
                    )
                }
            },
            isVerticalScreen = false,
            isErrorProvider = { false },
            selectedTabProvider = { Tabs.FolkTab.Poem },
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
            booksProvider = { emptyList() },
            isVerticalScreen = true,
            isErrorProvider = { true },
            selectedTabProvider = { Tabs.FolkTab.Poem },
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
            booksProvider = { emptyList() },
            isVerticalScreen = false,
            isErrorProvider = { true },
            selectedTabProvider = { Tabs.FolkTab.Poem },
            onCardClick = {},
            onTabClick = {},
            onBackClick = {},
            onHeartClick = {},
            onErrorButtonClick = {},
            modifier = Modifier.fillMaxSize(),
        )
    }
}
