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
 * Composable for displaying library menu
 *
 * @param isVerticalScreen describes screen orientation
 * @param onTaleClick callback that is executed when tale button is clicked
 * @param onRiddleClick callback that is executed when riddle button is clicked
 * @param onFolkClick callback that is executed when folk button is clicked
 * @param onAddTaleClick callback that is executed when add tale button is clicked
 * @param onRandomClick callback that is executed when random button is clicked
 * @param onBackClick callback that is executed when back button is clicked
 * @param modifier the modifier to be applied to this layout node
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

/**
 * Composable for displaying library menu
 *
 * @param isErrorProvider provides boolean. If true ErrorScreen will be shown
 * @param isVerticalScreen describes screen orientation
 * @param onTaleClick callback that is executed when tale button is clicked
 * @param onRiddleClick callback that is executed when riddle button is clicked
 * @param onFolkClick callback that is executed when folk button is clicked
 * @param onAddTaleClick callback that is executed when add tale button is clicked
 * @param onRandomClick callback that is executed when random button is clicked
 * @param onBackClick callback that is executed when back button is clicked
 * @param onErrorButtonClick callback that is executed when button on ErrorScreen is clicked
 * @param modifier the modifier to be applied to this layout node
 */
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
