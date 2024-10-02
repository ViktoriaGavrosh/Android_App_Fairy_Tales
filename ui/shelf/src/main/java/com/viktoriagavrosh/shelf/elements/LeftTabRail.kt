package com.viktoriagavrosh.shelf.elements

import android.content.res.Configuration
import androidx.compose.foundation.layout.Spacer
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
import com.viktoriagavrosh.repositories.utils.ShelfGenre
import com.viktoriagavrosh.shelf.utils.Tabs
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * App bar to display tabs on medium screen
 */
@Composable
internal fun LeftTabRail(
    selectedTabProvider: () -> Tabs,
    onTabClick: (Tabs) -> Unit,
    modifier: Modifier = Modifier
) {
    val verticalNavigationRailTestTag = stringResource(
        id = R.string.expanded_screen_test_tag
    )
    val selectedTab = selectedTabProvider()
    val tabs = getTabs(selectedTab.genre)

    NavigationRail(
        modifier = modifier
            .testTag(verticalNavigationRailTestTag),
    ) {
        Spacer(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_double_extra_large)))
        for (item in tabs) {
            NavigationRailItem(
                selected = item == selectedTab,
                onClick = { onTabClick(item) },
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconId ?: R.drawable.ic_favorite_false),
                        contentDescription = stringResource(id = item.textId),
                        tint = MaterialTheme.colorScheme.surfaceTint,
                        modifier = Modifier.scale(1.5F)
                    )
                }
            )
        }
    }
}

internal fun getTabs(genre: ShelfGenre): List<Tabs> {
    return when (genre) {
        in ShelfGenre.Folks.entries -> Tabs.FolkTab.entries
        in ShelfGenre.Tales.entries -> Tabs.TaleTab.entries
        else -> emptyList()
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalNavigationRailPreview() {
    FairyTalesTheme {
        LeftTabRail(
            selectedTabProvider = { Tabs.FolkTab.Poem },
            onTabClick = {}
        )
    }
}
