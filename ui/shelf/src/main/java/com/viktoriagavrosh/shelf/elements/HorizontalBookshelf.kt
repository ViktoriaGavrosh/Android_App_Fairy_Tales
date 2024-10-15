package com.viktoriagavrosh.shelf.elements

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.viktoriagavrosh.shelf.model.Book
import com.viktoriagavrosh.uikit.ItemCard
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display ShelfScreen content (horizontal screen orientation)
 *
 * @param paddingValues describes a padding to be applied along the edges inside a box.
 * @param booksProvider provides [Book]'s
 * @param isHeartShow if true, heart on card show
 * @param isBlurImages if true, image on card is blur
 * @param onCardClick callback that is executed when card is clicked
 * @param onHeartClick callback that is executed when heart on card is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
internal fun HorizontalBookshelf(
    paddingValues: PaddingValues,
    booksProvider: () -> List<Book>,
    isHeartShow: Boolean,
    isBlurImages: Boolean,
    onCardClick: (Int) -> Unit,
    onHeartClick: (Book) -> Unit,
    modifier: Modifier = Modifier,
) {
    val books = booksProvider()

    LazyVerticalGrid(
        columns = GridCells
            .Adaptive(minSize = dimensionResource(id = R.dimen.card_min_size_in_grid)),
        contentPadding = paddingValues,
        modifier = modifier,
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
                isBlur = isBlurImages,
                minLineText = 2,
                onCardClick = { onCardClick(book.id) },
                onHeartClick = { onHeartClick(book) },
            )
        }
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HorizontalBookshelfPreview() {
    FairyTalesTheme {
        HorizontalBookshelf(
            paddingValues = PaddingValues(0.dp),
            booksProvider = {
                List(4) {
                    Book(
                        title = "Story",
                        imageUrl = "",
                    )
                }
            },
            isHeartShow = true,
            isBlurImages = false,
            onCardClick = {},
            onHeartClick = {}
        )
    }
}

