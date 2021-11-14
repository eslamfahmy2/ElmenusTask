package com.fahmy.task.domian.mapper

interface DomainMapper<T, DomainModel> {

    fun mapToDomainModel(model: T): DomainModel

}