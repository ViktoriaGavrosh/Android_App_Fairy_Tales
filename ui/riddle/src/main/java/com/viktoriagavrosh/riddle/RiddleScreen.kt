package com.viktoriagavrosh.riddle

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.viktoriagavrosh.riddle.elements.ContentRiddleScreen
import com.viktoriagavrosh.riddle.model.ReadRiddle
import com.viktoriagavrosh.uikit.ErrorScreen
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display details of selected [ReadRiddle]
 */
@Composable
fun RiddleScreen(
    riddleId: Int,
    isVerticalScreen: Boolean,
    onBackClick: () -> Unit,
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: RiddleViewModel = hiltViewModel(
        creationCallback = { factory: RiddleViewModel.RiddleViewModelFactory ->
            factory.create(riddleId)
        }
    )
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    RiddleScreen(
        riddle = uiState.riddle,
        textSize = uiState.textSize,
        isError = uiState.isError,
        isVerticalScreen = isVerticalScreen,
        onBackClick = onBackClick,
        onSettingsClick = onSettingsClick,
        onErrorButtonClick = viewModel::initUiState,
        modifier = modifier
    )
}

@Composable
internal fun RiddleScreen(
    riddle: ReadRiddle,
    textSize: Float,
    isError: Boolean,
    isVerticalScreen: Boolean,
    onBackClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onErrorButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (isError) {
        ErrorScreen(
            onButtonClick = onErrorButtonClick,
            modifier = modifier,
        )
    } else {
        ContentRiddleScreen(
            riddle = riddle,
            textSize = textSize,
            isVerticalScreen = isVerticalScreen,
            onBackClick = onBackClick,
            onSettingsClick = onSettingsClick,
            modifier = modifier,
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalRiddleScreenPreview() {
    FairyTalesTheme {
        RiddleScreen(
            riddle = ReadRiddle(
                title = "title",
                text = "text",
                answer = "answer",
            ),
            textSize = 24.0F,
            isError = false,
            isVerticalScreen = true,
            onBackClick = {},
            onSettingsClick = {},
            onErrorButtonClick = {},
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ErrorVerticalRiddleScreenPreview() {
    FairyTalesTheme {
        RiddleScreen(
            riddle = ReadRiddle(),
            textSize = 24.0F,
            isError = true,
            isVerticalScreen = true,
            onBackClick = {},
            onSettingsClick = {},
            onErrorButtonClick = {},
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HorizontalRiddleScreenPreview() {
    FairyTalesTheme {
        RiddleScreen(
            riddle = ReadRiddle(
                title = "title",
                text = "text",
                answer = "answer",
            ),
            textSize = 24.0F,
            isError = false,
            isVerticalScreen = false,
            onBackClick = {},
            onSettingsClick = {},
            onErrorButtonClick = {},
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ErrorHorizontalRiddleScreenPreview() {
    FairyTalesTheme {
        RiddleScreen(
            riddle = ReadRiddle(),
            textSize = 24.0F,
            isError = true,
            isVerticalScreen = false,
            onBackClick = {},
            onSettingsClick = {},
            onErrorButtonClick = {},
        )
    }
}
