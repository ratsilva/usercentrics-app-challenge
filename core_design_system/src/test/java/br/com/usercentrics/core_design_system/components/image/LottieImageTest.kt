package br.com.usercentrics.core_design_system.components.image

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.airbnb.lottie.LottieTask
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.Executor

class LottieImageTest {

    @get: Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_5
    )

    @Before
    fun setup() {
        LottieTask.EXECUTOR = Executor(Runnable::run)
    }

    @Test
    fun `LottieImage - error`() {
        paparazzi.snapshot("lottie-image-error") {
            LottieImage(
                resource = LottieResource.Error
            )
        }
    }

    @Test
    fun `LottieImage - loading`() {
        paparazzi.snapshot("lottie-image-loading") {
            LottieImage(
                resource = LottieResource.Loading
            )
        }
    }
}