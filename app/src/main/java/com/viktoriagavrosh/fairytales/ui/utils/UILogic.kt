package com.viktoriagavrosh.fairytales.ui.utils

import com.viktoriagavrosh.fairytales.data.TaleType
import com.viktoriagavrosh.fairytales.model.FolkWork

/**
 * All functions for UI
 */
data class UILogic(
    val onTabClick: (TaleType) -> Unit = {},
    val onCardClick: (FolkWork) -> Unit = {},
    val onDetailScreenBackClick: () -> Unit = {},
    val onHeartClick: (FolkWork) -> Unit = {},
    val onTopBarHeartClick: () -> Unit = {}
)
