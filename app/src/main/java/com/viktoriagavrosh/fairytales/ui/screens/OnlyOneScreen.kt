package com.viktoriagavrosh.fairytales.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.data.CatalogFairyTales
import com.viktoriagavrosh.fairytales.data.FolkWorkType
import com.viktoriagavrosh.fairytales.model.Composition
import com.viktoriagavrosh.fairytales.model.FolkWork
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme
import com.viktoriagavrosh.fairytales.ui.utils.FairyTalesContentType
import com.viktoriagavrosh.fairytales.ui.utils.FairyTalesNavigationType

@Composable
fun OnlyOneScreen(
    folkWorks: List<FolkWork>,
    currentFolkWorkType: FolkWorkType,
    selectedWork: FolkWork,
    navigationType: FairyTalesNavigationType,
    contentType: FairyTalesContentType,
    isShowHomeScreen: Boolean,
    onTabClick: (FolkWorkType) -> Unit,
    onCardClick: (FolkWork) -> Unit,
    onDetailScreenBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (isShowHomeScreen) {
        Row(
            modifier = modifier
        ) {
            AnimatedVisibility(
                visible = navigationType == FairyTalesNavigationType.NAVIGATION_RAIL
            ) {
                FairyTalesNavigationRail(
                    currentFolkWorkType = currentFolkWorkType,
                    onTabClick = onTabClick,
                    modifier = Modifier.fillMaxHeight()
                )
            }
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                OnlyScreenTopBar(
                    text = stringResource(id = currentFolkWorkType.textId),
                    isShowHomeScreen = true,
                    onDetailScreenBackClick = onDetailScreenBackClick
                )
                ListCompositionsScreen(
                    folkWorks = folkWorks,
                    currentFolkWorkType = currentFolkWorkType,
                    selectedWork = selectedWork,
                    navigationType = navigationType,
                    onCardClick = onCardClick,
                    modifier = Modifier.weight(1F)
                )
                AnimatedVisibility(
                    visible = navigationType == FairyTalesNavigationType.BOTTOM_NAVIGATION
                ) {
                    BottomNavigateBar(
                        currentFolkWorkType = currentFolkWorkType,
                        onTabClick = onTabClick,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            OnlyScreenTopBar(
                text = selectedWork.title,
                isShowHomeScreen = false,
                onDetailScreenBackClick = onDetailScreenBackClick
            )
            DetailScreen(
                currentFolkWorkType = currentFolkWorkType,
                selectedWork = selectedWork,
                contentType = contentType,
                onDetailScreenBackClick = onDetailScreenBackClick
            )
        }
    }
}

@Composable
private fun OnlyScreenTopBar(
    text: String,
    isShowHomeScreen: Boolean,
    onDetailScreenBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (!isShowHomeScreen){
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
private fun BottomNavigateBar(
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
                        tint = MaterialTheme.colorScheme.surfaceTint
                    )
                }
            )
        }
    }
}

@Preview    //(widthDp = 700)
@Composable
fun OnlyOneScreenPreview() {
    FairyTalesTheme {
        OnlyOneScreen(
            folkWorks = emptyList(),
            currentFolkWorkType = FolkWorkType.Story,
            selectedWork = FolkWork(
                id = 0,
                genre = "story",
                title = "Story",
                text = "Story",
                answer = null,
                imageUri = null,
                audioUri = null,
                isFavorite = false
            ),
            navigationType = FairyTalesNavigationType.BOTTOM_NAVIGATION,
            contentType = FairyTalesContentType.LIST_ONLY,
            isShowHomeScreen = false,
            onTabClick = {},
            onCardClick = {},
            onDetailScreenBackClick = {}
        )
    }
}