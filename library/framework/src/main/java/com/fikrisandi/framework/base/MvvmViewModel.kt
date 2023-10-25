package com.fikrisandi.framework.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class MvvmViewModel: ViewModel() {

    private val _alert = MutableStateFlow(Triple(false, "", false))
    val alert get() = _alert.asStateFlow()

    private val handler = CoroutineExceptionHandler { _, throwable ->
        handleError(throwable)
    }

    protected fun handleError(e: Throwable) {
        showAlert(e.message.orEmpty(), status = false)
    }

    protected fun safeLaunch(block: suspend CoroutineScope.() -> Unit) = viewModelScope.launch(
        handler, block = block
    )

    protected fun showAlert(message: String, status: Boolean) = viewModelScope.launch {
        _alert.emit(
            Triple(true, message, status)
        )
    }

    fun endAlert() = viewModelScope.launch {
        _alert.emit(
            Triple(false, "", false)
        )
    }
}