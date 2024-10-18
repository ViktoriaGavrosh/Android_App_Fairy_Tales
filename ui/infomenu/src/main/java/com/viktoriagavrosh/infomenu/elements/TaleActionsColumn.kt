package com.viktoriagavrosh.infomenu.elements

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uikit.buttons.FlowerMenuButton
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display buttons with actions
 *
 * @param firstButtonText text on top button
 * @param onFirstButtonClick callback that is executed when top button is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
internal fun TaleActionsColumn(
    firstButtonText: String,
    onFirstButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(
            top = dimensionResource(R.dimen.padding_double_extra_large)
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        FlowerMenuButton(
            text = firstButtonText,
            onClick = onFirstButtonClick,
            modifier = Modifier
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TaleActionsColumnPreview() {
    FairyTalesTheme {
        TaleActionsColumn(
            firstButtonText = "Text",
            onFirstButtonClick = {},
        )
    }
}
