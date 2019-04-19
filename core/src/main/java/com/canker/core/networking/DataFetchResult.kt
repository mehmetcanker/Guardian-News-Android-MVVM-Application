package com.canker.core.networking


sealed class DataFetchResult<T> {

    data class Progress<T>(var loading: Boolean) : DataFetchResult<T>()
    data class Success<T>(var data: T) : DataFetchResult<T>()
    data class Failure<T>(val e: Throwable) : DataFetchResult<T>()

    companion object {
        fun <T> loading(isLoading: Boolean): DataFetchResult<T> = Progress(isLoading)
        fun <T> success(data: T): DataFetchResult<T> = Success(data)
        fun <T> failure(e: Throwable): DataFetchResult<T> = Failure(e)
    }
}
