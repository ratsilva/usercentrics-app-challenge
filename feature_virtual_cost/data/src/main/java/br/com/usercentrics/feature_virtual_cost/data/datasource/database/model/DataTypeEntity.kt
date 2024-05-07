package br.com.usercentrics.feature_virtual_cost.data.datasource.database.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import br.com.usercentrics.feature_virtual_cost.domain.model.DataType

@Entity(tableName = "data_types", indices = [Index(value = ["description"], unique = true)])
data class DataTypeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val description: String,
    val cost: Double
)

fun DataTypeEntity.toDomain() = DataType(
    description = this.description,
    cost = this.cost
)