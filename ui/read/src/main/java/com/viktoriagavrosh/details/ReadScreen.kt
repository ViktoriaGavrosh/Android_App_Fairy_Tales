package com.viktoriagavrosh.details

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.viktoriagavrosh.details.elements.ContentReadScreen
import com.viktoriagavrosh.details.model.ReadBook
import com.viktoriagavrosh.repositories.utils.ShelfGenre
import com.viktoriagavrosh.uikit.ErrorScreen
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display details of selected [ReadBook]
 */
@Composable
fun ReadScreen(
    bookId: Int,
    genre: ShelfGenre,
    isVerticalScreen: Boolean,
    onBackClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onInfoClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: ReadViewModel = hiltViewModel(
        creationCallback = { factory: ReadViewModel.ReadViewModelFactory ->
            factory.create(bookId, genre)
        }
    )
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ReadScreen(
        book = uiState.book,
        textSize = uiState.textSize,
        isError = uiState.isError,
        isVerticalScreen = isVerticalScreen,
        onBackClick = onBackClick,
        onSettingsClick = onSettingsClick,
        onInfoClick = onInfoClick,
        onErrorButtonClick = viewModel::initUiState,
        modifier = modifier
    )
}

@Composable
internal fun ReadScreen(
    book: ReadBook,
    textSize: Float,
    isError: Boolean,
    isVerticalScreen: Boolean,
    onBackClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onInfoClick: (Int) -> Unit,
    onErrorButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (isError) {
        ErrorScreen(
            onButtonClick = onErrorButtonClick,
            modifier = modifier,
        )
    } else {
        ContentReadScreen(
            book = book,
            textSize = textSize,
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
private fun VerticalReadScreenPreview() {
    FairyTalesTheme {
        ReadScreen(
            book = ReadBook(
                title = "title",
                text = "text"
            ),
            textSize = 24.0F,
            isError = false,
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
private fun VerticalTaleReadScreenPreview() {
    FairyTalesTheme {
        ReadScreen(
            book = ReadBook(
                title = "title",
                text = "text"
            ),
            textSize = 24.0F,
            isError = false,
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
private fun VerticalErrorReadScreenPreview() {
    FairyTalesTheme {
        ReadScreen(
            book = ReadBook(
                title = "title",
                text = "text"
            ),
            textSize = 24.0F,
            isError = true,
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
        ReadScreen(
            book = ReadBook(
                title = "title",
                text = "text"
            ),
            textSize = 24.0F,
            isError = false,
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
private fun HorizontalInfoReadScreenPreview() {
    FairyTalesTheme {
        ReadScreen(
            book = ReadBook(
                title = "title",
                text = "text"
            ),
            textSize = 24.0F,
            isError = false,
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
private fun HorizontalErrorReadScreenPreview() {
    FairyTalesTheme {
        ReadScreen(
            book = ReadBook(
                title = "title",
                text = "text"
            ),
            textSize = 24.0F,
            isError = true,
            isVerticalScreen = true,
            onBackClick = {},
            onSettingsClick = {},
            onInfoClick = {},
            onErrorButtonClick = {},
        )
    }
}
