package com.poid.baseline.light.presentation.ui_model

sealed class RequestUiState<out T> {
    object Idle : RequestUiState<Nothing>()
    object Loading : RequestUiState<Nothing>()
    data class Success<T>(val data: T) : RequestUiState<T>()
    data class Error(val error: Throwable) : RequestUiState<Nothing>()
}

data class MasterListItemUiModel(
    val someData: String
)