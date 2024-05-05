package br.com.usercentrics.core_feature_arch.presentation

import androidx.compose.runtime.Immutable

interface BaseState

/**
 * A [BaseUiState] implements the LCE pattern (Loading, Content and Error).
 */
@Immutable
sealed interface BaseUiState : BaseState {
    data class Content<T>(val data: T) : BaseUiState
    data object Loading : BaseUiState
    data class Error(val errorMessage: String? = null) : BaseUiState
}