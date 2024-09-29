package com.viktoriagavrosh.infomenu

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.viktoriagavrosh.infomenu.elements.ContentInfoScreen
import com.viktoriagavrosh.infomenu.model.TaleInfo
import com.viktoriagavrosh.uikit.ErrorScreen
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display details of selected [TaleInfo]
 */
@Composable
fun InfoScreen(
    taleId: Int,
    isVerticalScreen: Boolean,
    onReadClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: InfoViewModel = hiltViewModel(
        creationCallback = { factory: InfoViewModel.InfoViewModelFactory ->
            factory.create(taleId)
        }
    )
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    InfoScreen(
        taleProvider = { uiState.value.tale },
        isErrorProvider = { uiState.value.isError },
        isVerticalScreen = isVerticalScreen,
        onBackClick = onBackClick,
        onReadClick = onReadClick,
        onErrorButtonClick = viewModel::updateUiState,
        modifier = modifier
    )
}

@Composable
internal fun InfoScreen(
    taleProvider: () -> TaleInfo,
    isErrorProvider: () -> Boolean,
    isVerticalScreen: Boolean,
    onBackClick: () -> Unit,
    onReadClick: () -> Unit,
    onErrorButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (isErrorProvider()) {
        ErrorScreen(
            onButtonClick = onErrorButtonClick,
            modifier = modifier,
        )
    } else {
        ContentInfoScreen(
            taleProvider = taleProvider,
            isVerticalScreen = isVerticalScreen,
            onBackClick = onBackClick,
            onReadClick = onReadClick,
            modifier = modifier,
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalInfoScreenPreview() {
    FairyTalesTheme {
        InfoScreen(
            taleProvider = {
                TaleInfo(
                    title = "title",
                    isFavorite = false
                )
            },
            isErrorProvider = { false },
            isVerticalScreen = true,
            onBackClick = {},
            onReadClick = {},
            onErrorButtonClick = {},
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ErrorVerticalInfoScreenPreview() {
    FairyTalesTheme {
        InfoScreen(
            taleProvider = {
                TaleInfo(
                    title = "title",
                    isFavorite = true,
                )
            },
            isErrorProvider = { true },
            isVerticalScreen = true,
            onBackClick = {},
            onReadClick = {},
            onErrorButtonClick = {},
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HorizontalInfoScreenPreview() {
    FairyTalesTheme {
        InfoScreen(
            taleProvider = {
                TaleInfo(
                    title = "title",
                    isFavorite = true,
                )
            },
            isErrorProvider = { false },
            isVerticalScreen = false,
            onBackClick = {},
            onReadClick = {},
            onErrorButtonClick = {},
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ErrorHorizontalInfoScreenPreview() {
    FairyTalesTheme {
        InfoScreen(
            taleProvider = {
                TaleInfo(
                    title = "title",
                    isFavorite = false
                )
            },
            isErrorProvider = { true },
            isVerticalScreen = false,
            onBackClick = {},
            onReadClick = {},
            onErrorButtonClick = {},
        )
    }
}
