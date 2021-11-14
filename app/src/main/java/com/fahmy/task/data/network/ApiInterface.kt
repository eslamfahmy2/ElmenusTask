package com.fahmy.task.data.network


import com.fahmy.task.data.dto.ItemListDto
import com.fahmy.task.data.dto.TagsListDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("tags/{page}")
    suspend fun listTags(@Path("page") page: Int): TagsListDto

    @GET("items/{tagName}")
    suspend fun listItemsByTagName(@Path("tagName") tagName: String): ItemListDto
}