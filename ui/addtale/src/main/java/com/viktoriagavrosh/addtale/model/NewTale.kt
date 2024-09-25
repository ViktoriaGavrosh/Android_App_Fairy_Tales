package com.viktoriagavrosh.addtale.model

import com.viktoriagavrosh.repositories.utils.ShelfGenre

data class NewTale(
    val id: Int = 0,
    val title: String = "",
    val text: String = "",
    val genre: ShelfGenre = ShelfGenre.Tales.Animal,
    val isNight: Boolean = false,
)
