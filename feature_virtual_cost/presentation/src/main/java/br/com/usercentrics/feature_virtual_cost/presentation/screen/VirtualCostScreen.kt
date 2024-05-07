package br.com.usercentrics.feature_virtual_cost.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.usercentrics.core_design_system.components.image.LottieImage
import br.com.usercentrics.core_design_system.components.image.LottieResource
import br.com.usercentrics.core_design_system.components.text.TextBodyMedium
import br.com.usercentrics.core_design_system.components.text.TextDisplayLarge
import br.com.usercentrics.core_design_system.preview.DevicesPreview
import br.com.usercentrics.core_design_system.theme.AppChallengeTheme
import br.com.usercentrics.core_design_system.tokens.Dimen
import br.com.usercentrics.feature_virtual_cost.presentation.R
import br.com.usercentrics.feature_virtual_cost.presentation.screen.components.VirtualCostButton
import br.com.usercentrics.feature_virtual_cost.presentation.screen.preview.VirtualCostPreviewParameterProvider
import br.com.usercentrics.feature_virtual_cost.presentation.viewmodel.VirtualCostViewModel
import br.com.usercentrics.feature_virtual_cost.presentation.viewmodel.VirtualCostViewModel.UiState
import org.koin.androidx.compose.koinViewModel

@Composable
fun VirtualCostScreen(
    modifier: Modifier = Modifier,
    viewModel: VirtualCostViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    VirtualCostScreen(
        modifier = modifier,
        uiState = uiState,
        onShowConsentButtonClick = { viewModel.onShowConsentButtonClick(context) }
    )
}

@Composable
fun VirtualCostScreen(
    modifier: Modifier = Modifier,
    uiState: UiState,
    onShowConsentButtonClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxSize()
            .padding(Dimen.spacing06)
    ) {

        when (uiState) {
            is UiState.ContentVirtualCost -> VirtualCostSuccessScreen(
                uiState.virtualCost,
                onShowConsentButtonClick
            )

            UiState.Error -> VirtualCostErrorScreen()
            UiState.Loading -> VirtualCostLoadingScreen()
        }
    }
}

@Composable
fun VirtualCostSuccessScreen(virtualCost: Double, onShowConsentButtonClick: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextDisplayLarge(
                text = virtualCost.toString(),
                modifier = Modifier.padding(bottom = Dimen.spacing03)
            )
            TextBodyMedium(text = stringResource(id = R.string.score_label))
        }
        VirtualCostButton(
            text = R.string.show_banner_button_title,
            onClick = onShowConsentButtonClick,
        )
    }
}

@Composable
fun VirtualCostLoadingScreen() {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieImage(
            modifier = Modifier
                .weight(1f)
                .padding(Dimen.spacing16),
            resource = LottieResource.Loading
        )
        VirtualCostButton(
            text = R.string.show_banner_button_title,
            enabled = false,
        ) {}
    }
}

@Composable
fun VirtualCostErrorScreen() {
    Box(contentAlignment = Alignment.Center) {
        LottieImage(
            modifier = Modifier.align(Alignment.Center),
            resource = LottieResource.Error
        )
    }
}

@DevicesPreview
@Composable
fun ConsentManagementScreenPreview(
    @PreviewParameter(VirtualCostPreviewParameterProvider::class) uiState: UiState
) {
    AppChallengeTheme {
        VirtualCostScreen(
            uiState = uiState,
            onShowConsentButtonClick = { }
        )
    }
}