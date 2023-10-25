package com.fikrisandi.framework.network

sealed class ViewState<out T>{

    data object Loading: ViewState<Nothing>()
    data object Empty: ViewState<Nothing>()
    data class Failed(val exception: Throwable, val message: String): ViewState<Nothing>()
    data class Success<out T>(val data: T): ViewState<T>()
}