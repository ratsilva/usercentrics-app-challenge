package br.com.usercentrics.core_design_system.components.text

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test

class TextTest {

    @get: Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_5
    )

    @Test
    fun `TextBodyMedium - default`() {
        paparazzi.snapshot("text-body-medium") {
            TextBodyMedium(
                text = "Text Body Medium"
            )
        }
    }

    @Test
    fun `TextDisplayLarge - default`() {
        paparazzi.snapshot("text-display-large") {
            TextDisplayLarge(
                text = "Text Display Large"
            )
        }
    }
}