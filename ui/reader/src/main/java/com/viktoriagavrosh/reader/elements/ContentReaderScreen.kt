package com.viktoriagavrosh.reader.elements

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.reader.model.ReadBook
import com.viktoriagavrosh.repositories.utils.ShelfGenre
import com.viktoriagavrosh.uikit.ScreenTopBar
import com.viktoriagavrosh.uitheme.FairyTalesTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ContentReaderScreen(
    book: ReadBook,
    textSize: Float,
    isVerticalScreen: Boolean,
    onBackClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onInfoClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)

    Scaffold(
        modifier = modifier.background(color = MaterialTheme.colorScheme.surfaceContainerHigh),
        topBar = {
            ScreenTopBar(
                text = book.title,
                scrollBehavior = scrollBehavior,
                isSettingsIconShow = true,
                isBackIconShow = true,
                isInfoShow = book.genre !is ShelfGenre.Folks,
                onSettingsClick = onSettingsClick,
                onBackClick = onBackClick,
                onInfoClick = { onInfoClick() },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    ) { paddingValues ->
        if (isVerticalScreen) {
            VerticalReaderContent(
                book = book,
                textSize = textSize,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(paddingValues)
                    .nestedScroll(scrollBehavior.nestedScrollConnection),
            )
        } else {
            HorizontalReaderContent(
                book = book,
                textSize = textSize,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(paddingValues)
                    .nestedScroll(scrollBehavior.nestedScrollConnection),
            )
        }
    }
}
/*




    // States are only here, otherwise scroll disappears
    val scrollState = rememberScrollState()
    var fontSize by remember {
        mutableFloatStateOf(0.0F)
    }
    if (fontSize == 0.0F) fontSize = textSize
    Column(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.surfaceContainerHigh)
            .pointerInput(Unit) {
                detectTransformGestures { _, _, zoom, _ ->
                    fontSize *= zoom
                    if (fontSize < 8) fontSize = 8.0F
                    if (fontSize > 100) fontSize = 100.0F
                    onTextSizeUpdate(fontSize)
                }
            }
            .verticalScroll(scrollState)
    ) {

        if (isExpandedScreen) {

            HorizontalDetailScreen(
                tale = tale,
                fontSize = fontSize,
                modifier = Modifier
                    .testTag(stringResource(R.string.horizontal_detail_screen))
            )

        } else {
            VerticalDetailScreen(
                tale = tale,
                fontSize = fontSize,
                modifier = Modifier

                    .testTag(stringResource(R.string.vertical_detail_screen))
            )
        }
    }
}

 */

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalContentScreenPreview() {
    FairyTalesTheme {
        ContentReaderScreen(
            book = ReadBook(
                text = "Text",
                title = "Title",
                genre = ShelfGenre.Nights
            ),
            textSize = 24.0F,
            isVerticalScreen = true,
            onBackClick = {},
            onInfoClick = {},
            onSettingsClick = {}
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun FolkVerticalContentScreenPreview() {
    FairyTalesTheme {
        ContentReaderScreen(
            book = ReadBook(
                text = "Text",
                title = "Title",
                genre = ShelfGenre.Folks.Poem
            ),
            textSize = 24.0F,
            isVerticalScreen = true,
            onBackClick = {},
            onInfoClick = {},
            onSettingsClick = {}
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HorizontalContentScreenPreview() {
    FairyTalesTheme {
        ContentReaderScreen(
            book = ReadBook(
                text = "Text",
                title = "Title",
                genre = ShelfGenre.Nights
            ),
            textSize = 24.0F,
            isVerticalScreen = false,
            onBackClick = {},
            onInfoClick = {},
            onSettingsClick = {}
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun FolkHorizontalContentScreenPreview() {
    FairyTalesTheme {
        ContentReaderScreen(
            book = ReadBook(
                text = "Text",
                title = "Title",
                genre = ShelfGenre.Folks.Poem,
            ),
            textSize = 24.0F,
            isVerticalScreen = false,
            onBackClick = {},
            onInfoClick = {},
            onSettingsClick = {}
        )
    }
}
