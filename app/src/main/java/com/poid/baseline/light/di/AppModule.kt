package com.poid.baseline.light.di

import com.poid.baseline.light.data.repository.Repository
import com.poid.baseline.light.data.data_source.ApiDataSource
import com.poid.baseline.light.data.data_source.SystemServicesDataSource
import com.poid.baseline.light.domain.abstraction.IDomainMapper
import com.poid.baseline.light.domain.abstraction.IRepository
import com.poid.baseline.light.domain.abstraction.IUseCase
import com.poid.baseline.light.data.mapper.NetworkStateMapper
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
    single { SystemServicesDataSource(
        connectivityManager = get(),
        get(named(NetworkStateMapper::class.java.name))) }

    //DataSource mappers
    /**
     * place here mappers between data sources. For example from API to DB
     */

    // Repositories
    single<IRepository> {
        Repository(
            apiDataSource = get(),
            systemServicesDataSource = get()
        )
    }

    // domain
    factory<IUseCase<Flow<List<MasterListItemUiModel>>, GetSmtUseCase.UseCaseParams>>(
        named(GetSmtUseCase::class.java.name)
    ) {
        GetSmtUseCase(get())
    }
    factory<IUseCase<Flow<ConnectionStateModel>, Nothing?>>(
        named(ObserveConnectivityManagerSateUseCase::class.java.name)
    ) {
        ObserveConnectivityManagerSateUseCase(
            repository = get()
        )
    }

    // domain mappers
    factory<IDomainMapper<Boolean, ConnectionStateModel>>(
        named(NetworkStateMapper::class.java.name)
    ) {
        NetworkStateMapper()
    }

    // ViewModel
    viewModel {
        SharedViewModel(
            dispatcher = DefaultDispatchers(),
            getSomeUseCase = get(named(GetSmtUseCase::class.java.name)),
            observeConnectivityManagerSateUseCase = get(named(ObserveConnectivityManagerSateUseCase::class.java.name))
        )
    }
}