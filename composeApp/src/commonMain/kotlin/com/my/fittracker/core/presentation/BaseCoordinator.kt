package com.my.fittracker.core.presentation

abstract class BaseCoordinator<T: BaseState, T1: BaseScreenAction>(
    viewModel: BaseViewModel<T>
) {
    val uiState = viewModel.uiState
    val progressBarIsShown = viewModel.progressBarIsShown

    abstract fun createActions(): T1
}