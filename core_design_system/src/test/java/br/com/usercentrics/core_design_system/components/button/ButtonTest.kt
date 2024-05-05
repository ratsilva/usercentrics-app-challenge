package br.com.usercentrics.core_design_system.components.button

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test

class ButtonTest {

    @get: Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_5
    )

    @Test
    fun `ButtonPrimary - enabled`() {
        paparazzi.snapshot("button-primary-enabled") {
            ButtonScope {
                ButtonPrimary(
                    text = "Button Primary - enabled",
                    enabled = true
                ) {}
            }
        }
    }

    @Test
    fun `ButtonPrimary - disabled`() {
        paparazzi.snapshot("button-primary-disabled") {
            ButtonScope {
                ButtonPrimary(
                    text = "Button Primary - disabled",
                    enabled = false
                ) {}
            }
        }
    }
}

@Suppress("TestFunctionName")
@Composable
fun ButtonScope(content: @Composable () -> Unit) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.weight(1.0f))
        content()
    }
}