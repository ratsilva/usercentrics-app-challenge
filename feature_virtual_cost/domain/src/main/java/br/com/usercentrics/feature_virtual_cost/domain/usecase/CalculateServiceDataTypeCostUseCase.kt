package br.com.usercentrics.feature_virtual_cost.domain.usecase

import br.com.usercentrics.feature_virtual_cost.domain.model.ServiceCost
import br.com.usercentrics.feature_virtual_cost.domain.repository.DataTypeRepository
import kotlinx.coroutines.flow.first

class CalculateServiceDataTypeCostUseCase(
    private val dataTypeRepository: DataTypeRepository
) {
    suspend operator fun invoke(serviceCost: ServiceCost): ServiceCost = serviceCost.copy(
        cost = dataTypeRepository.getAll().first()
            .filter {
                serviceCost.service.collectedData.contains(it.description)
            }.sumOf {
                it.cost
            }
    )
}