package com.viktoriagavrosh.startmenu

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.viktoriagavrosh.startmenu.elements.ContentStartScreen
import com.viktoriagavrosh.uikit.ErrorScreen
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable for displaying start menu
 *
 * @param isVerticalScreen describes screen orientation
 * @param onFavoriteClick callback that is executed when favorite tale button is clicked
 * @param onNightClick callback that is executed when night tale button is clicked
 * @param onLibraryClick callback that is executed when library button is clicked
 * @param onLastTaleClick callback that is executed when last tale button is clicked
 * @param onRandomClick callback that is executed when random button is clicked
 * @param onSettingsClick callback that is executed when settings button is clicked
 * @param modifier the modifier to be applied to this layout node
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

    LaunchedEffect(Unit) {
        viewModel.initStartUiState()
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    StartScreen(
        isErrorProvider = { uiState.isError },
        isVerticalScreen = isVerticalScreen,
        onFavoriteClick = onFavoriteClick,
        onNightClick = onNightClick,
        onLibraryClick = onLibraryClick,
        onLastTaleClick = {
            onLastTaleClick(uiState.lastTaleId)
        },
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

/**
 * Composable for displaying start menu
 *
 * @param isErrorProvider provides boolean. If true ErrorScreen will be shown
 * @param isVerticalScreen describes screen orientation
 * @param onFavoriteClick callback that is executed when favorite tale button is clicked
 * @param onNightClick callback that is executed when night tale button is clicked
 * @param onLibraryClick callback that is executed when library button is clicked
 * @param onLastTaleClick callback that is executed when last tale button is clicked
 * @param onRandomClick callback that is executed when random button is clicked
 * @param onSettingsClick callback that is executed when settings button is clicked
 * @param onErrorButtonClick callback that is executed when button on ErrorScreen is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
internal fun StartScreen(
    isErrorProvider: () -> Boolean,
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
    if (isErrorProvider()) {
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
            isErrorProvider = { false },
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
            isErrorProvider = { false },
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
            isErrorProvider = { true },
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
            isErrorProvider = { true },
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
