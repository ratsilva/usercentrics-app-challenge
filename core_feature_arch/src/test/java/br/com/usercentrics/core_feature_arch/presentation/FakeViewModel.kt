package br.com.usercentrics.core_feature_arch.presentation

import androidx.compose.runtime.Immutable
import androidx.lifecycle.viewModelScope
import br.com.usercentrics.core_feature_arch.domain.FakeUseCase
import br.com.usercentrics.core_feature_arch.presentation.BaseUiState.*
import br.com.usercentrics.core_feature_arch.presentation.FakeViewModel.Action
import kotlinx.coroutines.launch

class FakeViewModel(private val useCase: FakeUseCase) :
    BaseViewModel<BaseUiState, Action>(Loading) {
    fun retrieveData() {
        viewModelScope.launch{
            try {
                useCase().collect{
                    sendAction(Action.ContentLoadSuccess(it))
                }
            }catch (e: Exception){
                sendAction(Action.ContentLoadFailure(e.message ?: ""))
            }
        }
    }

    @Immutable
    sealed interface Action : BaseAction<BaseUiState> {
        class ContentLoadSuccess(private val data: String) : Action {
            override fun mapToState(state: BaseUiState) = Content(data)
        }

        class ContentLoadFailure(private val errorMessage: String) : Action {
            override fun mapToState(state: BaseUiState) = Error(errorMessage = errorMessage)
        }
    }

}