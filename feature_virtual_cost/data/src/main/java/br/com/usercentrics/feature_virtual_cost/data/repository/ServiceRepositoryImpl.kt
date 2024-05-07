package br.com.usercentrics.feature_virtual_cost.data.repository

import br.com.usercentrics.feature_virtual_cost.domain.model.Service
import br.com.usercentrics.feature_virtual_cost.domain.repository.ServiceRepository
import br.com.usercentrics.plugin_data_privacy.model.DataService
import br.com.usercentrics.plugin_data_privacy.service.DataPrivacyService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ServiceRepositoryImpl(
    private val dataPrivacyService: DataPrivacyService
) : ServiceRepository {
    override suspend fun getConsentedServices(): Flow<List<Service>> =
        flow {
            dataPrivacyService.isReady().collect { isReady ->
                if (isReady) {
                    dataPrivacyService.collectedServices().collect { dataServices ->
                        dataServices.map { dataService ->
                            dataService.toService()
                        }.also {
                            emit(it)
                        }
                    }
                }
            }
        }
}

fun DataService.toService() = Service(
    id = this.id,
    name = this.name,
    description = this.description,
    collectedData = this.collectedData
)