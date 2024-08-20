package com.viktoriagavrosh.fairytales.ui.settingsscreen

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme

/**
 * Composable to display settings
 */
@Composable
fun SettingsScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SettingsScreen(
        uiState = uiState,
        onTextSizeUpdate = viewModel::updateTextSize,
        onBackClick = onBackClick,
        modifier = modifier,
    )
}

@Composable
fun SettingsScreen(
    uiState: SettingsState,
    onTextSizeUpdate: (Float) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            SettingsTopBar(
                text = stringResource(id = R.string.settings_title),
                onBackClick = onBackClick,
                modifier = Modifier.fillMaxWidth()
            )
        }
    ) { paddingValues ->
        SettingsContent(
            textSize = uiState.textSize,
            onTextSizeUpdate = onTextSizeUpdate,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = dimensionResource(id = R.dimen.padding_medium)),
        )
    }
}

/**
 * App bar to display back navigation
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsTopBar(
    text: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            Text(
                text = text,
                style = MaterialTheme.typography.displaySmall,
            )
        },
        modifier = modifier,
        navigationIcon = {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_medium)),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = stringResource(id = R.string.back),
                    modifier = Modifier.size(dimensionResource(id = R.dimen.top_bar_icon_size)),
                )
            }
        }
    )
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalSettingsScreenPreview() {
    FairyTalesTheme {
        SettingsScreen(
            uiState = SettingsState(textSize = 24.0F),
            onTextSizeUpdate = {},
            onBackClick = {}
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HorizontalSettingsScreenPreview() {
    FairyTalesTheme {
        SettingsScreen(
            uiState = SettingsState(textSize = 24.0F),
            onTextSizeUpdate = {},
            onBackClick = {}
        )
    }
}
