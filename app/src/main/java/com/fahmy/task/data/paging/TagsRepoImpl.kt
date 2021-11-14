package com.fahmy.task.data.paging

import androidx.paging.PagingSource
import com.fahmy.task.data.dto.ItemListDto
import com.fahmy.task.data.dto.TagDto
import com.fahmy.task.data.network.ApiInterface
import com.fahmy.task.domian.repository.TagsRepo

class TagsRepoImpl constructor(
    private val apiInterface: ApiInterface
) : TagsRepo {
    override fun listTags(): PagingSource<Int, TagDto> {
        return PagedTagsSource(apiInterface)
    }

    override suspend fun listItemsByTagName(name: String): ItemListDto {
        return apiInterface.listItemsByTagName(name)
    }

}