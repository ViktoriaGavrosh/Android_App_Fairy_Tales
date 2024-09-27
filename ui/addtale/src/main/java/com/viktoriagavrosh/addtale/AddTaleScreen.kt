package com.viktoriagavrosh.addtale

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.viktoriagavrosh.addtale.elements.ContentAddTaleScreen
import com.viktoriagavrosh.addtale.model.NewTale
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display details of selected [NewTale]
 */
@Composable
fun AddTaleScreen(
    isVerticalScreen: Boolean,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: AddTaleViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    AddTaleScreen(
        taleProvider = { uiState.value.newTale },
        isTaleValidProvider = { uiState.value.isTaleValid },
        isVerticalScreen = isVerticalScreen,
        onTitleValueChange = viewModel::updateTitle,
        onTextValueChange = viewModel::updateText,
        onGenreValueChange = viewModel::updateGenre,
        onIsNightValueChange = viewModel::updateIsNight,
        onBackClick = onBackClick,
        onAddButtonClick = {
            viewModel.addTale()
            onBackClick()
        },
        modifier = modifier
    )
}

@Composable
internal fun AddTaleScreen(
    taleProvider: () -> NewTale,
    isTaleValidProvider: () -> Boolean,
    isVerticalScreen: Boolean,
    onTitleValueChange: (String) -> Unit,
    onTextValueChange: (String) -> Unit,
    onGenreValueChange: (String) -> Unit,
    onIsNightValueChange: (Boolean) -> Unit,
    onBackClick: () -> Unit,
    onAddButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ContentAddTaleScreen(
        taleProvider = taleProvider,
        isTaleValidProvider = isTaleValidProvider,
        isVerticalScreen = isVerticalScreen,
        onTitleValueChange = onTitleValueChange,
        onTextValueChange = onTextValueChange,
        onGenreValueChange = onGenreValueChange,
        onIsNightValueChange = onIsNightValueChange,
        onBackClick = onBackClick,
        onAddButtonClick = onAddButtonClick,
        modifier = modifier,
    )
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalAddTaleScreenPreview() {
    FairyTalesTheme {
        AddTaleScreen(
            taleProvider = { NewTale() },
            isTaleValidProvider = { true },
            isVerticalScreen = true,
            onTitleValueChange = {},
            onTextValueChange = {},
            onGenreValueChange = {},
            onIsNightValueChange = {},
            onBackClick = {},
            onAddButtonClick = {},
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HorizontalAddTaleScreenPreview() {
    FairyTalesTheme {
        AddTaleScreen(
            taleProvider = { NewTale() },
            isTaleValidProvider = { true },
            isVerticalScreen = false,
            onTitleValueChange = {},
            onTextValueChange = {},
            onGenreValueChange = {},
            onIsNightValueChange = {},
            onBackClick = {},
            onAddButtonClick = {},
        )
    }
}
