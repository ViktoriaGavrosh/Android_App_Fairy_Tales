package com.viktoriagavrosh.infomenu.elements

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
import com.viktoriagavrosh.infomenu.model.TaleInfo
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uikit.ScreenTopBar
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display InfoScreen content with topBar
 *
 * @param taleProvider provides instance [TaleInfo]
 * @param isVerticalScreen describes screen orientation
 * @param onReadClick callback that is executed when read button is clicked
 * @param onBackClick callback that is executed when back button is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ContentInfoScreen(
    taleProvider: () -> TaleInfo,
    isVerticalScreen: Boolean,
    onBackClick: () -> Unit,
    onReadClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)

    Scaffold(
        modifier = modifier,
        topBar = {
            ScreenTopBar(
                text = stringResource(R.string.tale_top_bar_title),
                scrollBehavior = scrollBehavior,
                onBackClick = onBackClick,
                modifier = Modifier.fillMaxWidth(),
            )
        },
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
    ) { paddingValues ->
        if (isVerticalScreen) {
            VerticalInfoContent(
                taleProvider = taleProvider,
                onReadClick = onReadClick,
                modifier = modifier
                    .fillMaxHeight()
                    .padding(paddingValues)
                    .nestedScroll(scrollBehavior.nestedScrollConnection),
            )
        } else {
            HorizontalInfoContent(
                taleProvider = taleProvider,
                onReadClick = onReadClick,
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
        ContentInfoScreen(
            taleProvider = {
                TaleInfo(
                    title = "title",
                    isFavorite = true,
                )
            },
            isVerticalScreen = true,
            onBackClick = {},
            onReadClick = {},
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HorizontalContentScreenPreview() {
    FairyTalesTheme {
        ContentInfoScreen(
            taleProvider = {
                TaleInfo(
                    title = "title",
                    isFavorite = true,
                )
            },
            isVerticalScreen = false,
            onBackClick = {},
            onReadClick = {},
        )
    }
}
