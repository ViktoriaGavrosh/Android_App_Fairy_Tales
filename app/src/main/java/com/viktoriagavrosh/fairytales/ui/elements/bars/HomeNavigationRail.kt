package com.viktoriagavrosh.fairytales.ui.elements.bars

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.ui.elements.Genre
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme

/**
 * Composable to display tabs on medium and expanded screens
 */
@Composable
fun HomeNavigationRail(
    selectedGenre: Genre,
    onTabClick: (Genre) -> Unit,
    modifier: Modifier = Modifier,
) {
    // TODO maybe private ?
    val homeNavigationRailTestTag = stringResource(id = R.string.expanded_screen_test_tag)

    NavigationRail(
        modifier = modifier
            .padding(top = dimensionResource(id = R.dimen.padding_double_extra_large))
            .testTag(homeNavigationRailTestTag),
    ) {
        for (item in Genre.entries) {
            NavigationRailItem(
                selected = item == selectedGenre,
                onClick = { onTabClick(item) },
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconId),
                        contentDescription = stringResource(id = item.textId),
                        modifier = Modifier.scale(1.5F),
                        tint = MaterialTheme.colorScheme.surfaceTint,
                    )
                }
            )
        }
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HomeNavigationRailPreview() {
    FairyTalesTheme {
        HomeNavigationRail(
            selectedGenre = Genre.Puzzle,
            onTabClick = {},
        )
    }
}
