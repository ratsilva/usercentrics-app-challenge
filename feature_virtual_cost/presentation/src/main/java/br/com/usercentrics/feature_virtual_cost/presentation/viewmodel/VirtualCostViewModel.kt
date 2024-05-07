package br.com.usercentrics.feature_virtual_cost.presentation.viewmodel

import android.content.Context
import androidx.compose.runtime.Immutable
import androidx.lifecycle.viewModelScope
import br.com.usercentrics.core_feature_arch.presentation.BaseAction
import br.com.usercentrics.core_feature_arch.presentation.BaseState
import br.com.usercentrics.core_feature_arch.presentation.BaseViewModel
import br.com.usercentrics.feature_virtual_cost.domain.usecase.GetServiceCostUseCase
import br.com.usercentrics.feature_virtual_cost.presentation.viewmodel.VirtualCostViewModel.Action
import br.com.usercentrics.feature_virtual_cost.presentation.viewmodel.VirtualCostViewModel.UiState
import br.com.usercentrics.plugin_data_privacy.service.DataPrivacyService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VirtualCostViewModel(
    private val virtualCostUseCase: GetServiceCostUseCase,
    private val dataPrivacyService: DataPrivacyService
) : BaseViewModel<UiState, Action>(UiState.Loading) {

    init {
        fetchVirtualCost()
    }

    fun onShowConsentButtonClick(context: Context) {
        viewModelScope.launch(Dispatchers.Main) {
            dataPrivacyService.collectConsent(context)
        }
    }

    private fun fetchVirtualCost() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                virtualCostUseCase().collect { virtualCost ->
                    sendAction(Action.ContentVirtualCost(virtualCost))
                }
            } catch (e: Exception) {
                sendAction(Action.Error)
            }
        }
    }

    @Immutable
    sealed interface Action : BaseAction<UiState> {
        data class ContentVirtualCost(private val virtualCost: Double) : Action {
            override fun mapToState(state: UiState) = UiState.ContentVirtualCost(virtualCost)
        }

        data object Error : Action {
            override fun mapToState(state: UiState) = UiState.Error
        }
    }

    @Immutable
    sealed interface UiState : BaseState {
        data class ContentVirtualCost(val virtualCost: Double) : UiState
        data object Loading : UiState
        data object Error : UiState
    }

}