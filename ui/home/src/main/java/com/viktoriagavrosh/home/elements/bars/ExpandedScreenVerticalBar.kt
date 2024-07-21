package com.viktoriagavrosh.home.elements.bars

import android.content.res.Configuration
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
import androidx.compose.ui.unit.dp
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme
import com.viktoriagavrosh.home.R
import com.viktoriagavrosh.home.elements.TaleType

/**
 * App bar to display tabs on expanded screen
 */
@Composable
internal fun ExpandedScreenVerticalBar(
    selectedType: TaleType,
    onTabClick: (TaleType) -> Unit,
    modifier: Modifier = Modifier,
) {
    val expandedScreenVerticalBarTestTag = stringResource(
        id = R.string.expanded_screen_test_tag
    )
    PermanentDrawerSheet(
        modifier = modifier
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
    selectedType: TaleType,
    onTabClick: (TaleType) -> Unit,
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
        for (item in TaleType.entries) {
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

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ExpandedScreenVerticalBarPreview() {
    FairyTalesTheme {
        ExpandedScreenVerticalBar(
            selectedType = TaleType.Story,
            onTabClick = {},
            modifier = Modifier.width(220.dp)
        )
    }
}
