package usecase

import DataFixtures
import br.com.usercentrics.feature_virtual_cost.domain.repository.DataTypeRepository
import br.com.usercentrics.feature_virtual_cost.domain.usecase.CalculateServiceDataTypeCostUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class CalculateServiceDataTypeCostUseCaseTest {

    private val dataTypeRepository: DataTypeRepository = mockk()

    private val useCase = CalculateServiceDataTypeCostUseCase(dataTypeRepository)

    private val serviceCost = DataFixtures.ServiceCost.serviceCostAndMatches

    @Test
    fun `should calculate service cost with DataType`() = runBlocking {

        val dataTypes = DataFixtures.DataType.dataTypes
        coEvery {
            dataTypeRepository.getAll()
        } returns flowOf(dataTypes)

        val result = useCase(serviceCost)

        Assert.assertEquals(
            2.0,
            result.cost,
            0.0
        )

        coVerify(exactly = 1) {
            dataTypeRepository.getAll()
        }

    }
}