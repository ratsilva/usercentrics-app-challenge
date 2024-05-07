package br.com.usercentrics.feature_virtual_cost.data.datasource.api.model

import br.com.usercentrics.feature_virtual_cost.data.datasource.database.model.CostRuleEntity
import br.com.usercentrics.feature_virtual_cost.data.datasource.database.model.CostRuleEntity.CostRuleDataEntity
import br.com.usercentrics.feature_virtual_cost.domain.model.CostRule
import br.com.usercentrics.feature_virtual_cost.domain.model.CostRule.CostRuleData
import br.com.usercentrics.feature_virtual_cost.domain.model.Strategy
import com.squareup.moshi.Json

data class CostRuleDto(
    @Json(name = "name") val name: String,
    @Json(name = "enabled") val enabled: Boolean,
    @Json(name = "cost_factor") val costFactor: Double,
    @Json(name = "rule") val rule: CostRuleDataDto,
) {
    data class CostRuleDataDto(
        @Json(name = "strategy") val strategy: String,
        @Json(name = "data") val data: Map<String, Any>
    )
}

fun CostRuleDto.toDomain() = CostRule(
    name = this.name,
    enabled = this.enabled,
    costFactor = this.costFactor,
    rule = CostRuleData(
        strategy = Strategy.valueOf(this.rule.strategy),
        data = this.rule.data
    )
)

fun CostRuleDto.toEntity() = CostRuleEntity(
    name = this.name,
    enabled = this.enabled,
    costFactor = this.costFactor,
    rule = CostRuleDataEntity(
        strategy = this.rule.strategy,
        data = this.rule.data
    )
)