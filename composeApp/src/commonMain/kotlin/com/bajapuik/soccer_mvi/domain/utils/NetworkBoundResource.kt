package com.bajapuik.soccer_mvi.domain.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * Created by Julsapargi Nursam on 10/11/24
 * Mobile Engineer - Android
 */

class NetworkBoundResource(
    private val dispatcher: CoroutineDispatcher
) {
    fun <T, R> execute(
        localFetch: suspend () -> T?,
        remoteFetch: suspend () -> R,
        saveFetchResult: suspend (R) -> Unit,
        shouldFetch: () -> Boolean = { true }
    ): Flow<Result<T>> = flow {
        emit(Result.Loading)

        try {
            // Fetch from local storage
            val localData = localFetch()

            if (localData != null) {
                emit(Result.Success(localData))
            }

            // Check if should fetch from network
            if (shouldFetch()) {
                try {
                    val remoteData = remoteFetch()

                    saveFetchResult(remoteData)

                    val newLocalData = localFetch()
                    if (newLocalData != null) {
                        emit(Result.Success(newLocalData))
                    }
                } catch (e: Exception) {
                    emit(
                        Result.Error(
                            message = e.message ?: "Unknown error occurred",
                            data = localFetch()
                        )
                    )
                }
            }
        } catch (e: Exception) {
            emit(
                Result.Error<T>(
                    message = e.message ?: "Unknown error occurred",
                    data = localFetch()
                )
            )
        }
    }.flowOn(dispatcher)
}