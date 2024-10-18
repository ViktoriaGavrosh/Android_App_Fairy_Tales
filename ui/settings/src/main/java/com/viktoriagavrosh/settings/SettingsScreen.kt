package com.viktoriagavrosh.settings

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.viktoriagavrosh.settings.elements.ContentSettingsScreen
import com.viktoriagavrosh.uikit.ErrorScreen
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable for displaying settings
 *
 * @param isVerticalScreen describes screen orientation
 * @param onBackClick callback that is executed when back button is clicked
 * @param modifier the modifier to be applied to this layout node
 * @param viewModel instance of [SettingsViewModel]
 */
@Composable
fun SettingsScreen(
    isVerticalScreen: Boolean,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SettingsScreen(
        textSizeProvider = { uiState.textSize },
        isErrorProvider = { uiState.isError },
        isVerticalScreen = isVerticalScreen,
        onTextSizeUpdate = viewModel::updateTextSize,
        onBackClick = onBackClick,
        onErrorButtonClick = viewModel::initSettingsState,
        modifier = modifier,
    )
}

/**
 * Composable for displaying settings
 *
 * @param textSizeProvider provides text size value
 * @param isErrorProvider provides boolean. If true ErrorScreen will be shown
 * @param isVerticalScreen describes screen orientation
 * @param onTextSizeUpdate callback that is executed when text size is changed
 * @param onBackClick callback that is executed when back button is clicked
 * @param onErrorButtonClick callback that is executed when button on ErrorScreen is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
internal fun SettingsScreen(
    textSizeProvider: () -> Float,
    isErrorProvider: () -> Boolean,
    isVerticalScreen: Boolean,
    onTextSizeUpdate: (Float) -> Unit,
    onBackClick: () -> Unit,
    onErrorButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (isErrorProvider()) {
        ErrorScreen(
            onButtonClick = onErrorButtonClick,
            modifier = modifier,
        )
    } else {
        ContentSettingsScreen(
            textSizeProvider = textSizeProvider,
            isVerticalScreen = isVerticalScreen,
            onTextSizeUpdate = onTextSizeUpdate,
            onBackClick = onBackClick,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = dimensionResource(id = R.dimen.padding_medium)),
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalSettingsScreenPreview() {
    FairyTalesTheme {
        SettingsScreen(
            textSizeProvider = { 24.0F },
            isErrorProvider = { false },
            isVerticalScreen = true,
            onTextSizeUpdate = {},
            onBackClick = {},
            onErrorButtonClick = {},
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HorizontalSettingsScreenPreview() {
    FairyTalesTheme {
        SettingsScreen(
            textSizeProvider = { 24.0F },
            isErrorProvider = { false },
            isVerticalScreen = false,
            onTextSizeUpdate = {},
            onBackClick = {},
            onErrorButtonClick = {},
        )
    }
}

