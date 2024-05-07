package br.com.usercentrics.feature_virtual_cost.domain.calculator.strategy

import br.com.usercentrics.feature_virtual_cost.domain.calculator.CalculatorStrategy
import br.com.usercentrics.feature_virtual_cost.domain.calculator.applyFactorTo
import br.com.usercentrics.feature_virtual_cost.domain.model.CostRule
import br.com.usercentrics.feature_virtual_cost.domain.model.ServiceCost

class CalculatorAndStrategy(
    private val costRule: CostRule
) :
    CalculatorStrategy {
    override suspend fun calculate(serviceCost: ServiceCost): Double {
        if (serviceCost.service.collectedData.containsAll(costRule.extractData())) {
            return costRule.applyFactorTo(serviceCost.cost)
        }
        return serviceCost.cost
    }
}

private fun CostRule.extractData(): List<String> {
    return (this.rule.data["list"] as List<*>).map { it.toString() }
}