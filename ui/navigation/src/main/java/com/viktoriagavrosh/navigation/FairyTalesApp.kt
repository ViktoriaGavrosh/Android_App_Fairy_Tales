package com.viktoriagavrosh.navigation

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Top level composable that represents screens for the application
 */
@Composable
fun FairyTalesApp(
    modifier: Modifier = Modifier,
    windowSize: WindowWidthSizeClass,
) {
    val isVerticalScreen = windowSize == WindowWidthSizeClass.Compact

    AppNavHost(
        isVerticalScreen = isVerticalScreen,
        modifier = modifier,
    )
}
