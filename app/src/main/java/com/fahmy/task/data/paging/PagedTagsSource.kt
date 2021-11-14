package com.fahmy.task.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fahmy.task.data.dto.TagDto
import com.fahmy.task.data.network.ApiInterface
import javax.inject.Inject

private const val UNSPLASH_STARTING_PAGE_INDEX = 1

class PagedTagsSource @Inject constructor(
    private val source: ApiInterface,
) : PagingSource<Int, TagDto>() {

    override val jumpingSupported: Boolean
        get() = true

    override val keyReuseSupported: Boolean
        get() = true

    override fun getRefreshKey(state: PagingState<Int, TagDto>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TagDto> {
        return try {
            val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX
            val response = source.listTags(
                page = position,
            )
            LoadResult.Page(
                data = response.tags,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (response.tags.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


}