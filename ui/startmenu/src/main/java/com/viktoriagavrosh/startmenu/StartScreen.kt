package com.viktoriagavrosh.startmenu

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.viktoriagavrosh.startmenu.elements.ContentStartScreen
import com.viktoriagavrosh.uikit.ErrorScreen
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display different screens depending on window size
 */
@Composable
fun StartScreen(
    isVerticalScreen: Boolean,
    onFavoriteClick: () -> Unit,
    onNightClick: () -> Unit,
    onLibraryClick: () -> Unit,
    onLastTaleClick: (Int) -> Unit,
    onRandomClick: (Int) -> Unit,
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: StartViewModel = hiltViewModel()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    StartScreen(
        isError = uiState.isError,
        isVerticalScreen = isVerticalScreen,
        onFavoriteClick = onFavoriteClick,
        onNightClick = onNightClick,
        onLibraryClick = onLibraryClick,
        onLastTaleClick = { onLastTaleClick(uiState.lastTaleId) },
        onRandomClick = {
            viewModel.updateRandomTale()
            viewModel.updateLastTale()
            onRandomClick(uiState.randomTaleId)
        },
        onSettingsClick = onSettingsClick,
        onErrorButtonClick = viewModel::initStartUiState,
        modifier = modifier,
    )
}

@Composable
internal fun StartScreen(
    isError: Boolean,
    isVerticalScreen: Boolean,
    onFavoriteClick: () -> Unit,
    onNightClick: () -> Unit,
    onLibraryClick: () -> Unit,
    onLastTaleClick: () -> Unit,
    onRandomClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onErrorButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (isError) {
        ErrorScreen(
            onButtonClick = onErrorButtonClick,
            modifier = modifier
        )
    } else {
        ContentStartScreen(
            isVerticalScreen = isVerticalScreen,
            onFavoriteClick = onFavoriteClick,
            onNightClick = onNightClick,
            onLibraryClick = onLibraryClick,
            onLastTaleClick = onLastTaleClick,
            onRandomClick = onRandomClick,
            onSettingsClick = onSettingsClick,
            modifier = modifier,
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalStartScreenPreview() {
    FairyTalesTheme {
        StartScreen(
            isError = false,
            isVerticalScreen = true,
            onSettingsClick = {},
            onErrorButtonClick = {},
            onNightClick = {},
            onRandomClick = {},
            onLibraryClick = {},
            onFavoriteClick = {},
            onLastTaleClick = {},
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HorizontalStartScreenPreview() {
    FairyTalesTheme {
        StartScreen(
            isError = false,
            isVerticalScreen = false,
            onSettingsClick = {},
            onErrorButtonClick = {},
            onNightClick = {},
            onRandomClick = {},
            onLibraryClick = {},
            onFavoriteClick = {},
            onLastTaleClick = {},
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ErrorVerticalStartScreenPreview() {
    FairyTalesTheme {
        StartScreen(
            isError = true,
            isVerticalScreen = true,
            onSettingsClick = {},
            onErrorButtonClick = {},
            onNightClick = {},
            onRandomClick = {},
            onLibraryClick = {},
            onFavoriteClick = {},
            onLastTaleClick = {},
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ErrorHorizontalStartScreenPreview() {
    FairyTalesTheme {
        StartScreen(
            isError = true,
            isVerticalScreen = false,
            onSettingsClick = {},
            onErrorButtonClick = {},
            onNightClick = {},
            onRandomClick = {},
            onLibraryClick = {},
            onFavoriteClick = {},
            onLastTaleClick = {},
        )
    }
}
