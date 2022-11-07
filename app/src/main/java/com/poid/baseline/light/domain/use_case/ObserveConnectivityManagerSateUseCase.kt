package com.poid.baseline.light.domain.use_case

import com.poid.baseline.light.domain.abstraction.IDomainMapper
import com.poid.baseline.light.domain.abstraction.IRepository
import com.poid.baseline.light.domain.abstraction.IUseCase
import com.poid.baseline.light.domain.model.ConnectionStateModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ObserveConnectivityManagerSateUseCase(
    private val repository: IRepository,
    private val mapper: IDomainMapper<Boolean, ConnectionStateModel>
) : IUseCase<Flow<ConnectionStateModel>, Nothing?> {

    override fun execute(params: Nothing?): Flow<ConnectionStateModel> =
        repository.observeConnectivityState().map {
            mapper.map(it)
        }
}