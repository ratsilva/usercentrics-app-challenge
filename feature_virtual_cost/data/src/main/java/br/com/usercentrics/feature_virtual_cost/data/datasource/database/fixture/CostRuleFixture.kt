package br.com.usercentrics.feature_virtual_cost.data.datasource.database.fixture

internal val costRuleFixture = listOf(
    "INSERT INTO cost_rules VALUES (1, 'Banking snoopy', true, 10, 'AND', '{\"list\":[\"Purchase activity\",\"Bank details\",\"Credit and debit card number\"]}')",
    "INSERT INTO cost_rules VALUES (2, 'Why do you care?', true, 27, 'AND', '{\"list\":[\"Search terms\",\"Geographic location\",\"IP Address\"]}')",
    "INSERT INTO cost_rules VALUES (3, 'The good citizen', true, -10, 'LESS_OR_EQUAL', '{\"limit\":4}')"

)