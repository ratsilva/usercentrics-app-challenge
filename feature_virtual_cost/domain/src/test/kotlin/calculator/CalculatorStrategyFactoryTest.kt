package calculator

import DataFixtures
import br.com.usercentrics.feature_virtual_cost.domain.calculator.DefaultCalculatorStrategyFactory
import br.com.usercentrics.feature_virtual_cost.domain.calculator.strategy.CalculatorAndStrategy
import br.com.usercentrics.feature_virtual_cost.domain.calculator.strategy.CalculatorLessOrEqualStrategy
import org.junit.Assert
import org.junit.Test

class CalculatorStrategyFactoryTest {

    private val factory = DefaultCalculatorStrategyFactory()

    @Test
    fun `should create factory for AND rule`() {

        val costRule = DataFixtures.CostRule.costRuleAnd

        val strategy = factory.createFromCostRule(costRule)

        Assert.assertTrue(strategy is CalculatorAndStrategy)

    }

    @Test
    fun `should create factory for LESS_OR_EQUAL rule`() {

        val costRule = DataFixtures.CostRule.costRuleLessOrEqual

        val strategy = factory.createFromCostRule(costRule)

        Assert.assertTrue(strategy is CalculatorLessOrEqualStrategy)

    }

}