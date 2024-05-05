package br.com.usercentrics.plugin_data_privacy.model

import com.usercentrics.sdk.v2.settings.data.UsercentricsService
import org.junit.Assert.assertEquals
import org.junit.Test

class ServiceTest {

    private val id = "servive_id"
    private val description = "custom service"
    private val dataTypes = listOf("data_type_1", "data_type_2")

    @Test
    fun `should convert UsercentricsService to Service with templateId`() {

        val usercentricsService = UsercentricsService(
            templateId = id,
            descriptionOfService = description,
            dataCollectedList = dataTypes
        )

        val service = usercentricsService.toService()

        assertEquals(service.id, id)
        assertEquals(service.description, description)
        assertEquals(service.collectedData, dataTypes)

    }

    @Test
    fun `should convert UsercentricsService to Service without templateId`() {

        val usercentricsService = UsercentricsService(
            descriptionOfService = description,
            dataCollectedList = dataTypes
        )

        val service = usercentricsService.toService()

        assertEquals(service.id, "unknown_service_id")
        assertEquals(service.description, description)
        assertEquals(service.collectedData, dataTypes)

    }

}