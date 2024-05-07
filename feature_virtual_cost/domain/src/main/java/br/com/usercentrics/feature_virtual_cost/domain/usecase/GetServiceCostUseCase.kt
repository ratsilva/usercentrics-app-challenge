package br.com.usercentrics.feature_virtual_cost.domain.usecase

import br.com.usercentrics.feature_virtual_cost.domain.model.toServiceCost
import br.com.usercentrics.feature_virtual_cost.domain.repository.ServiceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.logging.Logger

class GetServiceCostUseCase(
    private val serviceRepository: ServiceRepository,
    private val calculateServiceDataTypeCostUseCase: CalculateServiceDataTypeCostUseCase,
    private val calculateServiceCostRulesUseCase: CalculateServiceCostRulesUseCase
) {
    companion object {
        val Log: Logger = Logger.getLogger(GetServiceCostUseCase::class.java.name)
    }

    suspend operator fun invoke(): Flow<Double> = flow {
        serviceRepository.getConsentedServices().collect { services ->
            services.map { service ->
                calculateServiceCostRulesUseCase(
                    calculateServiceDataTypeCostUseCase(service.toServiceCost())
                ).also { serviceCost ->
                    Log.info("${serviceCost.service.name} = ${serviceCost.cost}")
                }
            }.sumOf { it.cost }.also {
                Log.info("Total = $it")
                emit(it)
            }
        }
    }
}