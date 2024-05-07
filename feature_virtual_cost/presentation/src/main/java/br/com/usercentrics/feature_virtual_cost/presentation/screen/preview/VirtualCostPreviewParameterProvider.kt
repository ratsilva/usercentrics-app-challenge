package br.com.usercentrics.feature_virtual_cost.presentation.screen.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import br.com.usercentrics.feature_virtual_cost.presentation.viewmodel.VirtualCostViewModel.UiState

class VirtualCostPreviewParameterProvider :
    PreviewParameterProvider<UiState> {
    override val values = sequenceOf(
        UiState.Loading,
        UiState.Error,
        UiState.ContentVirtualCost(virtualCost = -100.0),
        UiState.ContentVirtualCost(virtualCost = 0.0),
        UiState.ContentVirtualCost(virtualCost = 100.0),
        UiState.ContentVirtualCost(virtualCost = 12.34),
    )
}