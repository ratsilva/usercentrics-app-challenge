package br.com.usercentrics.plugin_data_privacy.model

import com.usercentrics.sdk.v2.settings.data.UsercentricsService
data class Service(
    val id: String,
    val description: String,
    val collectedData: List<String>
)

fun UsercentricsService.toService() = Service(
    id = this.templateId ?: "unknown_service_id",
    description = this.descriptionOfService,
    collectedData = this.dataCollectedList
)