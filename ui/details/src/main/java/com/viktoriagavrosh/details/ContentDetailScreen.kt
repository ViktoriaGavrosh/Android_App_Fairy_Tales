package com.viktoriagavrosh.details

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.details.model.TaleUiDetail
import com.viktoriagavrosh.uitheme.FairyTalesTheme

@Composable
internal fun ContentDetailScreen(
    tale: TaleUiDetail,
    isExpandedScreen: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .background(color = MaterialTheme.colorScheme.surfaceContainerHigh),
    ) {
        if (isExpandedScreen) {
            HorizontalDetailScreen(
                tale = tale,
                modifier = Modifier
                    .testTag(stringResource(R.string.horizontal_detail_screen))
            )
        } else {
            Spacer(modifier = Modifier.weight(1F))
            VerticalDetailScreen(
                tale = tale,
                modifier = Modifier

                    .testTag(stringResource(R.string.vertical_detail_screen))
            )
            Spacer(modifier = Modifier.weight(1F))
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
            isExpandedScreen = false
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
            isExpandedScreen = true
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
            isExpandedScreen = true
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
            isExpandedScreen = true
        )
    }
}
