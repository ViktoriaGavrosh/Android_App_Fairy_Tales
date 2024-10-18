package com.viktoriagavrosh.addtale.model

import com.viktoriagavrosh.addtale.utils.TaleGenre

/**
 * Model represents a single tale for ui - AddTaleScreen
 *
 * @param id unique object identifier
 * @param title tale`s title
 * @param text tale`s text
 * @param taleGenre genre of tale
 * @param isNight boolean parameter describes tale is night
 * @param isChangeable boolean parameter describes tale can be changed
 */
internal data class NewTale(
    val id: Int = 0,
    val title: String = "",
    val text: String = "",
    val taleGenre: TaleGenre = TaleGenre.Animal,
    val isNight: Boolean = false,
    val isChangeable: Boolean = true,
)
