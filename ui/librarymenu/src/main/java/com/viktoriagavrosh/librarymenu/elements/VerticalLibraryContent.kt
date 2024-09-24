package com.viktoriagavrosh.librarymenu.elements

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.librarymenu.R
import com.viktoriagavrosh.uitheme.FairyTalesTheme

@Composable
internal fun VerticalLibraryContent(
    onTaleClick: () -> Unit,
    onRiddleClick: () -> Unit,
    onFolkClick: () -> Unit,
    onAddTaleClick: () -> Unit,
    onRandomClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ShelfButtons(
            onTaleClick = onTaleClick,
            onRiddleClick = onRiddleClick,
            onFolkClick = onFolkClick,
            modifier = Modifier
                .padding(top = dimensionResource(R.dimen.padding_double_extra_large))
                .padding(horizontal = dimensionResource(R.dimen.padding_double_extra_large)),
        )
        AdditionalButtons(
            onRandomClick = onRandomClick,
            onAddTaleClick = onAddTaleClick,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_double_extra_large)),
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalContentPreview() {
    FairyTalesTheme {
        VerticalLibraryContent(
            onRiddleClick = {},
            onRandomClick = {},
            onFolkClick = {},
            onTaleClick = {},
            onAddTaleClick = {},
        )
    }
}
