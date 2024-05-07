package br.com.usercentrics.feature_virtual_cost.presentation.screen

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import br.com.usercentrics.core_design_system.theme.AppChallengeTheme
import br.com.usercentrics.feature_virtual_cost.presentation.viewmodel.VirtualCostViewModel.UiState
import org.junit.Rule
import org.junit.Test

class VirtualCostScreenTest {

    @get: Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private fun setup(uiState: UiState) {
        composeTestRule.setContent {
            AppChallengeTheme {
                VirtualCostScreen(
                    uiState = uiState,
                    onShowConsentButtonClick = { }
                )
            }
        }
    }

    @Test
    fun verifyLoadingState() {

        setup(UiState.Loading)

        composeTestRule.onNodeWithTag(VirtualCostScreenTags.Loading.IMAGE, useUnmergedTree = true)
            .assertExists()
        composeTestRule.onNodeWithTag(VirtualCostScreenTags.Loading.BUTTON, useUnmergedTree = true)
            .assertExists().assertTextEquals("Show Consent Banner")
    }

    @Test
    fun verifyErrorState() {

        setup(UiState.Error)

        composeTestRule.onNodeWithTag(VirtualCostScreenTags.Error.IMAGE, useUnmergedTree = true)
            .assertExists()
    }

    @Test
    fun verifySuccessState() {

        setup(UiState.ContentVirtualCost(10.0))

        composeTestRule.onNodeWithTag(
            VirtualCostScreenTags.Success.TEXT_ONE,
            useUnmergedTree = true
        ).assertExists().assertTextEquals("10.0")

        composeTestRule.onNodeWithTag(
            VirtualCostScreenTags.Success.TEXT_TWO,
            useUnmergedTree = true
        ).assertExists().assertTextEquals("Consent Score")

        composeTestRule.onNodeWithTag(
            VirtualCostScreenTags.Success.BUTTON,
            useUnmergedTree = true
        ).assertExists().assertTextEquals("Show Consent Banner")

    }

}