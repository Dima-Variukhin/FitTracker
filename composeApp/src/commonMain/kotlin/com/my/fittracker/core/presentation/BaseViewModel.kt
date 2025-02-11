package com.my.fittracker.core.presentation

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

open class BaseViewModel<T : BaseState>(
    state: T,
) : ViewModel(), DefaultLifecycleObserver {
    private val _uiState = MutableStateFlow(state)
    val uiState: StateFlow<T> = _uiState.asStateFlow()

    private val stateLock = Mutex()

    private val _progressBarIsShown = MutableStateFlow(false)
    val progressBarIsShown = _progressBarIsShown.asStateFlow()

    protected val exceptionHandler = SupervisorJob() + Dispatchers.Main + ExceptionHandler { error ->
        println(">>>>! caught error = $error")
        println(">>>>! error.message = ${error.message}")
//        when (error) {
//            is ResponseException -> handleResponseMistake(error)
//            is HttpException -> {
//                if (error.code() != NO_INTERNET_CONNECTION_CODE
//                    || error.message() != NO_INTERNET_CONNECTION) {
//                    showSnackBar(context.getString(R.string.undefined_mistake))
//                }
//            }
//            else -> {
//                showSnackBar(context.getString(R.string.undefined_mistake))
//            }
//        }
        hideProgressBar()
    }

    fun showProgressBar() {
        _progressBarIsShown.update { true }
    }

    fun hideProgressBar() {
        _progressBarIsShown.update { false }
    }

    protected fun updateState(onUpdate: (T) -> T) {
        _uiState.update {
            onUpdate(it)
        }
    }

    protected suspend fun updateStateSynchronized(onUpdate: (T) -> T) {
        stateLock.withLock {
            updateState(onUpdate)
        }
    }

    @Suppress("FunctionName")
    private inline fun ExceptionHandler(crossinline handler: (Throwable) -> Unit): CoroutineExceptionHandler {
        return CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            handler(throwable)
        }
    }
}