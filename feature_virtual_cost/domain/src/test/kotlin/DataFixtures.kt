import br.com.usercentrics.feature_virtual_cost.domain.model.CostRule
import br.com.usercentrics.feature_virtual_cost.domain.model.DataType
import br.com.usercentrics.feature_virtual_cost.domain.model.Service
import br.com.usercentrics.feature_virtual_cost.domain.model.ServiceCost
import br.com.usercentrics.feature_virtual_cost.domain.model.Strategy

object DataFixtures {

    object Service {

        internal val fromRepository = listOf(
            Service(
                id = "service_id_1",
                name = "service name 1",
                description = "service description 1",
                collectedData = listOf("data1")
            ),
            Service(
                id = "service_id_2",
                name = "service name 2",
                description = "service description 2",
                collectedData = listOf("data2")
            )
        )

    }

    object ServiceCost {

        internal val serviceCostAndMatches = ServiceCost(
            service = Service(
                id = "service_id",
                name = "service name",
                description = "service description",
                collectedData = listOf("data1")
            ),
            cost = 10.0
        )

        internal val serviceCostAndNotMatches = ServiceCost(
            service = Service(
                id = "service_id",
                name = "service name",
                description = "service description",
                collectedData = listOf("data5")
            ),
            cost = 10.0
        )

        internal val serviceCostLessOrEqualMatches = ServiceCost(
            service = Service(
                id = "service_id",
                name = "service name",
                description = "service description",
                collectedData = listOf("data1")
            ),
            cost = 10.0
        )

        internal val serviceCostLessOrEqualNotMatches = ServiceCost(
            service = Service(
                id = "service_id",
                name = "service name",
                description = "service description",
                collectedData = listOf("data5", "data6", "data7")
            ),
            cost = 10.0
        )

    }

    object CostRule {

        internal val costRuleAnd = CostRule(
            name = "rule name",
            enabled = true,
            costFactor = 10.0,
            rule = br.com.usercentrics.feature_virtual_cost.domain.model.CostRule.CostRuleData(
                strategy = Strategy.AND,
                data = mapOf("list" to listOf("data1"))
            )
        )

        internal val costRuleLessOrEqual = CostRule(
            name = "rule name",
            enabled = true,
            costFactor = 10.0,
            rule = br.com.usercentrics.feature_virtual_cost.domain.model.CostRule.CostRuleData(
                strategy = Strategy.LESS_OR_EQUAL,
                data = mapOf("limit" to 2.0)
            )
        )
    }

    object DataType {
        internal val dataTypes = listOf(
            DataType("data1", 2.0),
            DataType("data2", 4.0),
        )
    }
//
//    object DataService {
//
//        internal fun fromPlugin() = listOf(
//            DataService(
//                "service_1",
//                "service name 1",
//                "service description 1",
//                listOf("data1", "data2")
//            ),
//            DataService(
//                "service_2",
//                "service name 2",
//                "service description 2",
//                listOf("data3", "data4")
//            )
//        )
//    }

}
