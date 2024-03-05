package com.viktoriagavrosh.fairytales.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.data.FolkWorkType

@Composable
fun OnlyScreenTopBar(
    text: String,
    isShowHomeScreen: Boolean,
    currentFolkWorkType: FolkWorkType,
    onDetailScreenBackClick: () -> Unit,
    isFavoriteWorks: Boolean,
    onTopBarHeartClicked: (FolkWorkType) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (!isShowHomeScreen) {
            IconButton(
                onClick = { onDetailScreenBackClick() },
                modifier = Modifier.padding(
                    start = dimensionResource(id = R.dimen.padding_medium)
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null
                )
            }
            Text(
                text = text,
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier
                    .padding(top = dimensionResource(id = R.dimen.padding_medium))
            )
        } else {
            Text(
                text = text,
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier
                    .padding(
                        start = dimensionResource(id = R.dimen.padding_large),
                        top = dimensionResource(id = R.dimen.padding_medium)
                    )

            )
            Icon(
                painter = if (isFavoriteWorks) {
                    painterResource(id = R.drawable.ic_favorite_true)
                } else {
                    painterResource(id = R.drawable.ic_favorite_false)
                },
                contentDescription = stringResource(R.string.favorite_folk_works),
                modifier = Modifier.clickable {
                    onTopBarHeartClicked(currentFolkWorkType)
                }
            )
        }
    }
}

@Composable
fun BottomNavigateBar(
    currentFolkWorkType: FolkWorkType,
    onTabClick: (FolkWorkType) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier
    ) {
        for (item in FolkWorkType.entries) {
            NavigationBarItem(
                selected = item == currentFolkWorkType,
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

@Composable
fun FairyTalesNavigationRail(
    currentFolkWorkType: FolkWorkType,
    onTabClick: (FolkWorkType) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationRail(
        modifier = modifier
            .padding(top = dimensionResource(id = R.dimen.padding_small))
    ) {
        for (item in FolkWorkType.entries) {
            NavigationRailItem(
                selected = item == currentFolkWorkType,
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

@Composable
fun BigLeftBar(
    selectedType: FolkWorkType,
    onTabClick: (FolkWorkType) -> Unit
) {
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
                label = {
                    Text(
                        text = stringResource(id = item.textId)
                    )
                },
                selected = item == selectedType,
                onClick = { onTabClick(item) },
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconId),
                        contentDescription = stringResource(id = item.textId),
                        tint = MaterialTheme.colorScheme.surfaceTint
                    )
                },
                modifier = Modifier.padding(
                    bottom = dimensionResource(id = R.dimen.padding_small)
                )
            )
        }
        Spacer(modifier = Modifier.weight(3F))
    }
}
