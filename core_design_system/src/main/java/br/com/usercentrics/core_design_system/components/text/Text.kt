package br.com.usercentrics.core_design_system.components.text

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.usercentrics.core_design_system.preview.DevicesPreview
import br.com.usercentrics.core_design_system.theme.AppChallengeTheme

@Composable
fun TextBodyMedium(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.bodyMedium,
    )
}

@Composable
fun TextDisplayLarge(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.displayLarge,
    )
}

@DevicesPreview
@Composable
fun TextPreview(modifier: Modifier = Modifier){
    AppChallengeTheme {
        Surface(modifier = modifier.fillMaxHeight()) {
            Column {
                TextBodyMedium("Text Body Medium")
                TextDisplayLarge("Text Display Large")
            }
        }
    }
}
