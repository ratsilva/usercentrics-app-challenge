package usecase

import DataFixtures
import app.cash.turbine.test
import br.com.usercentrics.feature_virtual_cost.domain.model.toServiceCost
import br.com.usercentrics.feature_virtual_cost.domain.repository.ServiceRepository
import br.com.usercentrics.feature_virtual_cost.domain.usecase.CalculateServiceCostRulesUseCase
import br.com.usercentrics.feature_virtual_cost.domain.usecase.CalculateServiceDataTypeCostUseCase
import br.com.usercentrics.feature_virtual_cost.domain.usecase.GetServiceCostUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class GetServiceCostUseCaseTest {

    private val serviceRepository: ServiceRepository = mockk()
    private val calculateServiceDataTypeCostUseCase: CalculateServiceDataTypeCostUseCase = mockk()
    private val calculateServiceCostRulesUseCase: CalculateServiceCostRulesUseCase = mockk()

    private val useCase = GetServiceCostUseCase(
        serviceRepository,
        calculateServiceDataTypeCostUseCase,
        calculateServiceCostRulesUseCase
    )

    @Test
    fun `should not calculate cost when services is empty`() = runTest {

        coEvery {
            serviceRepository.getConsentedServices()
        } returns emptyFlow()


        useCase().test {
            awaitComplete()
        }

        coVerify(exactly = 0) {
            calculateServiceDataTypeCostUseCase(any())
            calculateServiceCostRulesUseCase(any())
        }

    }

    @Test
    fun `should calculate cost when services is not empty`() = runTest {

        val dataFromRepository = DataFixtures.Service.fromRepository
        coEvery {
            serviceRepository.getConsentedServices()
        } returns flowOf(dataFromRepository)

        val service1CostInitial = dataFromRepository[0].toServiceCost()
        val service1CostDataType = service1CostInitial.copy(cost = 5.0)
        val service1CostCostRule = service1CostDataType.copy(cost = 7.0)
        coEvery {
            calculateServiceDataTypeCostUseCase(service1CostInitial)
        } returns service1CostDataType

        coEvery {
            calculateServiceCostRulesUseCase(service1CostDataType)
        } returns service1CostCostRule

        val service2CostInitial = dataFromRepository[1].toServiceCost()
        val service2CostDataType = service2CostInitial.copy(cost = 3.0)
        val service2CostCostRule = service2CostDataType.copy(cost = 13.0)
        coEvery {
            calculateServiceDataTypeCostUseCase(service2CostInitial)
        } returns service2CostDataType

        coEvery {
            calculateServiceCostRulesUseCase(service2CostDataType)
        } returns service2CostCostRule

        useCase().test {

            Assert.assertEquals(
                20.0,
                awaitItem(),
                0.0
            )

            awaitComplete()
        }

        coVerify(exactly = 2) {
            calculateServiceDataTypeCostUseCase(any())
            calculateServiceCostRulesUseCase(any())
        }

    }

}