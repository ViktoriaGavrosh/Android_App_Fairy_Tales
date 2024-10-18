package com.viktoriagavrosh.startmenu.elements

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display StartScreen content
 *
 * @param isVerticalScreen describes screen orientation
 * @param onFavoriteClick callback that is executed when favorite tale button is clicked
 * @param onNightClick callback that is executed when night tale button is clicked
 * @param onLibraryClick callback that is executed when library button is clicked
 * @param onLastTaleClick callback that is executed when last tale button is clicked
 * @param onRandomClick callback that is executed when random button is clicked
 * @param onSettingsClick callback that is executed when settings button is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
internal fun ContentStartScreen(
    isVerticalScreen: Boolean,
    onFavoriteClick: () -> Unit,
    onNightClick: () -> Unit,
    onLibraryClick: () -> Unit,
    onLastTaleClick: () -> Unit,
    onRandomClick: () -> Unit,
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (isVerticalScreen) {
        VerticalStartContent(
            onFavoriteClick = onFavoriteClick,
            onNightClick = onNightClick,
            onLibraryClick = onLibraryClick,
            onLastTaleClick = onLastTaleClick,
            onRandomClick = onRandomClick,
            onSettingsClick = onSettingsClick,
            modifier = modifier.fillMaxHeight(),
        )
    } else {
        HorizontalStartContent(
            onFavoriteClick = onFavoriteClick,
            onNightClick = onNightClick,
            onLibraryClick = onLibraryClick,
            onLastTaleClick = onLastTaleClick,
            onRandomClick = onRandomClick,
            onSettingsClick = onSettingsClick,
            modifier = modifier,
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalContentScreenPreview() {
    FairyTalesTheme {
        ContentStartScreen(
            isVerticalScreen = true,
            onSettingsClick = {},
            onNightClick = {},
            onRandomClick = {},
            onLibraryClick = {},
            onFavoriteClick = {},
            onLastTaleClick = {},
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HorizontalContentScreenPreview() {
    FairyTalesTheme {
        ContentStartScreen(
            isVerticalScreen = false,
            onSettingsClick = {},
            onNightClick = {},
            onRandomClick = {},
            onLibraryClick = {},
            onFavoriteClick = {},
            onLastTaleClick = {},
        )
    }
}
