package com.poid.baseline.light.presentation

import androidx.lifecycle.ViewModel
import com.poid.baseline.light.domain.abstraction.UseCase
import com.poid.baseline.light.domain.use_case.GetSmtUseCase
import com.poid.baseline.light.presentation.ui_model.SomeUiModel
import kotlinx.coroutines.flow.Flow

class SomeViewModel(
    private val getSomeUseCase: UseCase<Flow<SomeUiModel>, GetSmtUseCase.UseCaseParams>
) : ViewModel() {

}