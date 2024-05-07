package br.com.usercentrics.feature_virtual_cost.data.datasource.api.response

import br.com.usercentrics.feature_virtual_cost.data.datasource.api.model.CostRuleDto
import com.squareup.moshi.Json

data class GetCostRuleResponse(
    @Json(name = "cost_rules") val costRules: List<CostRuleDto>
)