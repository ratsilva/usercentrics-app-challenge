package br.com.usercentrics.feature_virtual_cost.domain.calculator

import br.com.usercentrics.feature_virtual_cost.domain.calculator.strategy.CalculatorAndStrategy
import br.com.usercentrics.feature_virtual_cost.domain.calculator.strategy.CalculatorLessOrEqualStrategy
import br.com.usercentrics.feature_virtual_cost.domain.model.CostRule
import br.com.usercentrics.feature_virtual_cost.domain.model.Strategy

interface CalculatorStrategyFactory {
    fun createFromCostRule(costRule: CostRule): CalculatorStrategy
}

class DefaultCalculatorStrategyFactory : CalculatorStrategyFactory {
    override fun createFromCostRule(costRule: CostRule) =
        when (costRule.rule.strategy) {
            Strategy.AND -> CalculatorAndStrategy(costRule)
            Strategy.LESS_OR_EQUAL -> CalculatorLessOrEqualStrategy(costRule)
        }
}