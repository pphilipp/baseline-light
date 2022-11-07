package com.poid.baseline.light.domain.model

import com.poid.baseline.light.domain.abstraction.IDomainModel

sealed class ConnectionStateModel : IDomainModel {
    object Idle : ConnectionStateModel()
    object Available : ConnectionStateModel()
    object Unavailable : ConnectionStateModel()
}