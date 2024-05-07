package br.com.usercentrics.feature_virtual_cost.data.datasource.api.service

import br.com.usercentrics.feature_virtual_cost.data.datasource.api.response.GetCostRuleResponse
import retrofit2.http.GET

interface CostRuleService {
    @GET("/cost_rules")
    suspend fun getAll(): GetCostRuleResponse
}