package br.com.usercentrics.core_design_system.components.image

import androidx.annotation.RawRes
import br.com.usercentrics.core_design_system.R

enum class LottieResource(@RawRes val file: Int) {
    Error(R.raw.error_lottie),
    Loading(R.raw.loading_lottie)
}