package br.com.usercentrics.plugin_data_privacy.model

import com.usercentrics.sdk.v2.settings.data.UsercentricsService

data class DataService(
    val id: String,
    val name: String,
    val description: String,
    val collectedData: List<String>
)

fun UsercentricsService.toService() = DataService(
    id = this.templateId ?: "unknown_service_id",
    name = this.dataProcessor ?: "unknown_service_name",
    description = this.descriptionOfService,
    collectedData = this.dataCollectedList
)