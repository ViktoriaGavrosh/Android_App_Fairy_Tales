package com.viktoriagavrosh.navigation

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Composable entry point of application UI
 *
 * @param windowSize parameter describes screen orientation
 * @param modifier the modifier to be applied to the layout
 */
@Composable
fun FairyTalesApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier,
) {
    val isVerticalScreen = windowSize == WindowWidthSizeClass.Compact

    AppNavHost(
        isVerticalScreen = isVerticalScreen,
        modifier = modifier,
    )
}
