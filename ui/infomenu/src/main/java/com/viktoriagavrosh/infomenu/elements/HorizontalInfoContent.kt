package com.viktoriagavrosh.infomenu.elements

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.infomenu.model.TaleInfo
import com.viktoriagavrosh.uikit.Cover
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uikit.image.BookImage
import com.viktoriagavrosh.uitheme.FairyTalesTheme

@Composable
internal fun HorizontalInfoContent(
    taleProvider: () -> TaleInfo,
    onReadClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .testTag(stringResource(R.string.horizontal_info_content_test_tag)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TaleInfoRow(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_extra_large)),
            taleProvider = taleProvider,
        )
        TaleActionsColumn(
            firstButtonText = stringResource(R.string.read_button),
            onFirstButtonClick = onReadClick,
            modifier = Modifier
                .width(dimensionResource(R.dimen.info_button_width))
                .padding(dimensionResource(R.dimen.padding_medium))
        )
    }
}

@Composable
private fun TaleInfoRow(
    modifier: Modifier,
    taleProvider: () -> TaleInfo,
) {
    val tale = taleProvider()
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier.weight(1F)
        ) {
            BookImage(
                title = tale.title,
                imageUrl = tale.imageUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = dimensionResource(R.dimen.padding_double_extra_large)
                    ),
            )
        }
        Box(
            modifier = Modifier.weight(0.8F),
            contentAlignment = Alignment.Center,
        ) {
            Cover(
                text = tale.title,
                modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium))
            )
        }
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HorizontalContentPreview() {
    FairyTalesTheme {
        HorizontalInfoContent(
            taleProvider = {
                TaleInfo(
                    title = "title",
                    isFavorite = true,
                )
            },
            onReadClick = {},
            modifier = Modifier.fillMaxHeight()
        )
    }
}

