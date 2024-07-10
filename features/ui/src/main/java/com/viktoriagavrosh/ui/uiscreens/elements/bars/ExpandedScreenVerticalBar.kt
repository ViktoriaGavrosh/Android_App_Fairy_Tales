package com.viktoriagavrosh.fairytales.ui.elements.bars

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.ui.R
import com.viktoriagavrosh.ui.uiscreens.FolkWorkType

/**
 * App bar to display tabs on expanded screen
 */
@Composable
fun ExpandedScreenVerticalBar(
    selectedType: FolkWorkType,
    onTabClick: (FolkWorkType) -> Unit
) {
    val expandedScreenVerticalBarTestTag = stringResource(
        id = R.string.expanded_screen_test_tag
    )
    PermanentDrawerSheet(
        modifier = Modifier.width(
            dimensionResource(id = R.dimen.permanent_drawer_sheet_width)
        )
    ) {
        NavigationDrawerContent(
            selectedType = selectedType,
            onTabClick = onTabClick,
            modifier = Modifier
                .wrapContentWidth()
                .fillMaxHeight()
                .background(
                    color = MaterialTheme.colorScheme.inverseOnSurface
                )
                .padding(dimensionResource(id = R.dimen.padding_small))
                .testTag(expandedScreenVerticalBarTestTag)
        )
    }
}

@Composable
private fun NavigationDrawerContent(
    selectedType: FolkWorkType,
    onTabClick: (FolkWorkType) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        Text(
            text = stringResource(id = R.string.title),
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.padding(
                top = dimensionResource(id = R.dimen.padding_medium),
                start = dimensionResource(id = R.dimen.padding_medium)
            )
        )
        Spacer(modifier = Modifier.weight(1F))
        for (item in FolkWorkType.entries) {
            NavigationDrawerItem(
                label = { Text(text = stringResource(id = item.textId)) },
                selected = item == selectedType,
                onClick = { onTabClick(item) },
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconId),
                        contentDescription = stringResource(id = item.textId),
                        tint = MaterialTheme.colorScheme.surfaceTint
                    )
                },
                modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_small))
            )
        }
        Spacer(modifier = Modifier.weight(3F))
    }
}

@Preview
@Composable
fun ExpandedScreenVerticalBarPreview() {
    ExpandedScreenVerticalBar(
        selectedType = FolkWorkType.Story,
        onTabClick = {}
    )
}
