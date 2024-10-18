package com.viktoriagavrosh.librarymenu.elements

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxHeight
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
 * Composable to display LibraryScreen content with topBar
 *
 * @param isVerticalScreen describes screen orientation
 * @param onTaleClick callback that is executed when tale button is clicked
 * @param onRiddleClick callback that is executed when riddle button is clicked
 * @param onFolkClick callback that is executed when folk button is clicked
 * @param onAddTaleClick callback that is executed when add tale button is clicked
 * @param onRandomClick callback that is executed when random button is clicked
 * @param onBackClick callback that is executed when back button is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ContentLibraryScreen(
    isVerticalScreen: Boolean,
    onTaleClick: () -> Unit,
    onRiddleClick: () -> Unit,
    onFolkClick: () -> Unit,
    onAddTaleClick: () -> Unit,
    onRandomClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)

    Scaffold(
        modifier = modifier,
        topBar = {
            ScreenTopBar(
                text = stringResource(R.string.library),
                scrollBehavior = scrollBehavior,
                onBackClick = onBackClick,
                modifier = Modifier.fillMaxWidth(),
            )
        },
    ) { paddingValues ->
        if (isVerticalScreen) {
            VerticalLibraryContent(
                onRandomClick = onRandomClick,
                onRiddleClick = onRiddleClick,
                onFolkClick = onFolkClick,
                onTaleClick = onTaleClick,
                onAddTaleClick = onAddTaleClick,
                modifier = modifier
                    .fillMaxHeight()
                    .padding(paddingValues)
                    .nestedScroll(scrollBehavior.nestedScrollConnection),
            )
        } else {
            HorizontalLibraryContent(
                onRandomClick = onRandomClick,
                onRiddleClick = onRiddleClick,
                onFolkClick = onFolkClick,
                onTaleClick = onTaleClick,
                onAddTaleClick = onAddTaleClick,
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
private fun VerticalContentScreenPreview() {
    FairyTalesTheme {
        ContentLibraryScreen(
            isVerticalScreen = true,
            onRiddleClick = {},
            onBackClick = {},
            onFolkClick = {},
            onTaleClick = {},
            onRandomClick = {},
            onAddTaleClick = {},
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HorizontalContentScreenPreview() {
    FairyTalesTheme {
        ContentLibraryScreen(
            isVerticalScreen = false,
            onRiddleClick = {},
            onBackClick = {},
            onFolkClick = {},
            onTaleClick = {},
            onRandomClick = {},
            onAddTaleClick = {},
        )
    }
}
