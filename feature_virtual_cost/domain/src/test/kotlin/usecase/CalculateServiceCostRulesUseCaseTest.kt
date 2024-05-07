package usecase

import DataFixtures
import br.com.usercentrics.feature_virtual_cost.domain.calculator.CalculatorStrategy
import br.com.usercentrics.feature_virtual_cost.domain.calculator.CalculatorStrategyFactory
import br.com.usercentrics.feature_virtual_cost.domain.repository.CostRuleRepository
import br.com.usercentrics.feature_virtual_cost.domain.usecase.CalculateServiceCostRulesUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class CalculateServiceCostRulesUseCaseTest {

    private val costRuleRepository: CostRuleRepository = mockk()
    private val calculatorStrategyFactory: CalculatorStrategyFactory = mockk()
    private val calculatorStrategy: CalculatorStrategy = mockk()

    private val useCase =
        CalculateServiceCostRulesUseCase(costRuleRepository, calculatorStrategyFactory)

    private val serviceCost = DataFixtures.ServiceCost.serviceCostAndMatches

    @Test
    fun `should calculate service cost with CostRule`() = runBlocking {

        val costRule = DataFixtures.CostRule.costRuleAnd
        coEvery {
            costRuleRepository.getAllActive()
        } returns flowOf(listOf(costRule))

        coEvery {
            calculatorStrategyFactory.createFromCostRule(costRule)
        } returns calculatorStrategy

        coEvery {
            calculatorStrategy.calculate(serviceCost)
        } returns (serviceCost.cost + 1.0)

        val result = useCase(serviceCost)

        Assert.assertEquals(
            11.0,
            result.cost,
            0.0
        )

        coVerify(exactly = 1) {
            costRuleRepository.getAllActive()
            calculatorStrategyFactory.createFromCostRule(any())
        }

    }
}