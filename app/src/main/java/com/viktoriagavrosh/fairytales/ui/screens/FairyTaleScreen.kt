package com.viktoriagavrosh.fairytales.ui.screens

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.viktoriagavrosh.fairytales.ui.FairyTalesViewModel
import com.viktoriagavrosh.fairytales.ui.utils.FairyTalesContentType
import com.viktoriagavrosh.fairytales.ui.utils.FairyTalesNavigationType


@Composable
fun FairyTalesApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier
) {
    val viewModel: FairyTalesViewModel = viewModel()
    val uiState = viewModel.uiState.collectAsState().value
    val navigationType: FairyTalesNavigationType
    val contentType: FairyTalesContentType

    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            navigationType = FairyTalesNavigationType.BOTTOM_NAVIGATION
            contentType = FairyTalesContentType.LIST_ONLY
        }

        WindowWidthSizeClass.Medium -> {
            navigationType = FairyTalesNavigationType.NAVIGATION_RAIL
            contentType = FairyTalesContentType.LIST_ONLY
        }

        WindowWidthSizeClass.Expanded -> {
            navigationType = FairyTalesNavigationType.PERMANENT_NAVIGATION_DRAWER
            contentType = FairyTalesContentType.LIST_AND_DETAILS
        }

        else -> {
            navigationType = FairyTalesNavigationType.BOTTOM_NAVIGATION
            contentType = FairyTalesContentType.LIST_ONLY
        }
    }

    FairyTalesHomeScreen(
        navigationType = navigationType,
        contentType = contentType,
        uiState = uiState,
        onTabClick = { compositionType ->
            viewModel.updateCompositionType(compositionType = compositionType)
        },
        onCardClick = { composition ->
            viewModel.navigateToDetailScreen(composition = composition)
        },
        onDetailScreenBackClick = {
            viewModel.navigateToHomeScreen()
        },
        modifier = modifier
    )
}

@Preview
@Composable
fun FairyTalesAppPreview() {
    FairyTalesApp(
        windowSize = WindowWidthSizeClass.Compact
    )
}
