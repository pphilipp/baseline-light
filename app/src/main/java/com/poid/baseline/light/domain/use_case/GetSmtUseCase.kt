package com.poid.baseline.light.domain.use_case

import com.poid.baseline.light.domain.abstraction.IRepository
import com.poid.baseline.light.domain.abstraction.IUseCase
import com.poid.baseline.light.presentation.ui_model.MasterListItemUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetSmtUseCase(
    private val repository: IRepository
) : IUseCase<Flow<List<MasterListItemUiModel>>, GetSmtUseCase.UseCaseParams> {

    data class UseCaseParams(val param: Int)

    override fun execute(params: UseCaseParams): Flow<List<MasterListItemUiModel>> = flow {
        emit(
            listOf(
                MasterListItemUiModel("some"),
                MasterListItemUiModel("content"),
                MasterListItemUiModel("here"),
            )
        )
    }
}
