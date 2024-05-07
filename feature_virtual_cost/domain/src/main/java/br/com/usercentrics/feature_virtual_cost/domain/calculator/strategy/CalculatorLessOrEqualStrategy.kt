package br.com.usercentrics.feature_virtual_cost.domain.calculator.strategy

import br.com.usercentrics.feature_virtual_cost.domain.calculator.CalculatorStrategy
import br.com.usercentrics.feature_virtual_cost.domain.model.CostRule
import br.com.usercentrics.feature_virtual_cost.domain.model.ServiceCost

class CalculatorLessOrEqualStrategy(
    private val costRule: CostRule
) : CalculatorStrategy {
    override suspend fun calculate(serviceCost: ServiceCost): Double {
        if (serviceCost.service.collectedData.size <= costRule.extractData()) {
            return serviceCost.cost + ((serviceCost.cost * costRule.costFactor) / 100)
        }
        return serviceCost.cost
    }
}

private fun CostRule.extractData() = (this.rule.data["limit"] as Double).toInt()