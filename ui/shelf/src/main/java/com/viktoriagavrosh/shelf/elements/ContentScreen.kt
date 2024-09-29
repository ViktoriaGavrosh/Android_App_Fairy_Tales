package com.viktoriagavrosh.shelf.elements

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.repositories.utils.ShelfGenre
import com.viktoriagavrosh.shelf.model.Book
import com.viktoriagavrosh.shelf.utils.Tabs
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display [Book] list or grid screen
 */
@Composable
internal fun ContentScreen(
    booksProvider: () -> List<Book>,
    genre: ShelfGenre,
    tabsProvider: () -> List<Tabs>,
    selectedTabProvider: () -> Tabs,
    isVerticalScreen: Boolean,
    onCardClick: (Int) -> Unit,
    onTabClick: (Tabs) -> Unit,
    onBackClick: () -> Unit,
    onHeartClick: (Book) -> Unit,
    modifier: Modifier = Modifier,
) {
    val isTabsShow = tabsProvider().isNotEmpty()
    val topBarTitle = stringResource(selectedTabProvider().textId)
    val isHeartShow = genre is ShelfGenre.Tales

    if (isVerticalScreen) {
        Column(
            modifier = Modifier.fillMaxSize()

        ) {
            Bookshelf(
                booksProvider = booksProvider,
                topBarTitle = topBarTitle,
                isVerticalScreen = true,
                isHeartShow = isHeartShow,
                isBlurImages = genre is ShelfGenre.Riddles,
                onCardClick = onCardClick,
                onBackClick = onBackClick,
                onHeartClick = onHeartClick,
                modifier = Modifier.weight(1F)
            )
            if (isTabsShow) {
                BottomTabBar(
                    tabsProvider = tabsProvider,
                    selectedTabProvider = selectedTabProvider,
                    onTabClick = onTabClick,
                    modifier = Modifier.fillMaxWidth()
                )
            }

        }
    } else {
        Row(modifier = modifier) {
            if (isTabsShow) {
                LeftTabRail(
                    tabsProvider = tabsProvider,
                    selectedTabProvider = selectedTabProvider,
                    onTabClick = onTabClick,
                    modifier = Modifier.fillMaxHeight()
                )
            }
            Bookshelf(
                booksProvider = booksProvider,
                topBarTitle = topBarTitle,
                isVerticalScreen = false,
                isHeartShow = isHeartShow,
                isBlurImages = genre is ShelfGenre.Riddles,
                onCardClick = onCardClick,
                onBackClick = onBackClick,
                onHeartClick = onHeartClick,
                // modifier = Modifier.weight(1F)   TODO 111
            )
        }
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TabsVerticalContentScreenPreview() {
    FairyTalesTheme {
        ContentScreen(
            booksProvider = {
                List(4) {
                    Book(
                        title = "Poem",
                        imageUrl = "",
                    )
                }
            },
            genre = ShelfGenre.Folks.Poem,
            tabsProvider = { Tabs.FolkTab.entries },
            selectedTabProvider = { Tabs.FolkTab.Poem },
            isVerticalScreen = true,
            onCardClick = {},
            onTabClick = {},
            onBackClick = {},
            onHeartClick = {},
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalContentScreenPreview() {
    FairyTalesTheme {
        ContentScreen(
            booksProvider = {
                List(4) {
                    Book(
                        title = "Tale",
                        imageUrl = "",
                    )
                }
            },
            genre = ShelfGenre.Nights,
            tabsProvider = { emptyList() },
            selectedTabProvider = { Tabs.FolkTab.Poem },
            isVerticalScreen = true,
            onCardClick = {},
            onTabClick = {},
            onBackClick = {},
            onHeartClick = {},
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TabsHorizontalContentScreenPreview() {
    FairyTalesTheme {
        ContentScreen(
            booksProvider = {
                List(4) {
                    Book(
                        title = "Poem",
                        imageUrl = "",
                    )
                }
            },
            genre = ShelfGenre.Nights,
            tabsProvider = { emptyList() },
            selectedTabProvider = { Tabs.FolkTab.Poem },
            isVerticalScreen = false,
            onCardClick = {},
            onTabClick = {},
            onBackClick = {},
            onHeartClick = {},
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HorizontalContentScreenPreview() {
    FairyTalesTheme {
        ContentScreen(
            booksProvider = {
                List(4) {
                    Book(
                        title = "Poem",
                        imageUrl = "",
                    )
                }
            },
            genre = ShelfGenre.Folks.Poem,
            tabsProvider = { Tabs.FolkTab.entries },
            selectedTabProvider = { Tabs.FolkTab.Poem },
            isVerticalScreen = false,
            onCardClick = {},
            onTabClick = {},
            onBackClick = {},
            onHeartClick = {},
        )
    }
}
