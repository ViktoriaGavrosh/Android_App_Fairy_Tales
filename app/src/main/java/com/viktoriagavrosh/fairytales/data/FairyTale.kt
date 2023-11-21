package com.viktoriagavrosh.fairytales.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

class FairyTale(
    @DrawableRes val imageId: Int,
    @StringRes val nameId: Int,
    @StringRes val shortTextId: Int
)