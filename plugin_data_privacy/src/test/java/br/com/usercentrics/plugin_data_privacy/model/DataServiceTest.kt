package br.com.usercentrics.plugin_data_privacy.model

import com.usercentrics.sdk.v2.settings.data.UsercentricsService
import org.junit.Assert.assertEquals
import org.junit.Test

class DataServiceTest {

    private val id = "servive_id"
    private val name = "my custom service"
    private val description = "custom service"
    private val dataTypes = listOf("data_type_1", "data_type_2")

    @Test
    fun `should convert UsercentricsService to Service with templateId and dataProcessor`() {

        val usercentricsService = UsercentricsService(
            templateId = id,
            dataProcessor = name,
            descriptionOfService = description,
            dataCollectedList = dataTypes
        )

        val dataService = usercentricsService.toService()

        assertEquals(dataService.id, id)
        assertEquals(dataService.name, name)
        assertEquals(dataService.description, description)
        assertEquals(dataService.collectedData, dataTypes)

    }

    @Test
    fun `should convert UsercentricsService to Service without templateId`() {

        val usercentricsService = UsercentricsService(
            templateId = null,
            dataProcessor = null,
            descriptionOfService = description,
            dataCollectedList = dataTypes
        )

        val dataService = usercentricsService.toService()

        assertEquals(dataService.id, "unknown_service_id")
        assertEquals(dataService.name, "unknown_service_name")
        assertEquals(dataService.description, description)
        assertEquals(dataService.collectedData, dataTypes)

    }

}