package br.com.usercentrics.core_design_system.components.button

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.usercentrics.core_design_system.preview.DevicesPreview
import br.com.usercentrics.core_design_system.theme.ButtonPrimaryContainerDisabled
import br.com.usercentrics.core_design_system.theme.ButtonPrimaryContainerEnabled
import br.com.usercentrics.core_design_system.theme.ButtonPrimaryContentDisabled
import br.com.usercentrics.core_design_system.theme.ButtonPrimaryContentEnabled
import br.com.usercentrics.core_design_system.theme.AppChallengeTheme

@Composable
fun ButtonPrimary(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    style: TextStyle = MaterialTheme.typography.titleMedium,
    textAlign: TextAlign = TextAlign.Center,
    onClick: () -> Unit,
) {
    ElevatedButton(
        enabled = enabled,
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = ButtonPrimaryContainerEnabled,
            contentColor = ButtonPrimaryContentEnabled,
            disabledContainerColor = ButtonPrimaryContainerDisabled,
            disabledContentColor = ButtonPrimaryContentDisabled,
        ),
        shape = RoundedCornerShape(8.dp),
        onClick = onClick,
    ) {
        Text(
            text = text,
            style = style,
            textAlign = textAlign,
            modifier = modifier.padding(vertical = 12.dp, horizontal = 24.dp),
        )
    }
}

@DevicesPreview
@Composable
fun ButtonPreview(modifier: Modifier = Modifier) {
    AppChallengeTheme {
        Surface(modifier = modifier.fillMaxHeight()) {
            Column {
                ButtonPrimary(text = "Button Primary - enabled") { /* onClick */ }
                ButtonPrimary(text = "Button Primary - disabled", enabled = false) { /* onClick */ }
            }
        }
    }
}