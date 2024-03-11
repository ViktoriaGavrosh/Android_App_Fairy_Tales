package com.viktoriagavrosh.fairytales.ui.elements.bars

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.data.FolkWorkType

/**
 * App bar to display tabs on medium screen
 */
@Composable
fun VerticalNavigationRail(
    selectedType: FolkWorkType,
    onTabClick: (FolkWorkType) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationRail(
        modifier = modifier
            .padding(top = dimensionResource(id = R.dimen.padding_small))
    ) {
        for (item in FolkWorkType.entries) {
            NavigationRailItem(
                selected = item == selectedType,
                onClick = { onTabClick(item) },
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconId),
                        contentDescription = stringResource(id = item.textId),
                        tint = MaterialTheme.colorScheme.surfaceTint
                    )
                }
            )
        }
    }
}

@Preview
@Composable
fun VerticalNavigationRailPreview() {
    VerticalNavigationRail(
        selectedType = FolkWorkType.Story,
        onTabClick = {}
    )
}
