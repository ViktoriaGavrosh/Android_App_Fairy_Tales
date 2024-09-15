package com.viktoriagavrosh.shelf.elements

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.shelf.R
import com.viktoriagavrosh.shelf.model.Book
import com.viktoriagavrosh.uikit.ItemCard
import com.viktoriagavrosh.uikit.ScreenTopBar
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display a list of [Book]-s on compact and medium screens
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Bookshelf(
    books: List<Book>,
    topBarTitle: String,
    isVerticalScreen: Boolean,
    isHeartShow: Boolean,
    onCardClick: (Int) -> Unit,
    onBackClick: () -> Unit,
    onHeartClick: (Book) -> Unit,
    modifier: Modifier = Modifier,
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)

    Scaffold(
        modifier = modifier,
        topBar = {
            ScreenTopBar(
                text = topBarTitle,
                scrollBehavior = scrollBehavior,
                modifier = Modifier.fillMaxWidth(),
                onBackClick = onBackClick,
            )
        }
    ) { paddingValues ->
        if (isVerticalScreen) {
            LazyColumn(
                contentPadding = paddingValues,
                modifier = Modifier
            ) {
                items(books) { book ->
                    ItemCard(
                        title = book.title,
                        imageUrl = book.imageUrl,
                        modifier = Modifier
                            .padding(dimensionResource(id = R.dimen.padding_medium))
                            .testTag(book.id.toString()),
                        isFavorite = book.isFavorite,
                        isHeartShow = isHeartShow,
                        onCardClick = { onCardClick(book.id) },
                        onHeartClick = { onHeartClick(book) },
                    )
                }
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells
                    .Adaptive(minSize = dimensionResource(id = R.dimen.card_min_size_in_grid)),
                contentPadding = paddingValues,
                modifier = modifier
            ) {
                items(books) { book ->
                    ItemCard(
                        title = book.title,
                        imageUrl = book.imageUrl,
                        modifier = Modifier
                            .padding(dimensionResource(id = R.dimen.padding_medium))
                            .testTag(book.id.toString()),   // TODO 111
                        isFavorite = book.isFavorite,
                        isHeartShow = isHeartShow,
                        minLineText = 2,
                        onCardClick = { onCardClick(book.id) },
                        onHeartClick = { onHeartClick(book) },
                    )
                }
            }
        }
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalListTalesPreview() {
    FairyTalesTheme {
        Bookshelf(
            books = List(4) {
                Book(
                    title = "Story"
                )
            },
            topBarTitle = "Title",
            isVerticalScreen = true,
            isHeartShow = true,
            onCardClick = {},
            onBackClick = {},
            onHeartClick = {}
        )
    }
}


@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HorizontalListTalesPreview() {
    FairyTalesTheme {
        Bookshelf(
            books = List(4) {
                Book(
                    title = "Story"
                )
            },
            topBarTitle = "Title",
            isVerticalScreen = false,
            isHeartShow = true,
            onCardClick = {},
            onBackClick = {},
            onHeartClick = {}
        )
    }
}
