package com.poid.baseline.light.di

import com.poid.baseline.light.data.repository.Repository
import com.poid.baseline.light.data.data_source.ApiDataSource
import com.poid.baseline.light.domain.abstraction.IRepository
import com.poid.baseline.light.domain.abstraction.UseCase
import com.poid.baseline.light.domain.use_case.GetSmtUseCase
import com.poid.baseline.light.presentation.SharedViewModel
import com.poid.baseline.light.presentation.ui_model.MasterListItemUiModel

import kotlinx.coroutines.flow.Flow
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    //DataSource
    single { ApiDataSource(get()) }

    //DataSource mappers
    /**
     * place here mappers between data sources. For example from API to DB
     */

    // Repositories
    single<IRepository> { Repository(get()) }

    // domain
    single<UseCase<Flow<List<MasterListItemUiModel>>, GetSmtUseCase.UseCaseParams>> {
        GetSmtUseCase(get())
    }

    //presentation mappers
    /**
     * place here domain to presentation(UIModels) mappers
     */

    // ViewModel
    viewModel { SharedViewModel(DefaultDispatchers(), get()) }
}