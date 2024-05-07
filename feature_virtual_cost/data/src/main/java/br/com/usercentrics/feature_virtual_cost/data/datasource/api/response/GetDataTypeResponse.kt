package br.com.usercentrics.feature_virtual_cost.data.datasource.api.response

import br.com.usercentrics.feature_virtual_cost.data.datasource.api.model.DataTypeDto
import com.squareup.moshi.Json

data class GetDataTypeResponse(
    @Json(name = "data_types") val dataTypes: List<DataTypeDto>
)