package com.viktoriagavrosh.riddle.elements

import android.content.res.Configuration
import androidx.compose.foundation.background
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
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.riddle.model.ReadRiddle
import com.viktoriagavrosh.uikit.ScreenTopBar
import com.viktoriagavrosh.uitheme.FairyTalesTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ContentRiddleScreen(
    riddle: ReadRiddle,
    textSize: Float,
    isVerticalScreen: Boolean,
    onBackClick: () -> Unit,
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)

    Scaffold(
        modifier = modifier.background(color = MaterialTheme.colorScheme.surfaceContainerHigh),
        topBar = {
            ScreenTopBar(
                text = riddle.title,
                scrollBehavior = scrollBehavior,
                isSettingsIconShow = true,
                isBackIconShow = true,
                onSettingsClick = onSettingsClick,
                onBackClick = onBackClick,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    ) { paddingValues ->
        if (isVerticalScreen) {
            VerticalRiddleContent(
                riddle = riddle,
                textSize = textSize,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(paddingValues)
                    .nestedScroll(scrollBehavior.nestedScrollConnection),
            )
        } else {
            HorizontalRiddleContent(
                riddle = riddle,
                textSize = textSize,
                modifier = Modifier
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
        ContentRiddleScreen(
            riddle = ReadRiddle(
                text = "Text",
                title = "Title",
                answer = "answer",
            ),
            textSize = 24.0F,
            isVerticalScreen = true,
            onBackClick = {},
            onSettingsClick = {}
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HorizontalContentScreenPreview() {
    FairyTalesTheme {
        ContentRiddleScreen(
            riddle = ReadRiddle(
                text = "Text",
                title = "Title",
                answer = "answer",
            ),
            textSize = 24.0F,
            isVerticalScreen = false,
            onBackClick = {},
            onSettingsClick = {}
        )
    }
}
