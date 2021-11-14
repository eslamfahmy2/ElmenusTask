package com.fahmy.task.domian.useCase

import androidx.paging.*
import com.fahmy.task.domian.mapper.ItemsDtoMapper
import com.fahmy.task.domian.mapper.TagDtoMapper
import com.fahmy.task.domian.model.Tag
import com.fahmy.task.domian.repository.TagsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

private const val TAG = "ListPagedTagsUseCase"

sealed class ListPagedSuccessState {
    data class PagedFlow(val flow: Flow<PagingData<Tag>>) : ListPagedSuccessState()
    data class LoadInitItems(val name: String) : ListPagedSuccessState()
}


class ListPagedTagsUseCase(
    private val repo: TagsRepo,
    private val mapper: TagDtoMapper,
    private val itemsDtoMapper: ItemsDtoMapper
) {
    operator fun invoke(): Flow<PagingData<Tag>> {
        return Pager(
            config = PagingConfig(
                pageSize = 8,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { repo.listTags() }
        ).flow.map { it ->
            it.map {
                mapper.mapToDomainModel(it)
            }
        }.flowOn(Dispatchers.IO)
            .conflate()
    }

}