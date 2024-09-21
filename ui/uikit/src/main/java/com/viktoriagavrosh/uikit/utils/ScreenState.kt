package com.viktoriagavrosh.uikit.utils

import com.viktoriagavrosh.repositories.utils.RequestResult

/**
 * State of Ui screens
 */
sealed class ScreenState<out T : Any>(val data: T? = null) {
    class None<T : Any> : ScreenState<T>()
    class Success<T : Any>(data: T) : ScreenState<T>(data = data)
    class Error<T : Any> : ScreenState<T>()
}

fun <T : Any, S : Any> RequestResult<T>.toScreenState(convertData: (T) -> S): ScreenState<S> {
    return when (this) {
        is RequestResult.Success -> ScreenState.Success(
            data = convertData(data)
        )

        is RequestResult.Error -> ScreenState.Error()
    }
}
