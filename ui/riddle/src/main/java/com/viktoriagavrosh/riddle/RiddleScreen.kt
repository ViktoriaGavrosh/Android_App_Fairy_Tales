package com.viktoriagavrosh.riddle

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.viktoriagavrosh.riddle.elements.ContentRiddleScreen
import com.viktoriagavrosh.riddle.model.ReadRiddle
import com.viktoriagavrosh.uikit.ErrorScreen
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable for displaying reader for riddles
 *
 * @param riddleId id of selected riddle
 * @param isVerticalScreen describes screen orientation
 * @param onBackClick callback that is executed when back button is clicked
 * @param onSettingsClick callback that is executed when settings button is clicked
 * @param modifier the modifier to be applied to this layout node
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
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val textSize = viewModel.textSize.collectAsStateWithLifecycle()

    RiddleScreen(
        riddleProvider = { uiState.value.riddle },
        textSizeProvider = { textSize.value },
        isErrorProvider = { uiState.value.isError },
        isVerticalScreen = isVerticalScreen,
        onBackClick = onBackClick,
        onSettingsClick = onSettingsClick,
        onErrorButtonClick = viewModel::initUiState,
        modifier = modifier
    )
}

/**
 * Composable for displaying reader for riddles
 *
 * @param riddleProvider provides id of selected riddle
 * @param textSizeProvider provides text size value
 * @param isErrorProvider provides boolean. If true ErrorScreen will be shown
 * @param isVerticalScreen describes screen orientation
 * @param onBackClick callback that is executed when back button is clicked
 * @param onSettingsClick callback that is executed when settings button is clicked
 * @param onErrorButtonClick callback that is executed when button on ErrorScreen is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
internal fun RiddleScreen(
    riddleProvider: () -> ReadRiddle,
    textSizeProvider: () -> Float,
    isErrorProvider: () -> Boolean,
    isVerticalScreen: Boolean,
    onBackClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onErrorButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (isErrorProvider()) {
        ErrorScreen(
            onButtonClick = onErrorButtonClick,
            modifier = modifier,
        )
    } else {
        ContentRiddleScreen(
            riddleProvider = riddleProvider,
            textSizeProvider = textSizeProvider,
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
            riddleProvider = {
                ReadRiddle(
                    title = "title",
                    text = "text",
                    answer = "answer",
                )
            },
            textSizeProvider = { 24.0F },
            isErrorProvider = { false },
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
            riddleProvider = { ReadRiddle() },
            textSizeProvider = { 24.0F },
            isErrorProvider = { true },
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
            riddleProvider = {
                ReadRiddle(
                    title = "title",
                    text = "text",
                    answer = "answer",
                )
            },
            textSizeProvider = { 24.0F },
            isErrorProvider = { false },
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
            riddleProvider = { ReadRiddle() },
            textSizeProvider = { 24.0F },
            isErrorProvider = { true },
            isVerticalScreen = false,
            onBackClick = {},
            onSettingsClick = {},
            onErrorButtonClick = {},
        )
    }
}
