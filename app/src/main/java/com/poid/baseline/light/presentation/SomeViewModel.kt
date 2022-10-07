package com.poid.baseline.light.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poid.baseline.light.domain.abstraction.UseCase
import com.poid.baseline.light.domain.use_case.GetSmtUseCase
import com.poid.baseline.light.presentation.ui_model.SomeUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SomeViewModel(
    private val getSomeUseCase: UseCase<Flow<SomeUiModel>, GetSmtUseCase.UseCaseParams>
) : ViewModel() {

    fun getSome() {
        try {
            viewModelScope.launch {
                getSomeUseCase.execute(GetSmtUseCase.UseCaseParams(0)).collect{
                    it
                }
            }
        } catch (e: Exception) {

        }
    }

}