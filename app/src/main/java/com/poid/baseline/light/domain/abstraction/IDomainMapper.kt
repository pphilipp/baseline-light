package com.poid.baseline.light.domain.abstraction

interface IDomainMapper<DataModel, out IDomainModel> {
    fun map(from: DataModel): IDomainModel
}