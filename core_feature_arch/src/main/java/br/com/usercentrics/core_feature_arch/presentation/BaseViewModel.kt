package br.com.usercentrics.core_feature_arch.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val StopTimeoutMillis: Long = 5000

abstract class BaseViewModel<State : BaseState, Action : BaseAction<State>>(initialState: State) :
    ViewModel() {

    private val _uiStateFlow = MutableStateFlow(initialState)

    val uiState = _uiStateFlow.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(StopTimeoutMillis),
        initialState
    )

    fun sendAction(action: Action) {
        viewModelScope.launch {
            _uiStateFlow.update { previousState ->
                action.mapToState(previousState)
            }
        }
    }
}