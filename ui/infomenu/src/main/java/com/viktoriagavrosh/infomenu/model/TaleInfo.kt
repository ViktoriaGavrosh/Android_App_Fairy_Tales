package com.viktoriagavrosh.infomenu.model

/**
 * Model represents a single tale for ui - InfoScreen
 *
 * @param id unique object identifier
 * @param title tale`s title
 * @param isFavorite boolean parameter describes tale is favorite
 * @param imageUrl url of image for tale
 */
data class TaleInfo(
    val id: Int = 0,
    val title: String = "",
    val isFavorite: Boolean = false,
    val imageUrl: String = "",
)
