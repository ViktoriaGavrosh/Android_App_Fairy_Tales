package com.viktoriagavrosh.reader.elements

import android.content.res.Configuration
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
    bookProvider: () -> ReadBook,
    textSizeProvider: () -> Float,
    isVerticalScreen: Boolean,
    onBackClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onInfoClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)

    val title = bookProvider().title
    val topBarTitle = if (isVerticalScreen) {
        "${title.take(8)}..."
    } else {
        title
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            ScreenTopBar(
                text = topBarTitle,
                scrollBehavior = scrollBehavior,
                isSettingsIconShow = true,
                isBackIconShow = true,
                isInfoShow = bookProvider().genre !is ShelfGenre.Folks,
                onSettingsClick = onSettingsClick,
                onBackClick = onBackClick,
                onInfoClick = { onInfoClick() },
                modifier = Modifier.fillMaxWidth(),
            )
        },
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
    ) { paddingValues ->
        if (isVerticalScreen) {
            VerticalReaderContent(
                bookProvider = bookProvider,
                textSizeProvider = textSizeProvider,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(paddingValues)
                    .nestedScroll(scrollBehavior.nestedScrollConnection),
            )
        } else {
            HorizontalReaderContent(
                bookProvider = bookProvider,
                textSizeProvider = textSizeProvider,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(paddingValues)
                    .nestedScroll(scrollBehavior.nestedScrollConnection),
            )
        }
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalContentScreenPreview() {
    FairyTalesTheme {
        ContentReaderScreen(
            bookProvider = {
                ReadBook(
                    text = "Text",
                    title = "Title",
                    genre = ShelfGenre.Nights
                )
            },
            textSizeProvider = { 24.0F },
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
            bookProvider = {
                ReadBook(
                    text = "Text",
                    title = "Title",
                    genre = ShelfGenre.Folks.Poem
                )
            },
            textSizeProvider = { 24.0F },
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
            bookProvider = {
                ReadBook(
                    text = "Text",
                    title = "Title",
                    genre = ShelfGenre.Nights
                )
            },
            textSizeProvider = { 24.0F },
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
            bookProvider = {
                ReadBook(
                    text = "Text",
                    title = "Title",
                    genre = ShelfGenre.Folks.Poem,
                )
            },
            textSizeProvider = { 24.0F },
            isVerticalScreen = false,
            onBackClick = {},
            onInfoClick = {},
            onSettingsClick = {}
        )
    }
}
