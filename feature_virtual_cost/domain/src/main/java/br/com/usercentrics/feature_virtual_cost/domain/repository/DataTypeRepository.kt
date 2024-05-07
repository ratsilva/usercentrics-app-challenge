package br.com.usercentrics.feature_virtual_cost.domain.repository

import br.com.usercentrics.feature_virtual_cost.domain.model.DataType
import kotlinx.coroutines.flow.Flow

interface DataTypeRepository {
    suspend fun getAll(onlyCache: Boolean = true): Flow<List<DataType>>
}