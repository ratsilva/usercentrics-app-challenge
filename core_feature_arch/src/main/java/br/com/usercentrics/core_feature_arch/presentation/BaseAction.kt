package br.com.usercentrics.core_feature_arch.presentation

interface BaseAction<State> {
    fun mapToState(state: State): State
}
