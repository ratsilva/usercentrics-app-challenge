package br.com.usercentrics.feature_virtual_cost.domain.usecase

import br.com.usercentrics.feature_virtual_cost.domain.calculator.CalculatorStrategyFactory
import br.com.usercentrics.feature_virtual_cost.domain.model.ServiceCost
import br.com.usercentrics.feature_virtual_cost.domain.repository.CostRuleRepository
import kotlinx.coroutines.flow.first

class CalculateServiceCostRulesUseCase(
    private val costRuleRepository: CostRuleRepository,
    private val calculatorStrategyFactory: CalculatorStrategyFactory,
) {
    suspend operator fun invoke(serviceCost: ServiceCost): ServiceCost =
        costRuleRepository.getAllActive().first()
            .fold(serviceCost) { updatedServiceCost, costRule ->
                updatedServiceCost.copy(
                    cost = calculatorStrategyFactory.createFromCostRule(costRule)
                        .calculate(updatedServiceCost)
                )
            }
}