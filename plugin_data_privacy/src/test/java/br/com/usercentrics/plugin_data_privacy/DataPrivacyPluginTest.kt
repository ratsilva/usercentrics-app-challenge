package br.com.usercentrics.plugin_data_privacy

import android.content.Context
import br.com.usercentrics.plugin_data_privacy.service.DataPrivacyService
import br.com.usercentrics.plugin_data_privacy.service.DataPrivacyServiceFactory
import br.com.usercentrics.plugin_data_privacy.service.DefaultDataPrivacyServiceFactory
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.After
import org.junit.Assert
import org.junit.Test

class DataPrivacyPluginTest {

    private val dataPrivacyService: DataPrivacyService = mockk()
    private val dataPrivacyServiceFactory: FakeDataPrivacyServiceFactory = mockk()
    private val context: Context = mockk()
    
    @After
    fun clearAll(){
        DataPrivacyPlugin.service = null
        DataPrivacyPlugin.serviceFactory = DefaultDataPrivacyServiceFactory()
    }

    @Test
    fun `should return instance when is already instantiated`() {

        DataPrivacyPlugin.service = dataPrivacyService

        Assert.assertEquals(
            dataPrivacyService,
            DataPrivacyPlugin.initialize(context)
        )

        coVerify(exactly = 0) {
            dataPrivacyServiceFactory.create()
        }

    }

    @Test
    fun `should create new instance when first initialization`() {

        coEvery {
            dataPrivacyServiceFactory.create()
        } returns dataPrivacyService

        coEvery {
            dataPrivacyService.initialize(context)
        } returns Unit

        DataPrivacyPlugin.serviceFactory = dataPrivacyServiceFactory

        Assert.assertEquals(
            dataPrivacyService,
            DataPrivacyPlugin.initialize(context)
        )

        coVerify(exactly = 1) {
            dataPrivacyServiceFactory.create()
        }

    }

}

abstract class FakeDataPrivacyServiceFactory : DataPrivacyServiceFactory