package com.viktoriagavrosh.repositories.model

import com.viktoriagavrosh.repositories.utils.ShelfGenre

/**
 * Model represents a single folk for repository
 *
 * @param id unique object identifier
 * @param genre genre of folk
 * @param title folk`s title
 * @param text folk`s text
 * @param imageUrl url of image for folk
 */
data class Folk(
    val id: Int = 0,
    val genre: ShelfGenre = ShelfGenre.Folks.Poem,
    val title: String = "",
    val text: String = "",
    val imageUrl: String? = null,
) : Retaining

/**
 * interface for all repository models that are exposed to ui
 *
 * @see Folk
 * @see Riddle
 * @see Tale
 * @see Settings
 */
interface Retaining
