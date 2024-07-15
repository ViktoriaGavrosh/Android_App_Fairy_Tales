package com.viktoriagavrosh.fairytales.ui.elements.bars

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.home.R
import com.viktoriagavrosh.ui.uiscreens.FolkWorkType

/**
 * App bar to display tabs on compact screen
 */
@Composable
fun BottomNavigateBar(
    selectedType: FolkWorkType,
    onTabClick: (FolkWorkType) -> Unit,
    modifier: Modifier = Modifier
) {
    val bottomNavigateBarTestTag = stringResource(id = R.string.compact_screen_test_tag)
    NavigationBar(
        modifier = modifier.testTag(bottomNavigateBarTestTag)
    ) {
        for (item in FolkWorkType.entries) {
            NavigationBarItem(
                selected = item == selectedType,
                onClick = { onTabClick(item) },
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconId),
                        contentDescription = stringResource(id = item.textId),
                        modifier = Modifier.size(dimensionResource(id = R.dimen.bottom_bar_icon_size)),
                        tint = MaterialTheme.colorScheme.surfaceTint
                    )
                }
            )
        }
    }
}

@Preview
@Composable
fun BottomNavigateBarPreview() {
    BottomNavigateBar(
        selectedType = FolkWorkType.Story,
        onTabClick = {}
    )
}
