package com.fahmy.task.domian.useCase

import com.fahmy.task.domian.mapper.ItemsDtoMapper
import com.fahmy.task.domian.model.Item
import com.fahmy.task.domian.repository.TagsRepo
import com.fahmy.task.domian.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ListItemsByTagNameUseCase(
    private val repo: TagsRepo,
    private val mapper: ItemsDtoMapper,
) {
    operator fun invoke(name: String) = flow<DataState<List<Item>>> {
        try {
            emit(DataState.Loading())
            val response = repo.listItemsByTagName(name = name).tags
            val result = response.map {
                mapper.mapToDomainModel(it)
            }
            emit(DataState.Success(result))
        } catch (e: Exception) {
            emit(DataState.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)
        .conflate()

}