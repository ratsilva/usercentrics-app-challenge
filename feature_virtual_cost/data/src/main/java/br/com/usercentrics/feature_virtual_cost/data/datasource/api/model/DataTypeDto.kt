package br.com.usercentrics.feature_virtual_cost.data.datasource.api.model

import br.com.usercentrics.feature_virtual_cost.data.datasource.database.model.DataTypeEntity
import br.com.usercentrics.feature_virtual_cost.domain.model.DataType
import com.squareup.moshi.Json

data class DataTypeDto(
    @Json(name = "description") val description: String,
    @Json(name = "cost") val cost: Double
)

fun DataTypeDto.toDomain() = DataType(
    description = this.description,
    cost = this.cost
)

fun DataTypeDto.toEntity() = DataTypeEntity(
    description = this.description,
    cost = this.cost
)