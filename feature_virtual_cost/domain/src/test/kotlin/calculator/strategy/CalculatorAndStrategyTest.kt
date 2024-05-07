package calculator.strategy

import DataFixtures
import br.com.usercentrics.feature_virtual_cost.domain.calculator.strategy.CalculatorAndStrategy
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class CalculatorAndStrategyTest {

    private val costRule = DataFixtures.CostRule.costRuleAnd

    private val strategy = CalculatorAndStrategy(costRule)

    @Test
    fun `should return initial cost when rule does not match`() = runBlocking {

        val serviceCost = DataFixtures.ServiceCost.serviceCostAndNotMatches

        val result = strategy.calculate(serviceCost)

        assertEquals(10.0, result, 0.0)

    }

    @Test
    fun `should return initial cost when rule does match`() = runBlocking {

        val serviceCost = DataFixtures.ServiceCost.serviceCostAndMatches

        val result = strategy.calculate(serviceCost)

        assertEquals(11.0, result, 0.0)

    }

}