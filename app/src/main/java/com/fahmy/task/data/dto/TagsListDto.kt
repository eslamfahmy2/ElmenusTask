package com.fahmy.task.data.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ItemListDto(

    @Expose
    @SerializedName("items")
    val tags: List<ItemDto>,


    )

data class ItemDto(

    @Expose
    @SerializedName("id")
    val id: String,

    @Expose
    @SerializedName("name")
    val name: String,

    @Expose
    @SerializedName("photoUrl")
    val photoUrl: String,

    @Expose
    @SerializedName("description")
    val description: String,

    )