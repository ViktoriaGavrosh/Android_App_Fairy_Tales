package com.viktoriagavrosh.settings.elements

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uikit.ScreenTopBar
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display Settings
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ContentSettingsScreen(
    textSizeProvider: () -> Float,
    isVerticalScreen: Boolean,
    onTextSizeUpdate: (Float) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
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
        if (isVerticalScreen) {
            VerticalSettingsContent(
                textSizeProvider = textSizeProvider,
                onTextSizeUpdate = onTextSizeUpdate,
                modifier = modifier
                    .padding(paddingValues)
                    .nestedScroll(scrollBehavior.nestedScrollConnection),
            )
        } else {
            HorizontalSettingsContent(
                textSizeProvider = textSizeProvider,
                onTextSizeUpdate = onTextSizeUpdate,
                modifier = modifier
                    .padding(paddingValues)
                    .nestedScroll(scrollBehavior.nestedScrollConnection),
            )
        }
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalSettingsContentPreview() {
    FairyTalesTheme {
        ContentSettingsScreen(
            textSizeProvider = { 24.0F },
            isVerticalScreen = true,
            onTextSizeUpdate = {},
            onBackClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HorizontalSettingsContentPreview() {
    FairyTalesTheme {
        ContentSettingsScreen(
            textSizeProvider = { 24.0F },
            isVerticalScreen = false,
            onTextSizeUpdate = {},
            onBackClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}
