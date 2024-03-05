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
import com.viktoriagavrosh.fairytales.ui.FairyTalesUiState
import com.viktoriagavrosh.fairytales.ui.elements.BigLeftBar
import com.viktoriagavrosh.fairytales.ui.elements.BottomNavigateBar
import com.viktoriagavrosh.fairytales.ui.elements.FairyTalesNavigationRail
import com.viktoriagavrosh.fairytales.ui.utils.UILogic

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
                    BigLeftBar(
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
                    currentFolkWorkType = uiState.folkWorkType,
                    onTabClick = logic.onTabClick,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        else -> {
            Row(
                modifier = modifier
            ) {
                FairyTalesNavigationRail(
                    currentFolkWorkType = uiState.folkWorkType,
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