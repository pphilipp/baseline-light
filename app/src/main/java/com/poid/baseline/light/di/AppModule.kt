package com.poid.baseline.light.di

import com.poid.baseline.light.data.repository.Repository
import com.poid.baseline.light.data.data_source.ApiDataSource
import com.poid.baseline.light.data.data_source.SystemServicesDataSource
import com.poid.baseline.light.domain.abstraction.IDomainMapper
import com.poid.baseline.light.domain.abstraction.IRepository
import com.poid.baseline.light.domain.abstraction.IUseCase
import com.poid.baseline.light.domain.mapper.NetworkStateMapper
import com.poid.baseline.light.domain.model.ConnectionStateModel
import com.poid.baseline.light.domain.use_case.GetSmtUseCase
import com.poid.baseline.light.domain.use_case.ObserveConnectivityManagerSateUseCase
import com.poid.baseline.light.presentation.SharedViewModel
import com.poid.baseline.light.presentation.ui_model.MasterListItemUiModel

import kotlinx.coroutines.flow.Flow
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {

    //DataSource
    single { ApiDataSource(get()) }
    single { SystemServicesDataSource(get()) }

    //DataSource mappers
    /**
     * place here mappers between data sources. For example from API to DB
     */

    // Repositories
    single<IRepository> { Repository(get(), get()) }

    // domain
    factory<IUseCase<Flow<List<MasterListItemUiModel>>, GetSmtUseCase.UseCaseParams>>(
        named("GetSmtUseCase")
    ) {
        GetSmtUseCase(get())
    }
    factory<IUseCase<Flow<ConnectionStateModel>, Nothing?>>(
        named("ObserveConnectivityManagerSateUseCase")
    ) {
        ObserveConnectivityManagerSateUseCase(get(), get())
    }

    // domain mappers
    factory<IDomainMapper<Boolean, ConnectionStateModel>>(
        named("NetworkStateMapper")
    ) {
        NetworkStateMapper()
    }

    // ViewModel
    viewModel {
        SharedViewModel(
            DefaultDispatchers(),
            get(named("GetSmtUseCase"))
        )
    }
}