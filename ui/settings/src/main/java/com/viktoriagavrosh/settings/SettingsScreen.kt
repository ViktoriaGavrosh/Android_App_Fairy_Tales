package com.viktoriagavrosh.settings

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.viktoriagavrosh.settings.elements.SettingsContent
import com.viktoriagavrosh.uikit.ErrorScreen
import com.viktoriagavrosh.uikit.ScreenTopBar
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display settings
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
        textSize = uiState.textSize,
        isError = uiState.isError,
        isVerticalScreen = isVerticalScreen,
        onTextSizeUpdate = viewModel::updateTextSize,
        onBackClick = onBackClick,
        onErrorButtonClick = viewModel::initSettingsState,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SettingsScreen(
    textSize: Float,
    isError: Boolean,
    isVerticalScreen: Boolean,
    onTextSizeUpdate: (Float) -> Unit,
    onBackClick: () -> Unit,
    onErrorButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (isError) {
        ErrorScreen(
            onButtonClick = onErrorButtonClick,
            modifier = modifier,
        )
    } else {

        val topAppBarState = rememberTopAppBarState()
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)

        Scaffold(
            modifier = modifier,
            topBar = {
                ScreenTopBar(
                    text = stringResource(id = R.string.settings_title),
                    scrollBehavior = scrollBehavior,
                    onBackClick = onBackClick,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        ) { paddingValues ->
            SettingsContent(
                textSize = textSize,
                isVerticalScreen = isVerticalScreen,
                onTextSizeUpdate = onTextSizeUpdate,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = dimensionResource(id = R.dimen.padding_medium)),
            )
        }
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalSettingsScreenPreview() {
    FairyTalesTheme {
        SettingsScreen(
            textSize = 24.0F,
            isError = false,
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
            textSize = 24.0F,
            isError = false,
            isVerticalScreen = false,
            onTextSizeUpdate = {},
            onBackClick = {},
            onErrorButtonClick = {},
        )
    }
}

