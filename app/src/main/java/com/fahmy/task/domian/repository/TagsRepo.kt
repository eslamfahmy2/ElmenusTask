package com.fahmy.task.domian.repository

import androidx.paging.PagingSource
import com.fahmy.task.data.dto.ItemListDto
import com.fahmy.task.data.dto.TagDto

interface TagsRepo {

    fun listTags(): PagingSource<Int, TagDto>

    suspend fun listItemsByTagName(name: String): ItemListDto
}