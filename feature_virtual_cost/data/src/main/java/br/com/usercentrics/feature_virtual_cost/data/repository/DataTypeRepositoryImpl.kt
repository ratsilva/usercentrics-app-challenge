package br.com.usercentrics.feature_virtual_cost.data.repository

import br.com.usercentrics.feature_virtual_cost.data.datasource.api.model.toEntity
import br.com.usercentrics.feature_virtual_cost.data.datasource.api.service.DataTypeService
import br.com.usercentrics.feature_virtual_cost.data.datasource.database.dao.DataTypeDao
import br.com.usercentrics.feature_virtual_cost.data.datasource.database.model.toDomain
import br.com.usercentrics.feature_virtual_cost.domain.model.DataType
import br.com.usercentrics.feature_virtual_cost.domain.repository.DataTypeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataTypeRepositoryImpl(
    private val dataTypeApiService: DataTypeService,
    private val dataTypeDao: DataTypeDao,
) : DataTypeRepository {
    override suspend fun getAll(onlyCache: Boolean): Flow<List<DataType>> =
        dataTypeDao.getAll().map { dataTypes ->
            dataTypes.map { it.toDomain() }
        }.also {
            if (!onlyCache) {
                dataTypeApiService.getAll().dataTypes.also { dataTypes ->
                    if (dataTypes.isNotEmpty()) {
                        dataTypeDao.upsertAll(dataTypes.map { it.toEntity() })
                    }
                }
            }
        }
}