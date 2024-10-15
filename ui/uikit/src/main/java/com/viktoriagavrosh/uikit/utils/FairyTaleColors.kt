package com.viktoriagavrosh.uikit.utils

import androidx.compose.ui.graphics.Color

// TODO delete this !
object FairyTaleColors {
    private val colors = listOf(
        Color(0xFFDDE3EA),
        Color(0xFFEAE4DD),
        Color(0xFFEADDE3),
        Color(0xFFE4DDEA),
    )

    val textColor = Color(0xFF001E2F)

    fun getRandomColor(): Color = colors.random()
}
