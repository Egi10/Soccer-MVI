package com.bajapuik.soccer_mvi.domain.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed class Result<out T> {
    data object Loading : Result<Nothing>()
    data class Success<out T>(val data: T) : Result<T>()
    data class Error<out T>(val message: String, val data: T? = null) : Result<Nothing>()
}

fun <T> Flow<T>.asResult(): Flow<Result<T>> {
    return this
        .map<T, Result<T>> { data ->
            Result.Success(data)
        }
        .onStart {
            emit(Result.Loading)
        }
        .catch { exception ->
            emit(Result.Error<T>(exception.message ?: "An error occurred"))
        }
}


