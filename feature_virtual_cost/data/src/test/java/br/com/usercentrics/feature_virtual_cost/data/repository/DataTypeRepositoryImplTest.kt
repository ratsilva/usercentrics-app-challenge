package br.com.usercentrics.feature_virtual_cost.data.repository

import app.cash.turbine.test
import br.com.usercentrics.feature_virtual_cost.data.DataFixtures
import br.com.usercentrics.feature_virtual_cost.data.datasource.api.model.toEntity
import br.com.usercentrics.feature_virtual_cost.data.datasource.api.service.DataTypeService
import br.com.usercentrics.feature_virtual_cost.data.datasource.database.dao.DataTypeDao
import br.com.usercentrics.feature_virtual_cost.data.datasource.database.model.toDomain
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class DataTypeRepositoryImplTest {

    private val dataTypeApiService: DataTypeService = mockk()

    private val dataTypeDao: DataTypeDao = mockk()

    private val repository = DataTypeRepositoryImpl(dataTypeApiService, dataTypeDao)

    @Test
    fun `getAll only fetch data from local source`() = runTest {
        val dataFromLocal = DataFixtures.DataType.fromLocal()

        coEvery {
            dataTypeDao.getAll()
        } returns flowOf(dataFromLocal)

        repository.getAll(onlyCache = true).test {
            val result = awaitItem()

            Assert.assertEquals(
                dataFromLocal.map { it.toDomain() },
                result
            )

            awaitComplete()
        }

        coVerify(exactly = 0) { dataTypeApiService.getAll() }
        coVerify(exactly = 1) { dataTypeDao.getAll() }
    }

    @Test
    fun `getAll fetch data from local source and remote source`() = runTest {
        val dataFromLocal = DataFixtures.DataType.fromLocal()
        val dataFromRemote = DataFixtures.DataType.fromRemote()

        coEvery {
            dataTypeDao.getAll()
        } returns flowOf(dataFromLocal)

        coEvery {
            dataTypeDao.upsertAll(dataFromRemote.dataTypes.map { it.toEntity() })
        } returns Unit

        coEvery {
            dataTypeApiService.getAll()
        } returns dataFromRemote

        repository.getAll(onlyCache = false).test {
            val result = awaitItem()

            Assert.assertEquals(
                dataFromLocal.map { it.toDomain() },
                result
            )

            awaitComplete()
        }

        coVerify(exactly = 1) {
            dataTypeApiService.getAll()
            dataTypeDao.getAll()
        }
    }
}