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
import com.viktoriagavrosh.shelf.utils.Tabs
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * App bar to display tabs on medium screen
 */
@Composable
internal fun LeftTabRail(
    tabsProvider: () -> List<Tabs>,
    selectedTabProvider: () -> Tabs,
    onTabClick: (Tabs) -> Unit,
    modifier: Modifier = Modifier
) {
    val verticalNavigationRailTestTag = stringResource(
        id = R.string.expanded_screen_test_tag
    )
    val selectedTab = selectedTabProvider()

    NavigationRail(
        modifier = modifier
            .testTag(verticalNavigationRailTestTag),
    ) {
        Spacer(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_double_extra_large)))
        for (item in tabsProvider()) {
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

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalNavigationRailPreview() {
    FairyTalesTheme {
        LeftTabRail(
            tabsProvider = { Tabs.FolkTab.entries },
            selectedTabProvider = { Tabs.FolkTab.Poem },
            onTabClick = {}
        )
    }
}
