package com.viktoriagavrosh.uikit.utils

/**
 * State of Ui screens
 */
sealed class ScreenState<out T : Any>(val data: T? = null) {
    class None<T : Any> : ScreenState<T>()
    class Success<T : Any>(data: T) : ScreenState<T>(data = data)
    class Error<T : Any> : ScreenState<T>()
}
