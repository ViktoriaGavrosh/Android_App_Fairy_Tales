package com.viktoriagavrosh.fairytales.ui.utils

import com.viktoriagavrosh.fairytales.data.FolkWorkType
import com.viktoriagavrosh.fairytales.model.FolkWork

data class UILogic(
    val onTabClick: (FolkWorkType) -> Unit = {},
    val onCardClick: (FolkWork) -> Unit = {},
    val onDetailScreenBackClick: () -> Unit = {},
    val onHeartClicked: (FolkWork) -> Unit = {},
    val onTopBarHeartClicked: () -> Unit = {}
)