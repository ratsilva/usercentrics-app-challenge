package br.com.usercentrics.feature_virtual_cost.domain.model

data class ServiceCost(
    val service: Service,
    val cost: Double
) {
    constructor(service: Service) : this(service = service, cost = 0.0)
}