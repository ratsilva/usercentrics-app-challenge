package br.com.usercentrics.feature_virtual_cost.data.repository

import app.cash.turbine.test
import br.com.usercentrics.feature_virtual_cost.data.DataFixtures
import br.com.usercentrics.feature_virtual_cost.data.datasource.api.model.toEntity
import br.com.usercentrics.feature_virtual_cost.data.datasource.api.service.CostRuleService
import br.com.usercentrics.feature_virtual_cost.data.datasource.database.dao.CostRuleDao
import br.com.usercentrics.feature_virtual_cost.data.datasource.database.model.toDomain
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class CostRuleRepositoryImplTest {

    private val costRuleApiService: CostRuleService = mockk()

    private val costRuleDao: CostRuleDao = mockk()

    private val repository = CostRuleRepositoryImpl(costRuleApiService, costRuleDao)

    @Test
    fun `getAllActive only fetch data from local source`() = runTest {
        val dataFromLocal = DataFixtures.CostRule.fromLocal()

        coEvery {
            costRuleDao.getAllActive()
        } returns flowOf(dataFromLocal)

        repository.getAllActive(onlyCache = true).test {
            val result = awaitItem()
            Assert.assertEquals(
                dataFromLocal.map { it.toDomain() },
                result
            )
            awaitComplete()
        }

        coVerify(exactly = 0) { costRuleApiService.getAll() }
        coVerify(exactly = 1) { costRuleDao.getAllActive() }
    }

    @Test
    fun `getAllActive fetch data from local source and remote source`() = runTest {
        val dataFromLocal = DataFixtures.CostRule.fromLocal()
        val dataFromRemote = DataFixtures.CostRule.fromRemote()

        coEvery {
            costRuleDao.getAllActive()
        } returns flowOf(dataFromLocal)

        coEvery {
            costRuleDao.upsertAll(dataFromRemote.costRules.map { it.toEntity() })
        } returns Unit

        coEvery {
            costRuleApiService.getAll()
        } returns dataFromRemote

        repository.getAllActive(onlyCache = false).test {
            val result = awaitItem()
            Assert.assertEquals(
                dataFromLocal.map { it.toDomain() },
                result
            )
            awaitComplete()
        }

        coVerify(exactly = 1) {
            costRuleApiService.getAll()
            costRuleDao.getAllActive()
        }
    }
}