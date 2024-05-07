package br.com.usercentrics.feature_virtual_cost.domain.repository

import br.com.usercentrics.feature_virtual_cost.domain.model.Service
import kotlinx.coroutines.flow.Flow

interface ServiceRepository {
    suspend fun getConsentedServices(): Flow<List<Service>>

}