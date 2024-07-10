package com.viktoriagavrosh.fairytales.ui.utils

import com.viktoriagavrosh.fairytales.model.FolkWork
import com.viktoriagavrosh.ui.uiscreens.FolkWorkType

/**
 * All functions for UI
 */
data class UILogic(
    val onTabClick: (FolkWorkType) -> Unit = {},
    val onCardClick: (FolkWork) -> Unit = {},
    val onDetailScreenBackClick: () -> Unit = {},
    val onHeartClick: (FolkWork) -> Unit = {},
    val onTopBarHeartClick: () -> Unit = {}
)
