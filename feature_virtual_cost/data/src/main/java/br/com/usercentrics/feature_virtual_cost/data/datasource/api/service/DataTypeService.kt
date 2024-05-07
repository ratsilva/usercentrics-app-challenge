package br.com.usercentrics.feature_virtual_cost.data.datasource.api.service

import br.com.usercentrics.feature_virtual_cost.data.datasource.api.response.GetDataTypeResponse
import retrofit2.http.GET

interface DataTypeService {
    @GET("/data_types")
    suspend fun getAll(): GetDataTypeResponse
}