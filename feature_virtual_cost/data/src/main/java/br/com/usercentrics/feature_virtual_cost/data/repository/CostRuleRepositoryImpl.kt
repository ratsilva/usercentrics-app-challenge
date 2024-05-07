package br.com.usercentrics.feature_virtual_cost.data.repository

import br.com.usercentrics.feature_virtual_cost.data.datasource.api.model.toEntity
import br.com.usercentrics.feature_virtual_cost.data.datasource.api.service.CostRuleService
import br.com.usercentrics.feature_virtual_cost.data.datasource.database.dao.CostRuleDao
import br.com.usercentrics.feature_virtual_cost.data.datasource.database.model.toDomain
import br.com.usercentrics.feature_virtual_cost.domain.model.CostRule
import br.com.usercentrics.feature_virtual_cost.domain.repository.CostRuleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CostRuleRepositoryImpl(
    private val costRuleApiService: CostRuleService,
    private val costRuleDao: CostRuleDao,
) : CostRuleRepository {
    override suspend fun getAllActive(onlyCache: Boolean): Flow<List<CostRule>> =
        costRuleDao.getAllActive().map { costRules ->
            costRules.map { it.toDomain() }
        }.also {
            if (!onlyCache) {
                costRuleApiService.getAll().costRules.also { costRules ->
                    if (costRules.isNotEmpty()) {
                        costRuleDao.upsertAll(costRules.map { it.toEntity() })
                    }
                }
            }
        }
}