package br.com.usercentrics.feature_virtual_cost

import br.com.usercentrics.feature_virtual_cost.data.datasource.api.service.CostRuleService
import br.com.usercentrics.feature_virtual_cost.data.datasource.api.service.DataTypeService
import br.com.usercentrics.feature_virtual_cost.data.datasource.database.VirtualCostDatabase
import br.com.usercentrics.feature_virtual_cost.data.repository.CostRuleRepositoryImpl
import br.com.usercentrics.feature_virtual_cost.data.repository.DataTypeRepositoryImpl
import br.com.usercentrics.feature_virtual_cost.data.repository.ServiceRepositoryImpl
import br.com.usercentrics.feature_virtual_cost.domain.calculator.CalculatorStrategyFactory
import br.com.usercentrics.feature_virtual_cost.domain.calculator.DefaultCalculatorStrategyFactory
import br.com.usercentrics.feature_virtual_cost.domain.repository.CostRuleRepository
import br.com.usercentrics.feature_virtual_cost.domain.repository.DataTypeRepository
import br.com.usercentrics.feature_virtual_cost.domain.repository.ServiceRepository
import br.com.usercentrics.feature_virtual_cost.domain.usecase.CalculateServiceCostRulesUseCase
import br.com.usercentrics.feature_virtual_cost.domain.usecase.CalculateServiceDataTypeCostUseCase
import br.com.usercentrics.feature_virtual_cost.domain.usecase.GetServiceCostUseCase
import br.com.usercentrics.feature_virtual_cost.presentation.viewmodel.VirtualCostViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit

internal val presentationModule = module {
    viewModelOf(::VirtualCostViewModel)
}

internal val domainModule = module {
    single<CalculatorStrategyFactory> { DefaultCalculatorStrategyFactory() }

    singleOf(::CalculateServiceCostRulesUseCase)

    singleOf(::CalculateServiceDataTypeCostUseCase)

    singleOf(::GetServiceCostUseCase)
}

internal val dataModule = module {
    single { get<Retrofit>().create(CostRuleService::class.java) }
    single { get<Retrofit>().create(DataTypeService::class.java) }

    single {
        VirtualCostDatabase.getDatabase(get())
    }
    single { get<VirtualCostDatabase>().costRules() }
    single { get<VirtualCostDatabase>().dataTypes() }

    single<CostRuleRepository> { CostRuleRepositoryImpl(get(), get()) }
    single<DataTypeRepository> { DataTypeRepositoryImpl(get(), get()) }
    single<ServiceRepository> { ServiceRepositoryImpl(get()) }
}

val featureVirtualCostModules = listOf(
    presentationModule,
    domainModule,
    dataModule
)