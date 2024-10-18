package com.viktoriagavrosh.librarymenu.elements

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uikit.buttons.FlowerMenuButton
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display buttons with actions
 *
 * @param onTaleClick callback that is executed when tale button is clicked
 * @param onRiddleClick callback that is executed when riddle button is clicked
 * @param onFolkClick callback that is executed when folk button is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
internal fun ShelfButtons(
    onTaleClick: () -> Unit,
    onRiddleClick: () -> Unit,
    onFolkClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        FlowerMenuButton(
            text = stringResource(R.string.tale_button),
            onClick = onTaleClick,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_extra_large))
        )
        FlowerMenuButton(
            text = stringResource(R.string.riddle_button),
            onClick = onRiddleClick,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_extra_large))
        )
        FlowerMenuButton(
            text = stringResource(R.string.folk_button),
            onClick = onFolkClick,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_extra_large))
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ShelfButtonsPreview() {
    FairyTalesTheme {
        ShelfButtons(
            onRiddleClick = {},
            onTaleClick = {},
            onFolkClick = {},
        )
    }
}
