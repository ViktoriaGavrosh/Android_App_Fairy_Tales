package com.viktoriagavrosh.shelf.elements

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.shelf.model.Book
import com.viktoriagavrosh.uikit.ScreenTopBar
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display ShelfScreen content with topBar
 *
 * @param booksProvider provides [Book]'s
 * @param topBarTitle title for topBar
 * @param isVerticalScreen describes screen orientation
 * @param isHeartShow if true, heart on card show
 * @param isBlurImages if true, image on card is blur
 * @param onCardClick callback that is executed when card is clicked
 * @param onBackClick callback that is executed when back button is clicked
 * @param onHeartClick callback that is executed when heart on card is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Bookshelf(
    booksProvider: () -> List<Book>,
    topBarTitle: String,
    isVerticalScreen: Boolean,
    isHeartShow: Boolean,
    isBlurImages: Boolean,
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
            VerticalBookshelf(
                paddingValues = paddingValues,
                booksProvider = booksProvider,
                isHeartShow = isHeartShow,
                isBlurImages = isBlurImages,
                onCardClick = onCardClick,
                onHeartClick = onHeartClick,
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
            )
        } else {
            HorizontalBookshelf(
                paddingValues = paddingValues,
                booksProvider = booksProvider,
                isHeartShow = isHeartShow,
                isBlurImages = isBlurImages,
                onCardClick = onCardClick,
                onHeartClick = onHeartClick,
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            )
        }
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalListTalesPreview() {
    FairyTalesTheme {
        Bookshelf(
            booksProvider = {
                List(4) {
                    Book(
                        title = "Story",
                        imageUrl = "",
                    )
                }
            },
            topBarTitle = "Title",
            isVerticalScreen = true,
            isHeartShow = true,
            isBlurImages = false,
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
            booksProvider = {
                List(4) {
                    Book(
                        title = "Story",
                        imageUrl = "",
                    )
                }
            },
            topBarTitle = "Title",
            isVerticalScreen = false,
            isHeartShow = true,
            isBlurImages = false,
            onCardClick = {},
            onBackClick = {},
            onHeartClick = {}
        )
    }
}
