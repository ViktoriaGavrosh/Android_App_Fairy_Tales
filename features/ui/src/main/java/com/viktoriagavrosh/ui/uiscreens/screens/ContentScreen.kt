package com.viktoriagavrosh.fairytales.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.viktoriagavrosh.ui.uiscreens.FairyTalesUiState
import com.viktoriagavrosh.fairytales.ui.elements.bars.BottomNavigateBar
import com.viktoriagavrosh.fairytales.ui.elements.bars.ExpandedScreenVerticalBar
import com.viktoriagavrosh.fairytales.ui.elements.bars.VerticalNavigationRail
import com.viktoriagavrosh.fairytales.ui.utils.UILogic

/**
 * Composable to display different screens depending on window size
 */
@Composable
fun ContentScreen(
    uiState: FairyTalesUiState,
    logic: UILogic,
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier
) {
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
                HomeScreen(
                    uiState = uiState,
                    logic = logic,
                    isExpandedScreen = true
                )
            }
        }

        WindowWidthSizeClass.Compact -> {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                HomeScreen(
                    uiState = uiState,
                    logic = logic,
                    isExpandedScreen = false,
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
                HomeScreen(
                    uiState = uiState,
                    logic = logic,
                    isExpandedScreen = false
                )
            }
        }
    }
}
