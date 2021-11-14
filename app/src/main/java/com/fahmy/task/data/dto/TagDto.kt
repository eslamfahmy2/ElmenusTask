package com.fahmy.task.data.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TagsListDto(

    @Expose
    @SerializedName("tags")
    val tags: List<TagDto>,


    )

data class TagDto(

    @Expose
    @SerializedName("tagName")
    val tagName: String,

    @Expose
    @SerializedName("photoURL")
    val photoURL: String,

    )