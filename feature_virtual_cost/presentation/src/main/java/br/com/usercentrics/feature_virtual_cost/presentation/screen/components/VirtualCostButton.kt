package br.com.usercentrics.feature_virtual_cost.presentation.screen.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import br.com.usercentrics.core_design_system.components.button.ButtonPrimary

@Composable
fun VirtualCostButton(
    modifier: Modifier = Modifier,
    @StringRes text: Int,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    ButtonPrimary(
        modifier = modifier.fillMaxWidth(),
        text = stringResource(id = text),
        enabled = enabled,
        onClick = onClick,
    )
}