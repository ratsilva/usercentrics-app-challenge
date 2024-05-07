package br.com.usercentrics.feature_virtual_cost.presentation.viewmodel

import app.cash.turbine.test
import br.com.usercentrics.feature_virtual_cost.domain.usecase.GetServiceCostUseCase
import br.com.usercentrics.feature_virtual_cost.presentation.util.MainDispatcherRule
import br.com.usercentrics.feature_virtual_cost.presentation.viewmodel.VirtualCostViewModel.UiState
import br.com.usercentrics.plugin_data_privacy.service.DataPrivacyService
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class VirtualCostViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val virtualCostUseCase: GetServiceCostUseCase = mockk()

    private val dataPrivacyService: DataPrivacyService = mockk()

    private lateinit var viewModel: VirtualCostViewModel

    private val virtualCost = 75.0

    @Before
    fun setup() {
        coEvery {
            virtualCostUseCase()
        } returns flow {
            delay(100)
            emit(virtualCost)
        }

        viewModel = VirtualCostViewModel(virtualCostUseCase, dataPrivacyService)
    }

    @After
    fun clear() {
        clearAllMocks()
    }

    @Test
    fun `should starts with Loading state`() = runTest {
        viewModel.uiState.test {
            Assert.assertEquals(
                UiState.Loading,
                awaitItem()
            )
            cancelAndIgnoreRemainingEvents()
        }

    }

    @Test
    fun `should fetch virtual cost successfully`() = runTest {
        viewModel.uiState.test {
            Assert.assertEquals(
                UiState.Loading,
                awaitItem()
            )

            Assert.assertEquals(
                UiState.ContentVirtualCost(virtualCost),
                awaitItem()
            )

            cancelAndIgnoreRemainingEvents()
        }

    }

    @Test
    fun `should fetch virtual cost with error`() = runTest {

        coEvery {
            virtualCostUseCase()
        } returns flow {
            delay(100)
            throw Exception("error")
        }

        viewModel = VirtualCostViewModel(virtualCostUseCase, dataPrivacyService)

        viewModel.uiState.test {
            Assert.assertEquals(
                UiState.Loading,
                awaitItem()
            )

            Assert.assertEquals(
                UiState.Error,
                awaitItem()
            )

            cancelAndIgnoreRemainingEvents()
        }

    }
}