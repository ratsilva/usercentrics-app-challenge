package br.com.usercentrics.feature_virtual_cost.domain.repository

import br.com.usercentrics.feature_virtual_cost.domain.model.CostRule
import kotlinx.coroutines.flow.Flow

interface CostRuleRepository {
    suspend fun getAllActive(onlyCache: Boolean = true): Flow<List<CostRule>>
}