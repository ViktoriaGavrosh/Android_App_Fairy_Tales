package com.viktoriagavrosh.shelf.elements

import android.content.res.Configuration
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
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
 * Composable to display bar for tabs
 *
 * @param selectedTabProvider provides one of [Tabs], that is shown
 * @param onTabClick callback that is executed when one of tabs is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
internal fun BottomTabBar(
    selectedTabProvider: () -> Tabs,
    onTabClick: (Tabs) -> Unit,
    modifier: Modifier = Modifier
) {
    val bottomNavigateBarTestTag = stringResource(id = R.string.compact_screen_test_tag)
    val selectedTab = selectedTabProvider()
    val tabs = getTabs(selectedTab.genre)

    NavigationBar(
        modifier = modifier
            .testTag(bottomNavigateBarTestTag)
            .sizeIn(maxHeight = dimensionResource(id = R.dimen.max_bottom_navigation_bar_height)),
    ) {
        for (item in tabs) {
            NavigationBarItem(
                selected = item == selectedTab,
                onClick = { onTabClick(item) },
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconId ?: R.drawable.ic_favorite_false),
                        contentDescription = stringResource(id = item.textId),
                        modifier = Modifier.scale(1.5F),
                        tint = MaterialTheme.colorScheme.surfaceTint
                    )
                }
            )
        }
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BottomNavigateBarPreview() {
    FairyTalesTheme {
        BottomTabBar(
            selectedTabProvider = { Tabs.FolkTab.Poem },
            onTabClick = {}
        )
    }
}
