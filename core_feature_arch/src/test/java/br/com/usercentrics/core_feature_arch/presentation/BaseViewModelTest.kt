package br.com.usercentrics.core_feature_arch.presentation

import app.cash.turbine.test
import br.com.usercentrics.core_feature_arch.domain.FakeUseCase
import br.com.usercentrics.core_feature_arch.util.MainDispatcherRule
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BaseViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val useCase: FakeUseCase = mockk()
    private lateinit var viewModel: FakeViewModel

    @Before
    fun setup() {
        coEvery {
            useCase()
        } returns flowOf("data")

        viewModel = FakeViewModel(useCase)
    }

    @After
    fun clear(){
        clearAllMocks()
    }

    @Test
    fun `should starts with Loading state`() = runTest {
        viewModel.uiState.test {
            Assert.assertEquals(
                BaseUiState.Loading,
                awaitItem()
            )

            ensureAllEventsConsumed()
        }

    }

    @Test
    fun `should emit success values after fetch data successfully`() = runTest {
        viewModel.uiState.test {
            Assert.assertEquals(
                BaseUiState.Loading,
                awaitItem()
            )

            viewModel.retrieveData()

            Assert.assertEquals(
                BaseUiState.Content("data"),
                awaitItem()
            )

            ensureAllEventsConsumed()

            coVerify(exactly = 1) {
                useCase()
            }
        }
    }

    @Test
    fun `should emit error values after fetch data failure`() = runTest {
        viewModel.uiState.test {
            Assert.assertEquals(
                BaseUiState.Loading,
                awaitItem()
            )

            val error = Exception("exception")
            coEvery {
                useCase()
            } returns flow { throw error }

            viewModel.retrieveData()

            Assert.assertEquals(
                BaseUiState.Error("exception"),
                awaitItem()
            )

            ensureAllEventsConsumed()

            coVerify(exactly = 1) {
                useCase()
            }
        }
    }

    @Test
    fun `should not emit duplicated states`() = runTest {
        viewModel.uiState.test {
            Assert.assertEquals(
                BaseUiState.Loading,
                awaitItem()
            )

            viewModel.retrieveData()

            Assert.assertEquals(
                BaseUiState.Content("data"),
                awaitItem()
            )

            viewModel.retrieveData()

            ensureAllEventsConsumed()

            coVerify(exactly = 2) {
                useCase()
            }
        }
    }

}
