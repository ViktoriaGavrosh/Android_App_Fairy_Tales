package com.viktoriagavrosh.librarymenu.elements

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uitheme.FairyTalesTheme

@Composable
internal fun HorizontalLibraryContent(
    onTaleClick: () -> Unit,
    onRiddleClick: () -> Unit,
    onFolkClick: () -> Unit,
    onAddTaleClick: () -> Unit,
    onRandomClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .verticalScroll(rememberScrollState()),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier.weight(1F)
        ) {
            ShelfButtons(
                onTaleClick = onTaleClick,
                onFolkClick = onFolkClick,
                onRiddleClick = onRiddleClick,
                modifier = Modifier
                    .padding(horizontal = dimensionResource(R.dimen.padding_double_extra_large)),
            )
        }
        Box(
            modifier = Modifier.weight(0.6F),
            contentAlignment = Alignment.TopCenter
        ) {
            AdditionalButtons(
                onAddTaleClick = onAddTaleClick,
                onRandomClick = onRandomClick,
            )
        }
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HorizontalContentPreview() {
    FairyTalesTheme {
        HorizontalLibraryContent(
            onRiddleClick = {},
            onRandomClick = {},
            onFolkClick = {},
            onTaleClick = {},
            onAddTaleClick = {},
        )
    }
}

