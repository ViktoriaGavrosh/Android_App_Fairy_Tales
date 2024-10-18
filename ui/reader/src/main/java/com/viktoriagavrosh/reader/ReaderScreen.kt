package com.viktoriagavrosh.reader

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.viktoriagavrosh.reader.elements.ContentReaderScreen
import com.viktoriagavrosh.reader.model.ReadBook
import com.viktoriagavrosh.repositories.utils.ShelfGenre
import com.viktoriagavrosh.uikit.ErrorScreen
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable for displaying reader
 *
 * @param bookId id of selected book
 * @param genre genre of selected book
 * @param isVerticalScreen describes screen orientation
 * @param onBackClick callback that is executed when back button is clicked
 * @param onSettingsClick callback that is executed when settings button is clicked
 * @param onInfoClick callback that is executed when info button is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
fun ReaderScreen(
    bookId: Int,
    genre: ShelfGenre,
    isVerticalScreen: Boolean,
    onBackClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onInfoClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: ReaderViewModel = hiltViewModel(
        creationCallback = { factory: ReaderViewModel.ReaderViewModelFactory ->
            factory.create(bookId, genre)
        }
    )
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val textSize = viewModel.textSize.collectAsStateWithLifecycle()

    ReaderScreen(
        bookProvider = { uiState.value.book },
        textSizeProvider = { textSize.value },
        isErrorProvider = { uiState.value.isError },
        isVerticalScreen = isVerticalScreen,
        onBackClick = onBackClick,
        onSettingsClick = onSettingsClick,
        onInfoClick = onInfoClick,
        onErrorButtonClick = viewModel::initUiState,
        modifier = modifier
    )
}

/**
 * Composable for displaying reader
 *
 * @param bookProvider provides id of selected book
 * @param textSizeProvider provides text size value
 * @param isErrorProvider provides boolean. If true ErrorScreen will be shown
 * @param isVerticalScreen describes screen orientation
 * @param onBackClick callback that is executed when back button is clicked
 * @param onSettingsClick callback that is executed when settings button is clicked
 * @param onInfoClick callback that is executed when info button is clicked
 * @param onErrorButtonClick callback that is executed when button on ErrorScreen is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
internal fun ReaderScreen(
    bookProvider: () -> ReadBook,
    textSizeProvider: () -> Float,
    isErrorProvider: () -> Boolean,
    isVerticalScreen: Boolean,
    onBackClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onInfoClick: () -> Unit,
    onErrorButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (isErrorProvider()) {
        ErrorScreen(
            onButtonClick = onErrorButtonClick,
            modifier = modifier,
        )
    } else {
        ContentReaderScreen(
            bookProvider = bookProvider,
            textSizeProvider = textSizeProvider,
            isVerticalScreen = isVerticalScreen,
            onBackClick = onBackClick,
            onSettingsClick = onSettingsClick,
            onInfoClick = onInfoClick,
            modifier = modifier,
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun FolkVerticalReadScreenPreview() {
    FairyTalesTheme {
        ReaderScreen(
            bookProvider = {
                ReadBook(
                    title = "title",
                    text = "text",
                    genre = ShelfGenre.Folks.Poem,
                )
            },
            textSizeProvider = { 24.0F },
            isErrorProvider = { false },
            isVerticalScreen = true,
            onBackClick = {},
            onSettingsClick = {},
            onInfoClick = {},
            onErrorButtonClick = {},
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalReadScreenPreview() {
    FairyTalesTheme {
        ReaderScreen(
            bookProvider = {
                ReadBook(
                    title = "title",
                    text = "text",
                )
            },
            textSizeProvider = { 24.0F },
            isErrorProvider = { false },
            isVerticalScreen = true,
            onBackClick = {},
            onSettingsClick = {},
            onInfoClick = {},
            onErrorButtonClick = {},
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ErrorVerticalReadScreenPreview() {
    FairyTalesTheme {
        ReaderScreen(
            bookProvider = {
                ReadBook(
                    title = "title",
                    text = "text"
                )
            },
            textSizeProvider = { 24.0F },
            isErrorProvider = { true },
            isVerticalScreen = true,
            onBackClick = {},
            onSettingsClick = {},
            onInfoClick = {},
            onErrorButtonClick = {},
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HorizontalReadScreenPreview() {
    FairyTalesTheme {
        ReaderScreen(
            bookProvider = {
                ReadBook(
                    title = "title",
                    text = "text"
                )
            },
            textSizeProvider = { 24.0F },
            isErrorProvider = { false },
            isVerticalScreen = false,
            onBackClick = {},
            onSettingsClick = {},
            onInfoClick = {},
            onErrorButtonClick = {},
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun FolkHorizontalReadScreenPreview() {
    FairyTalesTheme {
        ReaderScreen(
            bookProvider = {
                ReadBook(
                    title = "title",
                    text = "text",
                    genre = ShelfGenre.Folks.Poem,
                )
            },
            textSizeProvider = { 24.0F },
            isErrorProvider = { false },
            isVerticalScreen = false,
            onBackClick = {},
            onSettingsClick = {},
            onInfoClick = {},
            onErrorButtonClick = {},
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ErrorHorizontalReadScreenPreview() {
    FairyTalesTheme {
        ReaderScreen(
            bookProvider = {
                ReadBook(
                    title = "title",
                    text = "text"
                )
            },
            textSizeProvider = { 24.0F },
            isErrorProvider = { true },
            isVerticalScreen = false,
            onBackClick = {},
            onSettingsClick = {},
            onInfoClick = {},
            onErrorButtonClick = {},
        )
    }
}
