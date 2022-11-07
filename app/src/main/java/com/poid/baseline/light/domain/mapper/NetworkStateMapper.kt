package com.poid.baseline.light.domain.mapper

import com.poid.baseline.light.domain.abstraction.IDomainMapper
import com.poid.baseline.light.domain.model.ConnectionStateModel

class NetworkStateMapper : IDomainMapper<Boolean, ConnectionStateModel> {

    override fun map(from: Boolean): ConnectionStateModel = when (from) {
        true -> ConnectionStateModel.Available
        false -> ConnectionStateModel.Unavailable
    }
}