package com.viktoriagavrosh.fairytales.ui.screens

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
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.data.FolkWorkType
import com.viktoriagavrosh.fairytales.model.FolkWork
import com.viktoriagavrosh.fairytales.ui.FairyTalesUiState
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme
import com.viktoriagavrosh.fairytales.ui.utils.FairyTalesContentType
import com.viktoriagavrosh.fairytales.ui.utils.FairyTalesNavigationType

@Composable
fun FairyTalesHomeScreen(
    navigationType: FairyTalesNavigationType,
    contentType: FairyTalesContentType,
    uiState: FairyTalesUiState,
    onTabClick: (FolkWorkType) -> Unit,
    onCardClick: (FolkWork) -> Unit,
    onDetailScreenBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (navigationType == FairyTalesNavigationType.PERMANENT_NAVIGATION_DRAWER) {
        PermanentNavigationDrawer(
            drawerContent = {
                PermanentDrawerSheet(
                    modifier = Modifier.width(
                        dimensionResource(id = R.dimen.permanent_drawer_sheet_width)
                    )
                ) {
                    NavigationDrawerContent(
                        selectedFolkWorkType = uiState.folkWorkType,
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
        ) {
            ListAndDetailScreen(
                folkWorks = uiState.folkWorks,
                currentFolkWorkType = uiState.folkWorkType,
                selectedWork = uiState.selectedWork,
                navigationType = navigationType,
                contentType = contentType,
                onCardClick = onCardClick,
                modifier = modifier
            )
        }
    } else {
        OnlyOneScreen(
            folkWorks = uiState.folkWorks,
            currentFolkWorkType = uiState.folkWorkType,
            selectedWork = uiState.selectedWork,
            navigationType = navigationType,
            contentType = contentType,
            isShowHomeScreen = uiState.isShowHomeScreen,
            onTabClick = onTabClick,
            onCardClick = onCardClick,
            onDetailScreenBackClick = onDetailScreenBackClick,
            modifier = modifier
        )
    }
}

@Composable
private fun NavigationDrawerContent(
    selectedFolkWorkType: FolkWorkType,
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
        Spacer(
            modifier = Modifier
                .weight(1F)
        )
        for (item in FolkWorkType.entries) {
            NavigationDrawerItem(
                label = {
                    Text(
                        text = stringResource(id = item.textId)
                    )
                },
                selected = item == selectedFolkWorkType,
                onClick = { onTabClick(item) },
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconId),
                        contentDescription = null,       // TODO my сделать обязательно!!!
                        tint = MaterialTheme.colorScheme.surfaceTint
                    )
                },
                modifier = Modifier.padding(
                    bottom = dimensionResource(id = R.dimen.padding_small)
                )
            )
        }
        Spacer(
            modifier = Modifier
                .weight(3F)
        )
    }
}

@Preview
@Composable
fun FairyTalesHomeScreenPreview() {
    FairyTalesTheme {
        FairyTalesHomeScreen(
            navigationType = FairyTalesNavigationType.BOTTOM_NAVIGATION,
            contentType = FairyTalesContentType.LIST_ONLY,
            uiState = FairyTalesUiState(),
            onTabClick = {},
            onCardClick = {},
            onDetailScreenBackClick = {})
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun FairyTalesHomeScreenTabletPreview() {
    FairyTalesTheme {
        FairyTalesHomeScreen(
            navigationType = FairyTalesNavigationType.PERMANENT_NAVIGATION_DRAWER,
            contentType = FairyTalesContentType.LIST_AND_DETAILS,
            uiState = FairyTalesUiState(),
            onTabClick = {},
            onCardClick = {},
            onDetailScreenBackClick = {})
    }
}