package calculator.strategy

import DataFixtures
import br.com.usercentrics.feature_virtual_cost.domain.calculator.strategy.CalculatorLessOrEqualStrategy
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class CalculatorLessOrEqualStrategyTest {

    private val costRule = DataFixtures.CostRule.costRuleLessOrEqual

    private val strategy = CalculatorLessOrEqualStrategy(costRule)

    @Test
    fun `should return initial cost when rule does not match`() = runBlocking {

        val serviceCost = DataFixtures.ServiceCost.serviceCostLessOrEqualNotMatches

        val result = strategy.calculate(serviceCost)

        Assert.assertEquals(10.0, result, 0.0)

    }

    @Test
    fun `should return initial cost when rule does match`() = runBlocking {

        val serviceCost = DataFixtures.ServiceCost.serviceCostLessOrEqualMatches

        val result = strategy.calculate(serviceCost)

        Assert.assertEquals(11.0, result, 0.0)

    }
}