package br.com.usercentrics.feature_virtual_cost.domain.model

data class Service(
    val id: String,
    val name: String,
    val description: String,
    val collectedData: List<String>
)

fun Service.toServiceCost() = ServiceCost(this)