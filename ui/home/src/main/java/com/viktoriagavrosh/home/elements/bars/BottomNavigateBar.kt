package com.viktoriagavrosh.home.elements.bars

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
import com.viktoriagavrosh.home.R
import com.viktoriagavrosh.home.elements.Genre
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * App bar to display tabs on compact screen
 */
@Composable
internal fun BottomNavigateBar(
    selectedType: Genre,
    onTabClick: (Genre) -> Unit,
    modifier: Modifier = Modifier
) {
    val bottomNavigateBarTestTag = stringResource(id = R.string.compact_screen_test_tag)
    NavigationBar(
        modifier = modifier
            .testTag(bottomNavigateBarTestTag)
            .sizeIn(maxHeight = dimensionResource(id = R.dimen.max_bottom_navigation_bar_height))
    ) {
        for (item in Genre.entries) {
            NavigationBarItem(
                selected = item == selectedType,
                onClick = { onTabClick(item) },
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconId),
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
        BottomNavigateBar(
            selectedType = Genre.Puzzle,
            onTabClick = {}
        )
    }
}
