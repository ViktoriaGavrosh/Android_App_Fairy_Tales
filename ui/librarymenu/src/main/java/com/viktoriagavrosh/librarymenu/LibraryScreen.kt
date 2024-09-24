package com.viktoriagavrosh.librarymenu

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.viktoriagavrosh.librarymenu.elements.ContentLibraryScreen
import com.viktoriagavrosh.uikit.ErrorScreen
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display different screens depending on window size
 */
@Composable
fun LibraryScreen(
    isVerticalScreen: Boolean,
    onTaleClick: () -> Unit,
    onRiddleClick: () -> Unit,
    onFolkClick: () -> Unit,
    onAddTaleClick: () -> Unit,
    onRandomClick: (Int) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: LibraryViewModel = hiltViewModel()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LibraryScreen(
        isErrorProvider = { uiState.isError },
        isVerticalScreen = isVerticalScreen,
        onTaleClick = onTaleClick,
        onRiddleClick = onRiddleClick,
        onFolkClick = onFolkClick,
        onAddTaleClick = onAddTaleClick,
        onRandomClick = {
            viewModel.updateRandomTale()
            viewModel.updateLastTale()
            onRandomClick(uiState.randomTaleId)
        },
        onBackClick = onBackClick,
        onErrorButtonClick = { viewModel.initLibraryUiState() },
        modifier = modifier,
    )
}

@Composable
internal fun LibraryScreen(
    isErrorProvider: () -> Boolean,
    isVerticalScreen: Boolean,
    onTaleClick: () -> Unit,
    onRiddleClick: () -> Unit,
    onFolkClick: () -> Unit,
    onAddTaleClick: () -> Unit,
    onRandomClick: () -> Unit,
    onBackClick: () -> Unit,
    onErrorButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (isErrorProvider()) {
        ErrorScreen(
            onButtonClick = onErrorButtonClick,
            modifier = modifier
        )
    } else {
        ContentLibraryScreen(
            isVerticalScreen = isVerticalScreen,
            onRandomClick = onRandomClick,
            onRiddleClick = onRiddleClick,
            onBackClick = onBackClick,
            onFolkClick = onFolkClick,
            onTaleClick = onTaleClick,
            onAddTaleClick = onAddTaleClick,
            modifier = modifier,
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalLibraryScreenPreview() {
    FairyTalesTheme {
        LibraryScreen(
            isErrorProvider = { false },
            isVerticalScreen = true,
            onRandomClick = {},
            onBackClick = {},
            onErrorButtonClick = {},
            onRiddleClick = {},
            onFolkClick = {},
            onTaleClick = {},
            onAddTaleClick = {},
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HorizontalLibraryScreenPreview() {
    FairyTalesTheme {
        LibraryScreen(
            isErrorProvider = { false },
            isVerticalScreen = false,
            onRandomClick = {},
            onBackClick = {},
            onErrorButtonClick = {},
            onRiddleClick = {},
            onFolkClick = {},
            onTaleClick = {},
            onAddTaleClick = {},
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ErrorVerticalLibraryScreenPreview() {
    FairyTalesTheme {
        LibraryScreen(
            isErrorProvider = { true },
            isVerticalScreen = true,
            onRandomClick = {},
            onBackClick = {},
            onErrorButtonClick = {},
            onRiddleClick = {},
            onFolkClick = {},
            onTaleClick = {},
            onAddTaleClick = {},
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ErrorHorizontalLibraryScreenPreview() {
    FairyTalesTheme {
        LibraryScreen(
            isErrorProvider = { true },
            isVerticalScreen = false,
            onRandomClick = {},
            onBackClick = {},
            onErrorButtonClick = {},
            onRiddleClick = {},
            onFolkClick = {},
            onTaleClick = {},
            onAddTaleClick = {},
        )
    }
}
