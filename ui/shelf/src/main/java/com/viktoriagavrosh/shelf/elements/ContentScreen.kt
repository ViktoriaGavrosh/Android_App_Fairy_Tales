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
import com.viktoriagavrosh.shelf.R
import com.viktoriagavrosh.shelf.model.Book
import com.viktoriagavrosh.shelf.utils.Tabs
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display [Book] list or grid screen
 */
@Composable
internal fun ContentScreen(
    books: List<Book>,
    genre: ShelfGenre,
    tabs: List<Tabs>,
    isVerticalScreen: Boolean,
    onCardClick: (Int) -> Unit,
    onTabClick: (ShelfGenre) -> Unit,
    onBackClick: () -> Unit,
    onHeartClick: (Book) -> Unit,
    modifier: Modifier = Modifier,
) {
    val topBarTitle = stringResource(getTitleId(genre))

    val isTabsShow =
        (genre is ShelfGenre.Tales) || (genre is ShelfGenre.Folks)   // TODO check how it works 111
    val isHeartShow = genre is ShelfGenre.Tales

    if (isVerticalScreen) {
        Column(
            modifier = Modifier.fillMaxSize()

        ) {
            Bookshelf(
                books = books,
                topBarTitle = topBarTitle,
                isVerticalScreen = true,
                isHeartShow = isHeartShow,
                onCardClick = onCardClick,
                onBackClick = onBackClick,
                onHeartClick = onHeartClick,
                modifier = Modifier.weight(1F)
            )
            if (isTabsShow) {
                BottomTabBar(
                    tabs = tabs,
                    selectedTab = genre,
                    onTabClick = onTabClick,
                    modifier = Modifier.fillMaxWidth()
                )
            }

        }
    } else {
        Row(modifier = modifier) {
            if (isTabsShow) {
                LeftTabRail(
                    tabs = tabs,
                    selectedTab = genre,
                    onTabClick = onTabClick,
                    modifier = Modifier.fillMaxHeight()
                )
            }
            Bookshelf(
                books = books,
                topBarTitle = topBarTitle,
                isVerticalScreen = false,
                isHeartShow = isHeartShow,
                onCardClick = onCardClick,
                onBackClick = onBackClick,
                onHeartClick = onHeartClick,
                // modifier = Modifier.weight(1F)    TODO 111
            )
        }
    }
}

private fun getTitleId(genre: ShelfGenre): Int {
    return when (genre) {
        ShelfGenre.Tales.Animal -> Tabs.TaleTab.Animal.textId
        ShelfGenre.Tales.Fairy -> Tabs.TaleTab.Fairy.textId
        ShelfGenre.Tales.People -> Tabs.TaleTab.People.textId
        ShelfGenre.Folks.Poem -> Tabs.FolkTab.Poem.textId
        ShelfGenre.Folks.Counting -> Tabs.FolkTab.Counting.textId
        ShelfGenre.Folks.Lullaby -> Tabs.FolkTab.Lullaby.textId
        ShelfGenre.Riddles -> R.string.title_riddles
        ShelfGenre.Nights -> R.string.title_night
        ShelfGenre.Favorites -> R.string.title_favorite
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TabsVerticalContentScreenPreview() {
    FairyTalesTheme {
        ContentScreen(
            books = List(4) {
                Book(
                    title = "Poem",
                    imageUrl = "",
                )
            },
            genre = ShelfGenre.Folks.Poem,
            tabs = Tabs.FolkTab.entries,
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
            books = List(4) {
                Book(
                    title = "Tale",
                    imageUrl = "",
                )
            },
            genre = ShelfGenre.Nights,
            tabs = emptyList(),
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
            books = List(4) {
                Book(
                    title = "Poem",
                    imageUrl = "",
                )
            },
            genre = ShelfGenre.Nights,
            tabs = emptyList(),
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
            books = List(4) {
                Book(
                    title = "Poem",
                    imageUrl = "",
                )
            },
            genre = ShelfGenre.Folks.Poem,
            tabs = Tabs.FolkTab.entries,
            isVerticalScreen = false,
            onCardClick = {},
            onTabClick = {},
            onBackClick = {},
            onHeartClick = {},
        )
    }
}
