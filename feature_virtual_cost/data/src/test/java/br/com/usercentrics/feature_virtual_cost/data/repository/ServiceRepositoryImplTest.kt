package br.com.usercentrics.feature_virtual_cost.data.repository

import app.cash.turbine.test
import br.com.usercentrics.feature_virtual_cost.data.DataFixtures
import br.com.usercentrics.plugin_data_privacy.service.DataPrivacyService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class ServiceRepositoryImplTest {

    private val dataPrivacyService: DataPrivacyService = mockk()

    private val repository = ServiceRepositoryImpl(dataPrivacyService)

    @Test
    fun `getConsentedServices handles data successfully`() = runTest {
        val dataFromPlugin = DataFixtures.DataService.fromPlugin()

        coEvery {
            dataPrivacyService.isReady()
        } returns flowOf(true)

        coEvery {
            dataPrivacyService.collectedServices()
        } returns flowOf(dataFromPlugin)

        repository.getConsentedServices().test {
            val result = awaitItem()
            Assert.assertEquals(
                dataFromPlugin.map { it.toService() },
                result
            )
            awaitComplete()
        }

        coVerify(exactly = 1) {
            dataPrivacyService.isReady()
            dataPrivacyService.collectedServices()
        }
    }
}