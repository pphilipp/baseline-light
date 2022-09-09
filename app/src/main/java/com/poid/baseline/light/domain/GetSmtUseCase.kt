package com.poid.baseline.light.domain

import com.poid.baseline.light.data.Repository
import com.poid.baseline.light.presentation.ui_model.SomeUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetSmtUseCase(
    private val repository: Repository
) : UseCase<Flow<SomeUiModel>, GetSmtUseCase.UseCaseParams> {

    data class UseCaseParams(val param: Int)

    override fun execute(params: UseCaseParams): Flow<SomeUiModel> = flow {
        emit(SomeUiModel(-1))
    }
}
