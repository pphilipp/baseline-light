package com.poid.baseline.light.presentation

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poid.baseline.light.di.DispatcherProvider
import com.poid.baseline.light.domain.abstraction.IUseCase
import com.poid.baseline.light.domain.model.ConnectionStateModel
import com.poid.baseline.light.domain.use_case.GetSmtUseCase
import com.poid.baseline.light.presentation.ui_model.RequestState
import com.poid.baseline.light.presentation.ui_model.MasterListItemUiModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SharedViewModel(
    private val dispatcher: DispatcherProvider,
    private val getSomeUseCase: IUseCase<Flow<List<MasterListItemUiModel>>, GetSmtUseCase.UseCaseParams>,
    private val observeConnectivityManagerSateUseCase: IUseCase<Flow<ConnectionStateModel>, Nothing?>,
) : ViewModel() {

    val titleMaster: MutableState<String> = mutableStateOf("")
    val titleDetails: MutableState<String> = mutableStateOf("Details")

    private val _masterListFlow =
        MutableStateFlow<RequestState<List<MasterListItemUiModel>>>(RequestState.Idle)
    val masterListFlow: StateFlow<RequestState<List<MasterListItemUiModel>>> =
        _masterListFlow.asStateFlow()

    private val _detailsFlow = MutableStateFlow<RequestState<String>>(RequestState.Idle)
    val detailsFlow: StateFlow<RequestState<String>> = _detailsFlow.asStateFlow()

    private val _connectionState = MutableStateFlow<Pair<ConnectionStateModel, Boolean>>(
        ConnectionStateModel.Idle to false
    )
    val connectionState: StateFlow<Pair<ConnectionStateModel, Boolean>> =
        _connectionState.asStateFlow()

    init {
        observeConnectivityManagerSate()
        fetchMasterContent()
    }

    fun markConnectionStateAsShown() {
        _connectionState.update { currentState ->
            currentState.copy(second = true)
        }
    }

    fun fetchMasterContent() {
        _masterListFlow.value = RequestState.Loading

        viewModelScope.launch(dispatcher.main) {
            try {
                getSomeUseCase
                    .execute(GetSmtUseCase.UseCaseParams(0))
                    .collect {
                        _masterListFlow.value = RequestState.Success(data = it)
                        titleMaster.value = "Master list size - ${it.size}"
                    }
            } catch (e: Throwable) {
                _masterListFlow.value = RequestState.Error(e)
            }

        }
    }

    private fun observeConnectivityManagerSate() {
        try {
            viewModelScope.launch(dispatcher.main) {
                observeConnectivityManagerSateUseCase
                    .execute(null)
                    .collect { connectionState ->
                        Log.d("SharedViewModel", "🚍observeConnectivityManagerSate $connectionState")

                        _connectionState.update { currentState ->
                            currentState.copy(connectionState, false)
                        }
                    }
            }
        } catch (e: Exception) {
            _connectionState.value = ConnectionStateModel.Idle to false
        }
    }

}