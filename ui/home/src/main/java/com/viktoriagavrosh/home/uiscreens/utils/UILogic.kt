package com.viktoriagavrosh.home.uiscreens.utils

import com.viktoriagavrosh.fairytales.model.FolkWork
import com.viktoriagavrosh.home.uiscreens.FolkWorkType

/**
 * All functions for UI
 */
data class UILogic(
    val onTabClick: (FolkWorkType) -> Unit = {},
    val onHeartClick: (FolkWork) -> Unit = {},
    val onTopBarHeartClick: () -> Unit = {},
)
