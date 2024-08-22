package com.viktoriagavrosh.fairytales.ui

/**
 * State of Ui screen
 */
sealed class ScreenState<out T : Any>(val data: T? = null) {
    class None<T : Any> : ScreenState<T>()
    class Success<T : Any>(data: T) : ScreenState<T>(data = data)
    class Error<T : Any> : ScreenState<T>()
}
