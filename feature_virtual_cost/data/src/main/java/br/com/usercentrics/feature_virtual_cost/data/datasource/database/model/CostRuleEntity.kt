package br.com.usercentrics.feature_virtual_cost.data.datasource.database.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import br.com.usercentrics.feature_virtual_cost.domain.model.CostRule
import br.com.usercentrics.feature_virtual_cost.domain.model.CostRule.CostRuleData
import br.com.usercentrics.feature_virtual_cost.domain.model.Strategy
import kotlinx.serialization.SerialName

@Entity(tableName = "cost_rules", indices = [Index(value = ["name"], unique = true)])
data class CostRuleEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val enabled: Boolean,
    val costFactor: Double,
    @Embedded val rule: CostRuleDataEntity,
) {
    data class CostRuleDataEntity(
        @SerialName("strategy") val strategy: String,
        @SerialName("data") val data: Map<String, Any>
    )
}

fun CostRuleEntity.toDomain() = CostRule(
    name = this.name,
    enabled = this.enabled,
    costFactor = this.costFactor,
    rule = CostRuleData(
        strategy = Strategy.valueOf(this.rule.strategy),
        data = this.rule.data
    )
)

