package com.viktoriagavrosh.ui.uiscreens.utils

import com.viktoriagavrosh.ui.model.FolkWork
import com.viktoriagavrosh.ui.uiscreens.FolkWorkType

/**
 * All functions for UI
 */
data class UILogic(
    val onTabClick: (FolkWorkType) -> Unit = {},
    val onHeartClick: (FolkWork) -> Unit = {},
    val onTopBarHeartClick: () -> Unit = {},
)
