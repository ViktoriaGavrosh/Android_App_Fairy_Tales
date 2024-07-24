package com.viktoriagavrosh.home

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.home.elements.GridTales
import com.viktoriagavrosh.home.elements.ListTales
import com.viktoriagavrosh.home.elements.bars.ContentTopBar
import com.viktoriagavrosh.home.model.TaleUiHome
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display [TaleUiHome] list or grid screen
 */
@Composable
internal fun ContentScreen(
    tales: List<TaleUiHome>,
    @StringRes topBarTextId: Int,
    isFavoriteTalesList: Boolean,
    isExpandedScreen: Boolean,
    onHeartClick: (TaleUiHome) -> Unit,
    onTopBarHeartClick: () -> Unit,
    onCardClick: (TaleUiHome) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = if (isExpandedScreen) {
            modifier.padding(end = dimensionResource(id = R.dimen.right_padding_horizontal_screen))
        } else {
            modifier
        }
    ) {
        ContentTopBar(
            text = stringResource(id = topBarTextId),
            isFavoriteTalesList = isFavoriteTalesList,
            onTopBarHeartClick = onTopBarHeartClick,
            modifier = Modifier.fillMaxWidth()
        )
        if (isExpandedScreen) {
            GridTales(
                tales = tales,
                onCardClick = onCardClick,
                onHeartClick = onHeartClick,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            ListTales(
                tales = tales,
                onCardClick = onCardClick,
                onHeartClick = onHeartClick,
                modifier = Modifier
            )
        }
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalContentScreenPreview() {
    FairyTalesTheme {
        ContentScreen(
            tales = List(4) {
                TaleUiHome(
                    id = it,
                    title = "title",
                )
            },
            topBarTextId = R.string.title_fairy_tales,
            isFavoriteTalesList = false,
            isExpandedScreen = false,
            onHeartClick = {},
            onTopBarHeartClick = {},
            onCardClick = {}
        )
    }
}

@Preview(name = "Light", widthDp = 700)
@Preview(name = "Dark", widthDp = 700, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalFavoriteContentScreenPreview() {
    FairyTalesTheme {
        ContentScreen(
            tales = List(2) {
                TaleUiHome(
                    id = it,
                    title = "title",
                    isFavorite = true
                )
            },
            topBarTextId = R.string.title_fairy_tales,
            isFavoriteTalesList = true,
            isExpandedScreen = false,
            onHeartClick = {},
            onTopBarHeartClick = {},
            onCardClick = {}
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HorizontalContentScreenPreview() {
    FairyTalesTheme {
        ContentScreen(
            tales = List(4) {
                TaleUiHome(
                    id = it,
                    title = "title",
                )
            },
            topBarTextId = R.string.title_fairy_tales,
            isFavoriteTalesList = false,
            isExpandedScreen = true,
            onHeartClick = {},
            onTopBarHeartClick = {},
            onCardClick = {}
        )
    }
}
