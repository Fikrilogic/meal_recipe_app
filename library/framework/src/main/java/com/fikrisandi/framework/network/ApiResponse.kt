package com.fikrisandi.framework.network

sealed class ApiResult<out T>{

    data class Success<out T>(
        val data: T,
    ): ApiResult<T>()

    data class Failed(
        val exception: Throwable
    ): ApiResult<Nothing>()
}