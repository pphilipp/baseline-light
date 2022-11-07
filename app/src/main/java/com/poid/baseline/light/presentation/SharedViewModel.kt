package com.poid.baseline.light.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poid.baseline.light.di.DispatcherProvider
import com.poid.baseline.light.domain.abstraction.UseCase
import com.poid.baseline.light.domain.use_case.GetSmtUseCase
import com.poid.baseline.light.presentation.ui_model.RequestState
import com.poid.baseline.light.presentation.ui_model.MasterListItemUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SharedViewModel(
    private val dispatcher: DispatcherProvider,
    private val getSomeUseCase: UseCase<Flow<List<MasterListItemUiModel>>, GetSmtUseCase.UseCaseParams>,
) : ViewModel() {

    val titleMaster: MutableState<String> = mutableStateOf("")
    val titleDetails: MutableState<String> = mutableStateOf("Details")

    private val _masterListFlow =
        MutableStateFlow<RequestState<List<MasterListItemUiModel>>>(RequestState.Idle)
    val masterListFlow: StateFlow<RequestState<List<MasterListItemUiModel>>> =
        _masterListFlow.asStateFlow()

    private val _detailsFlow = MutableStateFlow<RequestState<String>>(RequestState.Idle)
    val detailsFlow: StateFlow<RequestState<String>> = _detailsFlow.asStateFlow()

    fun fetchMasterContent() {
        _masterListFlow.value = RequestState.Loading

        viewModelScope.launch(dispatcher.main) {
            try {
                getSomeUseCase.execute(
                    GetSmtUseCase.UseCaseParams(0)
                ).collect {
                    _masterListFlow.value = RequestState.Success(data = it)
                    titleMaster.value = "Master list size - ${it.size}"
                }
            } catch (e: Throwable) {
                _masterListFlow.value = RequestState.Error(e)
            }

        }
    }

}