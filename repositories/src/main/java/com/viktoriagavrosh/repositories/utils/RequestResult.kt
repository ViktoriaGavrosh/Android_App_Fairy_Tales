package com.viktoriagavrosh.repositories.utils

/**
 * Data source query result
 *
 * @param data get from data source
 */
sealed class RequestResult<out T : Any>(open val data: T? = null) {

    /**
     * request completed success. Data received.
     *
     * @param data get from data source
     */
    class Success<T : Any>(override val data: T) : RequestResult<T>(data)

    /**
     * request completed with error. Data didn't receive.
     *
     * @param error what exception was thrown
     */
    class Error<T : Any>(val error: Throwable? = null) : RequestResult<T>()
}

/**
 * convert types of data.
 *
 * @param mapper how conversion should be done
 * @return [RequestResult] with data of new type
 */
fun <I : Any, O : Any> RequestResult<I>.map(mapper: (I) -> O): RequestResult<O> {
    return when (this) {
        is RequestResult.Success -> RequestResult.Success(mapper(data))
        is RequestResult.Error -> RequestResult.Error()
    }
}
