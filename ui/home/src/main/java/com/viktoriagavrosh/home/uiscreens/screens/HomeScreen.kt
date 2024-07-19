package com.viktoriagavrosh.home.uiscreens.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.viktoriagavrosh.fairytales.model.FolkWork
import com.viktoriagavrosh.home.uiscreens.FairyTalesViewModel
import com.viktoriagavrosh.home.uiscreens.elements.bars.BottomNavigateBar
import com.viktoriagavrosh.home.uiscreens.elements.bars.ExpandedScreenVerticalBar
import com.viktoriagavrosh.home.uiscreens.elements.bars.VerticalNavigationRail
import com.viktoriagavrosh.home.uiscreens.utils.UILogic

/**
 * Composable to display different screens depending on window size
 */
@Composable
fun HomeScreen(
    windowSize: WindowWidthSizeClass,
    onCardClick: (FolkWork) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FairyTalesViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val logic = UILogic(
        onTabClick = viewModel::updateCompositionType,
        onHeartClick = viewModel::updateWorkFavorite,
        onTopBarHeartClick = viewModel::updateListFavoriteWorks,
    )

    when (windowSize) {
        WindowWidthSizeClass.Expanded -> {
            PermanentNavigationDrawer(
                drawerContent = {
                    ExpandedScreenVerticalBar(
                        selectedType = uiState.folkWorkType,
                        onTabClick = logic.onTabClick
                    )
                }
            ) {
                ContentScreen(
                    uiState = uiState,
                    logic = logic,
                    onCardClick = onCardClick,
                    isExpandedScreen = true
                )
            }
        }

        WindowWidthSizeClass.Compact -> {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                ContentScreen(
                    uiState = uiState,
                    logic = logic,
                    isExpandedScreen = false,
                    onCardClick = onCardClick,
                    modifier = Modifier.weight(1F)
                )
                BottomNavigateBar(
                    selectedType = uiState.folkWorkType,
                    onTabClick = logic.onTabClick,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        else -> {
            Row(
                modifier = modifier
            ) {
                VerticalNavigationRail(
                    selectedType = uiState.folkWorkType,
                    onTabClick = logic.onTabClick,
                    modifier = Modifier.fillMaxHeight()
                )
                ContentScreen(
                    uiState = uiState,
                    logic = logic,
                    onCardClick = onCardClick,
                    isExpandedScreen = false
                )
            }
        }
    }
}
