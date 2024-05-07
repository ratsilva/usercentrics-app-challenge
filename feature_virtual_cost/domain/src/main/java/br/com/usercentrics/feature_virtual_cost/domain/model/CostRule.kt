package br.com.usercentrics.feature_virtual_cost.domain.model

enum class Strategy {
    AND,
    LESS_OR_EQUAL
}

data class CostRule(
    val name: String,
    val enabled: Boolean,
    val costFactor: Double,
    val rule: CostRuleData
) {
    data class CostRuleData(
        val strategy: Strategy,
        val data: Map<String, Any>
    )
}