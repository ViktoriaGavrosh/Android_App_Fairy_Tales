package com.viktoriagavrosh.infomenu.elements

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.infomenu.model.TaleInfo
import com.viktoriagavrosh.uikit.Cover
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uikit.image.BookImage
import com.viktoriagavrosh.uitheme.FairyTalesTheme

@Composable
internal fun VerticalInfoContent(
    taleProvider: () -> TaleInfo,
    onReadClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val tale = taleProvider()
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BookImage(
            title = tale.title,
            imageUrl = tale.imageUrl,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = dimensionResource(id = R.dimen.padding_extra_large),
                    top = dimensionResource(id = R.dimen.padding_large),
                    end = dimensionResource(id = R.dimen.padding_extra_large),
                    bottom = dimensionResource(id = R.dimen.padding_small)
                ),
        )
        Cover(
            text = tale.title,
            modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium))
        )
        TaleActionsColumn(
            firstButtonText = stringResource(R.string.read_button),
            onFirstButtonClick = onReadClick,
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_double_extra_large))
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalContentPreview() {
    FairyTalesTheme {
        VerticalInfoContent(
            taleProvider = {
                TaleInfo(
                    title = "title",
                    isFavorite = true,
                )
            },
            onReadClick = {},
        )
    }
}
