package br.com.usercentrics.core_design_system.preview

import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Preview(showSystemUi = true, showBackground = true, device = Devices.PHONE)
@Preview(showSystemUi = true, showBackground = true, device = Devices.PIXEL_TABLET)
annotation class DevicesPreview