package com.viktoriagavrosh.fairytales.ui.screens

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.viktoriagavrosh.fairytales.ui.FairyTalesViewModel
import com.viktoriagavrosh.fairytales.ui.utils.FairyTalesContentType
import com.viktoriagavrosh.fairytales.ui.utils.FairyTalesNavigationType


@Composable
fun FairyTalesApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier
) {
    val viewModel: FairyTalesViewModel = viewModel(factory = FairyTalesViewModel.factory)
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()   // TODO my сделать через by  и collectAsStateWithLifecycle
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

    /*
    val works by viewModel.allStoriesState.collectAsStateWithLifecycle()

    val games by viewModel.getAllGamesStream().collectAsState(emptyList())

     */

    if (uiState.folkWorks.isNotEmpty()) {      // TODO my т к в State первый List приходит пустой (неизвестно почему)
        FairyTalesHomeScreen(
            navigationType = navigationType,
            contentType = contentType,
            uiState = uiState,
            onTabClick = { compositionType ->
                viewModel.updateCompositionType(folkWorkType = compositionType)
            },
            onCardClick = { folkWork ->
                viewModel.navigateToDetailScreen(folkWork = folkWork)    // TODO my try viewModel::navigateToDetailScreen
            },
            onDetailScreenBackClick = {
                viewModel.navigateToHomeScreen()
            },
            modifier = modifier
        )
    }
}

@Preview
@Composable
fun FairyTalesAppPreview() {
    FairyTalesApp(
        windowSize = WindowWidthSizeClass.Compact
    )
}
