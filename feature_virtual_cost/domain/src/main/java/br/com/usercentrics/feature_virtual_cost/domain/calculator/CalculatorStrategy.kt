package br.com.usercentrics.feature_virtual_cost.domain.calculator

import br.com.usercentrics.feature_virtual_cost.domain.model.ServiceCost

interface CalculatorStrategy {
    suspend fun calculate(serviceCost: ServiceCost): Double
}