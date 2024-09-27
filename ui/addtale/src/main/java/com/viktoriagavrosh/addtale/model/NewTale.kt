package com.viktoriagavrosh.addtale.model

import com.viktoriagavrosh.addtale.utils.TaleGenre

internal data class NewTale(
    val id: Int = 0,
    val title: String = "",
    val text: String = "",
    val taleGenre: TaleGenre = TaleGenre.Animal,
    val isNight: Boolean = false,
    val isChangeable: Boolean = true,
)
