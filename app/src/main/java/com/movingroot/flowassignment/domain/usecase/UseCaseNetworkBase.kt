package com.movingroot.flowassignment.domain.usecase

import com.movingroot.flowassignment.data.model.ApiResult

open class UseCaseNetworkBase {
    suspend fun <T> getNullableResult(call: suspend () -> ApiResult<T>): ApiResult<T>? {
        return try {
            call.invoke()
        } catch (_: Exception) {
            null
        }
    }
}
