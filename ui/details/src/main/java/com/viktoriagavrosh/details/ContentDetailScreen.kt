package com.viktoriagavrosh.details

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.details.model.TaleUiDetail
import com.viktoriagavrosh.uitheme.FairyTalesTheme

@Composable
internal fun ContentDetailScreen(
    tale: TaleUiDetail,
    isExpandedScreen: Boolean,
    textSize: Float,
    onTextSizeUpdate: (Float) -> Unit,
    modifier: Modifier = Modifier,
) {
    // States are only here, otherwise scroll disappears
    val scrollState = rememberScrollState()
    var fontSize by remember {
        mutableFloatStateOf(0.0F)
    }
    if (fontSize == 0.0F) fontSize = textSize
    Column(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.surfaceContainerHigh)
            .pointerInput(Unit) {
                detectTransformGestures { _, _, zoom, _ ->
                    fontSize *= zoom
                    if (fontSize < 8) fontSize = 8.0F
                    if (fontSize > 100) fontSize = 100.0F
                    onTextSizeUpdate(fontSize)
                }
            }
            .verticalScroll(scrollState)
    ) {

        if (isExpandedScreen) {

            HorizontalDetailScreen(
                tale = tale,
                fontSize = fontSize,
                modifier = Modifier
                    .testTag(stringResource(R.string.horizontal_detail_screen))
            )

        } else {
            VerticalDetailScreen(
                tale = tale,
                fontSize = fontSize,
                modifier = Modifier

                    .testTag(stringResource(R.string.vertical_detail_screen))
            )
        }
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalContentScreenPreview() {
    FairyTalesTheme {
        ContentDetailScreen(
            tale = TaleUiDetail(
                text = "Text"
            ),
            isExpandedScreen = false,
            textSize = 24.0F,
            onTextSizeUpdate = {},
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PuzzleVerticalContentScreenPreview() {
    FairyTalesTheme {
        ContentDetailScreen(
            tale = TaleUiDetail(
                text = "Text",
                genre = "puzzle",
                answer = "Answer",
            ),
            isExpandedScreen = false,
            textSize = 24.0F,
            onTextSizeUpdate = {},
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun StoryHorizontalContentScreenPreview() {
    FairyTalesTheme {
        ContentDetailScreen(
            tale = TaleUiDetail(
                text = "Text",
                genre = "story",
            ),
            isExpandedScreen = true,
            textSize = 24.0F,
            onTextSizeUpdate = {},
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PuzzleHorizontalContentScreenPreview() {
    FairyTalesTheme {
        ContentDetailScreen(
            tale = TaleUiDetail(
                text = "Text",
                genre = "puzzle",
                answer = "Answer",
            ),
            isExpandedScreen = true, textSize = 24.0F,
            onTextSizeUpdate = {},
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun GameHorizontalContentScreenPreview() {
    FairyTalesTheme {
        ContentDetailScreen(
            tale = TaleUiDetail(
                text = "Text",
                genre = "game",
            ),
            isExpandedScreen = true, textSize = 24.0F,
            onTextSizeUpdate = {},
        )
    }
}
