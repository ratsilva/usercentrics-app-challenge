package br.com.usercentrics.feature_virtual_cost.data

import br.com.usercentrics.feature_virtual_cost.data.datasource.api.model.CostRuleDto
import br.com.usercentrics.feature_virtual_cost.data.datasource.api.model.CostRuleDto.CostRuleDataDto
import br.com.usercentrics.feature_virtual_cost.data.datasource.api.model.DataTypeDto
import br.com.usercentrics.feature_virtual_cost.data.datasource.api.response.GetCostRuleResponse
import br.com.usercentrics.feature_virtual_cost.data.datasource.api.response.GetDataTypeResponse
import br.com.usercentrics.feature_virtual_cost.data.datasource.database.model.CostRuleEntity
import br.com.usercentrics.feature_virtual_cost.data.datasource.database.model.CostRuleEntity.CostRuleDataEntity
import br.com.usercentrics.feature_virtual_cost.data.datasource.database.model.DataTypeEntity
import br.com.usercentrics.plugin_data_privacy.model.DataService

object DataFixtures {

    object CostRule {

        internal fun fromLocal() = listOf(
            CostRuleEntity(
                1,
                "Banking snoopy",
                true,
                10.0,
                CostRuleDataEntity("AND", mapOf("list" to listOf("data1", "data2", "data3")))
            ),
            CostRuleEntity(
                2,
                "Why do you care?",
                true,
                27.0,
                CostRuleDataEntity("AND", mapOf("list" to listOf("data4", "data5", "data6")))
            ),
            CostRuleEntity(
                3,
                "The good citizen",
                true,
                -10.0,
                CostRuleDataEntity("LESS_OR_EQUAL", mapOf("limit" to 4))
            ),
        )

        internal fun fromRemote() = GetCostRuleResponse(
            costRules = listOf(
                CostRuleDto(
                    "My custom rule",
                    true,
                    -15.0,
                    CostRuleDataDto("LESS_OR_EQUAL", mapOf("limit" to 1))
                )
            )
        )
    }

    object DataType {
        internal fun fromLocal() = listOf(
            DataTypeEntity(1, "IP address", 2.0),
            DataTypeEntity(2, "User behaviour", 2.0),
            DataTypeEntity(3, "User agent", 2.0)
        )

        internal fun fromRemote() = GetDataTypeResponse(
            dataTypes = listOf(
                DataTypeDto("App crashes", -2.0)
            )
        )
    }

    object DataService {

        internal fun fromPlugin() = listOf(
            DataService(
                "service_1",
                "service name 1",
                "service description 1",
                listOf("data1", "data2")
            ),
            DataService(
                "service_2",
                "service name 2",
                "service description 2",
                listOf("data3", "data4")
            )
        )
    }

}
