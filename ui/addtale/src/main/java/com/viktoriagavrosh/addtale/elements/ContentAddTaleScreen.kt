package com.viktoriagavrosh.addtale.elements

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.addtale.model.NewTale
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uikit.ScreenTopBar
import com.viktoriagavrosh.uitheme.FairyTalesTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ContentAddTaleScreen(
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
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)

    Scaffold(
        modifier = modifier,
        topBar = {
            ScreenTopBar(
                text = stringResource(R.string.add_tale),
                scrollBehavior = scrollBehavior,
                onBackClick = onBackClick,
                modifier = Modifier.fillMaxWidth(),
            )
        },
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
    ) { paddingValues ->
        if (isVerticalScreen) {
            VerticalAddTaleContent(
                taleProvider = taleProvider,
                isTaleValidProvider = isTaleValidProvider,
                onTitleValueChange = onTitleValueChange,
                onTextValueChange = onTextValueChange,
                onGenreValueChange = onGenreValueChange,
                onIsNightValueChange = onIsNightValueChange,
                onAddButtonClick = onAddButtonClick,
                modifier = modifier
                    .fillMaxHeight()
                    .padding(paddingValues)
                    .nestedScroll(scrollBehavior.nestedScrollConnection),
            )
        } else {
            HorizontalAddTaleContent(
                taleProvider = taleProvider,
                isTaleValidProvider = isTaleValidProvider,
                onTitleValueChange = onTitleValueChange,
                onTextValueChange = onTextValueChange,
                onGenreValueChange = onGenreValueChange,
                onIsNightValueChange = onIsNightValueChange,
                onAddButtonClick = onAddButtonClick,
                modifier = modifier
                    .fillMaxHeight()
                    .padding(paddingValues)
                    .nestedScroll(scrollBehavior.nestedScrollConnection),
            )
        }
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalContentScreenPreview() {
    FairyTalesTheme {
        ContentAddTaleScreen(
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
private fun HorizontalContentScreenPreview() {
    FairyTalesTheme {
        ContentAddTaleScreen(
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
