package com.viktoriagavrosh.fairytales.ui.utils

import com.viktoriagavrosh.fairytales.data.FolkWorkType
import com.viktoriagavrosh.fairytales.model.FolkWork

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