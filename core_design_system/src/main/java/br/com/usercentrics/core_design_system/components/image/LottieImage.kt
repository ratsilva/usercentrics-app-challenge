package br.com.usercentrics.core_design_system.components.image

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import br.com.usercentrics.core_design_system.preview.DevicesPreview
import br.com.usercentrics.core_design_system.theme.AppChallengeTheme
import br.com.usercentrics.core_design_system.tokens.Dimen
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LottieImage(modifier: Modifier = Modifier, resource: LottieResource) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(resource.file))
    LottieAnimation(
        modifier = modifier,
        composition = composition,
        iterations = LottieConstants.IterateForever
    )
}

@DevicesPreview
@Composable
fun LottieImagePreview(modifier: Modifier = Modifier) {
    AppChallengeTheme {
        Surface(modifier = modifier.fillMaxHeight()) {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                LottieImage(
                    modifier = Modifier.requiredSize(Dimen.Image.large),
                    resource = LottieResource.Loading
                )
                LottieImage(
                    modifier = Modifier.requiredSize(Dimen.Image.large),
                    resource = LottieResource.Error
                )
            }
        }
    }
}